package com.example.animal_tamagochi

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.example.animal_tamagochi.models.DialogueManager

class DialogueActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dialogue)

        val sendMessage = findViewById<TextView>(R.id.dialogue_text)
        val imageMessage = findViewById<ImageView>(R.id.dialogue_image)
        val chapter = intent.getIntExtra("CHAPTER_KEY", 0)
        val dialogue = DialogueManager.getDialogue(chapter)
        var counter = 0

        if (dialogue.isNotEmpty()) {
            val firstLine = dialogue[counter]
            sendMessage.text = firstLine.text
        }

        imageMessage.setOnClickListener() {
            counter++
            if (counter >= dialogue.size) {
                finish()
            } else {
                val nextLine = dialogue[counter]
                sendMessage.text = nextLine.text
                imageMessage.setImageResource(nextLine.character.image)
            }

        }

    }
}