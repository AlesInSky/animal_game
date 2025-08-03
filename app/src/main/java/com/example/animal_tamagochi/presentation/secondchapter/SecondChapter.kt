package com.example.animal_tamagochi.presentation.secondchapter

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.animal_tamagochi.DialogueActivity
import com.example.animal_tamagochi.R
import com.example.animal_tamagochi.domain.usecase.GetFlyUseCase
import java.util.Timer
import java.util.TimerTask

class SecondChapter : ComponentActivity() {

    private lateinit var viewModel: SecondChapterViewModel
    private lateinit var timerFly: Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second_chapter)

        val getFlyUseCase = GetFlyUseCase()
        val factory = SecondChapterViewModelFactory(getFlyUseCase)
        viewModel = ViewModelProvider(this, factory)[SecondChapterViewModel::class.java]

        val intent = Intent(this, DialogueActivity::class.java)
        intent.putExtra("CHAPTER_KEY", 2)
        startActivity(intent)

        timerFly = Timer()
        val gameLayout = findViewById<FrameLayout>(R.id.second_chapter_layout)
        val counterView = findViewById<TextView>(R.id.fly_counter)
        val progressBar = findViewById<ProgressBar>(R.id.progress_timer)
        val imageView = ImageView(this)

        viewModel.flyProperties.observe(this) { props ->
            imageView.setImageResource(props.imageResId)
            imageView.layoutParams = FrameLayout.LayoutParams(props.width, props.height)
            imageView.x = props.x
            imageView.y = props.y
        }

        fun getFinishDialogue() {
            val intent = Intent(this, DialogueActivity::class.java)
            intent.putExtra("CHAPTER_KEY", 22)
            startActivity(intent)
        }

        val timerChapter = object : CountDownTimer(60000, 100) {
            override fun onTick(millisUntilFinished: Long) {
                val progress = (millisUntilFinished / 60000.0 * 100).toInt()
                progressBar.progress = progress
            }

            override fun onFinish() {
                progressBar.progress = 0
                Toast.makeText(
                    this@SecondChapter,
                    "Время вышло! Попробуй еще раз!",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
        timerChapter.start()

        timerFly.schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    if (viewModel.getCurrentCounter() >= 10) {
                        viewModel.resetCounter()
                        getFinishDialogue()
                        finish()
                    } else
                        viewModel.getFly()
                }
            }
        }, 0, 500)

        gameLayout.addView(imageView)

        imageView.setOnClickListener {
            viewModel.incrementCounter()
        }

        viewModel.counter.observe(this) { count ->
            counterView.text = "Мух поймано: $count"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        timerFly.cancel()
    }
}