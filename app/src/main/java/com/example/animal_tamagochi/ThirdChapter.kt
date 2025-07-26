package com.example.animal_tamagochi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.example.animal_tamagochi.models.Card
import android.os.Handler
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class ThirdChapter : ComponentActivity() {

    private lateinit var dialogueLauncher: ActivityResultLauncher<Intent>
    private lateinit var progressBar: ProgressBar
    private var timer: CountDownTimer? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_third_chapter)

        progressBar = findViewById(R.id.progress_timer)

        dialogueLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                startGameTimer()
            }

        val startIntent = Intent(this, DialogueActivity::class.java)
        startIntent.putExtra("CHAPTER_KEY", 3)
        dialogueLauncher.launch(startIntent)

        fun getFinishDialogue() {
            timer?.cancel()
            val intent = Intent(this, DialogueActivity::class.java)
            intent.putExtra("CHAPTER_KEY", 33)
            dialogueLauncher.launch(intent)
        }

        var flippedCard = mutableListOf<Pair<ImageView, Card>>()
        var counter = 0

        val cardList = mutableListOf(
            Card(false, R.drawable.card1),
            Card(false, R.drawable.card1),
            Card(false, R.drawable.card2),
            Card(false, R.drawable.card2),
            Card(false, R.drawable.card3),
            Card(false, R.drawable.card3),
            Card(false, R.drawable.card4),
            Card(false, R.drawable.card4)
        ).shuffled()

        val imageViews = listOf(
            findViewById<ImageView>(R.id.card1),
            findViewById<ImageView>(R.id.card2),
            findViewById<ImageView>(R.id.card3),
            findViewById<ImageView>(R.id.card4),
            findViewById<ImageView>(R.id.card5),
            findViewById<ImageView>(R.id.card6),
            findViewById<ImageView>(R.id.card7),
            findViewById<ImageView>(R.id.card8)
        )

        for (imageView in imageViews) {
            imageView.setImageResource(R.drawable.cover_card)
        }

        for (i in cardList.indices) {
            val card = cardList[i]
            val imageView = imageViews[i]

            imageView.setOnClickListener {
                if (card.isFlipped || flippedCard.size == 2) return@setOnClickListener

                card.isFlipped = true
                imageView.setImageResource(card.image)
                flippedCard.add(imageView to card)

                if (flippedCard.size == 2) {
                    val (firstView, firstCard) = flippedCard[0]
                    val (secondView, secondCard) = flippedCard[1]

                    if (firstCard.image == secondCard.image) {
                        flippedCard.clear()
                        counter += 2
                        if (counter == cardList.size) {
                            getFinishDialogue()
                            finish()
                        }
                    } else {
                        Handler().postDelayed({
                            firstCard.isFlipped = false
                            secondCard.isFlipped = false
                            firstView.setImageResource(R.drawable.cover_card)
                            secondView.setImageResource(R.drawable.cover_card)
                            flippedCard.clear()
                        }, 750)
                    }
                }
            }
        }
    }

    private fun startGameTimer() {
        timer = object : CountDownTimer(10000, 100) {
            override fun onTick(millisUntilFinished: Long) {
                val progress = (millisUntilFinished / 10000.0 * 100).toInt()
                progressBar.progress = progress
            }

            override fun onFinish() {
                progressBar.progress = 0
                Toast.makeText(
                    this@ThirdChapter,
                    "Время вышло! Попробуй еще раз!",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }

        timer?.start() // теперь используется глобальная переменная timer
    }
}