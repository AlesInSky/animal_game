package com.example.animal_tamagochi.presentation.thirdchapter

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animal_tamagochi.models.CardAdapter
import com.example.animal_tamagochi.DialogueActivity
import com.example.animal_tamagochi.R

class ThirdChapter : ComponentActivity() {

    private lateinit var dialogueLauncher: ActivityResultLauncher<Intent>
    private lateinit var progressBar: ProgressBar
    private var timer: CountDownTimer? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CardAdapter

    private lateinit var viewModel: ThirdChapterViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_third_chapter)

        progressBar = findViewById(R.id.progress_timer)
        recyclerView = findViewById(R.id.card_grid)
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        viewModel = ViewModelProvider(this)[ThirdChapterViewModel::class.java]

        adapter = CardAdapter(viewModel.cards.value ?: mutableListOf()) { index ->
            viewModel.handleCardClick(index)
        }

        recyclerView.adapter = adapter

        dialogueLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                startGameTimer()
            }

        viewModel.cards.observe(this) { newCards ->
            adapter.updateCards(newCards)
        }

        viewModel.matchedCount.observe(this) { count ->
            if (count == viewModel.cards.value?.size) {
                getFinishDialogue()
            }
        }

        val startIntent = Intent(this, DialogueActivity::class.java)
        startIntent.putExtra("CHAPTER_KEY", 3)
        dialogueLauncher.launch(startIntent)
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