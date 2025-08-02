package com.example.animal_tamagochi

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider

class FirstChapter : ComponentActivity() {

    private lateinit var viewModel: FirstChapterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, DialogueActivity::class.java)
        intent.putExtra("CHAPTER_KEY",1)
        startActivity(intent)

        enableEdgeToEdge()
        setContentView(R.layout.activity_first_chapter)

        viewModel = ViewModelProvider(this)[FirstChapterViewModel::class.java]

        val dialogueText = findViewById<TextView>(R.id.dialogue_text)
        val firstButton = findViewById<Button>(R.id.tadpole_first)
        val secondButton = findViewById<Button>(R.id.tadpole_second)
        val thirdButton = findViewById<Button>(R.id.tadpole_thirst)
        val imageTadpole = findViewById<ImageView>(R.id.tadpole_image)

        viewModel.dialogueText.observe(this) {
            dialogueText.text = it
        }

        viewModel.buttonVisibility.observe(this) { visible ->
            val visibility = if (visible) View.VISIBLE else View.INVISIBLE
            firstButton.visibility = visibility
            secondButton.visibility = visibility
            thirdButton.visibility = visibility
        }

        viewModel.toastMessage.observe(this) { message ->
            message?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                viewModel.clearToast()
            }
        }

        viewModel.chapterFinished.observe(this) { isFinished ->
            if (isFinished) {
                val intent = Intent(this, DialogueActivity::class.java)
                intent.putExtra("CHAPTER_KEY", 11)
                startActivity(intent)
                finish()
            }
        }

        firstButton.setOnClickListener { viewModel.selectTadpole(1) }
        secondButton.setOnClickListener { viewModel.selectTadpole(2) }
        thirdButton.setOnClickListener { viewModel.selectTadpole(3) }

        imageTadpole.setOnClickListener() {
            viewModel.getQuestion()
        }
    }
}