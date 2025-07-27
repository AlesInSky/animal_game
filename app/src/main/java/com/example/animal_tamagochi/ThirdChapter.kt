package com.example.animal_tamagochi
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.example.animal_tamagochi.models.Card
import android.os.Handler
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ThirdChapter : ComponentActivity() {

    private lateinit var dialogueLauncher: ActivityResultLauncher<Intent>
    private lateinit var progressBar: ProgressBar
    private var timer: CountDownTimer? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var cards: MutableList<Card>
    private lateinit var adapter: CardAdapter

    private var flippedCards = mutableListOf<Int>()
    private var matchedCount = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_third_chapter)

        progressBar = findViewById(R.id.progress_timer)
        recyclerView = findViewById(R.id.card_grid)
        recyclerView.layoutManager = GridLayoutManager(this, 3)

        // Список карточек
        cards = mutableListOf(
            Card(false, R.drawable.card1),
            Card(false, R.drawable.card1),
            Card(false, R.drawable.card2),
            Card(false, R.drawable.card2),
            Card(false, R.drawable.card3),
            Card(false, R.drawable.card3),
            Card(false, R.drawable.card4),
            Card(false, R.drawable.card4),
            Card(false, R.drawable.card5),
            Card(false, R.drawable.card5),
            Card(false, R.drawable.card6),
            Card(false, R.drawable.card6)
        ).shuffled().toMutableList()

        adapter = CardAdapter(cards) { index ->
            handleCardClick(index)
        }

        recyclerView.adapter = adapter

        // Диалог перед стартом
        dialogueLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            startGameTimer()
        }

        val startIntent = Intent(this, DialogueActivity::class.java)
        startIntent.putExtra("CHAPTER_KEY", 3)
        dialogueLauncher.launch(startIntent)
    }

    private fun handleCardClick(index: Int) {
        if (cards[index].isFlipped || flippedCards.size == 2) return

        cards[index].isFlipped = true
        flippedCards.add(index)
        adapter.notifyItemChanged(index)

        if (flippedCards.size == 2) {
            val first = flippedCards[0]
            val second = flippedCards[1]

            if (cards[first].image == cards[second].image) {
                // Успешная пара
                flippedCards.clear()
                matchedCount += 2

                if (matchedCount == cards.size) {
                    getFinishDialogue()
                    finish()
                }
            } else {
                // Не совпало — переворачиваем обратно через паузу
                Handler().postDelayed({
                    cards[first].isFlipped = false
                    cards[second].isFlipped = false
                    adapter.notifyItemChanged(first)
                    adapter.notifyItemChanged(second)
                    flippedCards.clear()
                }, 650)
            }
        }
    }

    private fun getFinishDialogue() {
        timer?.cancel()
        val intent = Intent(this, DialogueActivity::class.java)
        intent.putExtra("CHAPTER_KEY", 33)
        dialogueLauncher.launch(intent)
    }

    private fun startGameTimer() {
        timer = object : CountDownTimer(40000, 100) {
            override fun onTick(millisUntilFinished: Long) {
                val progress = (millisUntilFinished / 40000.0 * 100).toInt()
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

        timer?.start()
    }
}