package com.example.animal_tamagochi

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import java.util.Timer
import java.util.TimerTask
import kotlin.random.Random

class SecondChapter : ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second_chapter)

        val intent = Intent(this, DialogueActivity::class.java)
        intent.putExtra("CHAPTER_KEY", 2)
        startActivity(intent)

        var imageCounter = 0
        val timerFly = Timer()
        val gameLayout = findViewById<FrameLayout>(R.id.second_chapter_layout)
        val counterView = findViewById<TextView>(R.id.fly_counter)
        val progressBar = findViewById<ProgressBar>(R.id.progress_timer)
        val imageView = ImageView(this)

        fun getFinishDialogue() {
            val intent = Intent(this, DialogueActivity::class.java)
            intent.putExtra("CHAPTER_KEY", 22)
            startActivity(intent)
        }


        fun getFly(): ImageView {
            imageView.setImageResource(R.drawable.fly_no_background)
            imageView.layoutParams = FrameLayout.LayoutParams(200, 200)

            val displayMetrics = Resources.getSystem().displayMetrics
            val screenWidth = displayMetrics.widthPixels
            val screenHeight = displayMetrics.heightPixels

            val randomX = Random.nextInt(0, screenWidth - 150)
            val randomY = Random.nextInt(0, screenHeight - 150)

            imageView.x = randomX.toFloat()
            imageView.y = randomY.toFloat()

            return imageView
        }

        val timerChapter = object : CountDownTimer(60000, 100) {
            override fun onTick(millisUntilFinished: Long) {
                val progress = (millisUntilFinished / 60000.0 * 100).toInt()
                progressBar.progress = progress
            }

            override fun onFinish() {
                progressBar.progress = 0
                Toast.makeText(this@SecondChapter, "Время вышло! Попробуй еще раз!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
        timerChapter.start()


        timerFly.schedule(object : TimerTask() {
            @SuppressLint("SuspiciousIndentation")
            override fun run() {
                runOnUiThread {
                    if (imageCounter >= 10) {
                        imageCounter = 0
                        getFinishDialogue()
                        finish()
                    } else
                        getFly()
                }
            }
        }, 0, 500)

        gameLayout.addView(imageView)

        imageView.setOnClickListener() {
            imageCounter++
            counterView.text = "Мух поймано: ${imageCounter}"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        var timer = Timer()
        timer.cancel()
    }
}