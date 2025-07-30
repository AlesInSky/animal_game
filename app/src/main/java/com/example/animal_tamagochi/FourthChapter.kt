package com.example.animal_tamagochi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts

class FourthChapter : ComponentActivity() {

    private var dialogueQueue = mutableListOf<Int>()
    private var choiceCounter = 0
    private var lastFirstLevelChoice = 0
    private var shouldFinishAfterDialogues = false
    private var isDialogueActive = false

    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btn4: Button

    private val dialogueLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        isDialogueActive = false
        if (dialogueQueue.isNotEmpty()) {
            startDialogue(dialogueQueue.removeAt(0))
        } else if (shouldFinishAfterDialogues) {
            finish()
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fourth_chapter)

        btn1 = findViewById(R.id.final_ask_button_one)
        btn2 = findViewById(R.id.final_ask_button_two)
        btn3 = findViewById(R.id.final_ask_button_three)
        btn4 = findViewById(R.id.question_four)

        btn1.setOnClickListener { handleChoice(411, 421) }
        btn2.setOnClickListener { handleChoice(412, 422) }
        btn3.setOnClickListener { handleChoice(413, 423) }
        btn4.setOnClickListener { handleChoice(414, 423) }

        updateButtonText()
        startDialogue(4) // начальный диалог
    }

    private fun startDialogue(chapterKey: Int) {
        isDialogueActive = true
        val intent = Intent(this, DialogueActivity::class.java)
        intent.putExtra("CHAPTER_KEY", chapterKey)
        dialogueLauncher.launch(intent)
    }

    private fun handleChoice(firstLevelKey: Int, secondLevelKey: Int) {
        choiceCounter++
        dialogueQueue.clear()

        when (choiceCounter) {
            1 -> {
                lastFirstLevelChoice = firstLevelKey
                dialogueQueue.add(firstLevelKey)
                updateButtonText()
            }

            2 -> {
                if (lastFirstLevelChoice == 422) {
                    dialogueQueue.addAll(listOf(422, 442))
                } else {
                    dialogueQueue.add(secondLevelKey)
                    val finalKey = when (secondLevelKey) {
                        421 -> 441
                        422 -> 442
                        423 -> 443
                        else -> error("Unknown choice")
                    }
                    dialogueQueue.add(finalKey)
                }
                shouldFinishAfterDialogues = true
            }
        }

        if (!isDialogueActive) {
            startDialogue(dialogueQueue.removeAt(0))
        }
    }

    private fun updateButtonText() {
        when (choiceCounter) {
            0 -> {
                btn1.text = "Это не свобода. Это диктатура под страхом."
                btn2.text = "Ты не спасал. Ты предавал"
                btn3.text = "Думаешь, тебя стоит простить ?"
                btn3.visibility = View.VISIBLE
                btn4.text = "Ты боишься потерять контроль."
            }

            1 -> {
                btn1.text = "Ты всё ещё можешь всё исправить. Сдайся."
                btn2.text = "Исчезни. Сейчас. Без следа."
                btn3.visibility = View.GONE
                btn4.text = "Никаких сделок. Ты идёшь на плаху — или в землю."
            }
        }
    }
}