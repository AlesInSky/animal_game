package com.example.animal_tamagochi

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.animal_tamagochi.models.DialogueManager

class DialogueActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dialogue)

        val sendMessage = findViewById<TextView>(R.id.dialogue_text)
        val imageMessage = findViewById<ImageView>(R.id.dialogue_image)
        val chapter = intent.getIntExtra("CHAPTER_KEY", 1)
        val dialogue = DialogueManager.getDialogue(chapter)
        var counter = 0

        if (dialogue.isNotEmpty()) {
            val firstLine = dialogue[counter]
            sendMessage.text = firstLine.text
        }

        imageMessage.setOnClickListener() {
            counter++
            val firstLine = dialogue[counter]
            sendMessage.text = firstLine.text
            imageMessage.setImageResource(firstLine.character.image)
        }
    }
}