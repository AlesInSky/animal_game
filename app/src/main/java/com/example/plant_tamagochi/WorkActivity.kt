package com.example.plant_tamagochi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.example.animal_tamagochi.R
import com.example.plant_tamagochi.models.InventoryItem

class WorkActivity : ComponentActivity() {
    private var example = 0
    private var correctDecision = 0
    private lateinit var currentTaskAnswer: String

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_work)

        val textViewCalculation = findViewById<TextView>(R.id.textViewCalculation)
        val editTextAnswer = findViewById<EditText>(R.id.editTextAnswer)
        val buttonAnswer = findViewById<Button>(R.id.buttonAnswer)

        fun getTask(): String {
            val list: List<Int> = (1..10).toList()
            val a = list.random()
            val b = list.random()
            textViewCalculation.text = "$a + $b = ?"
            val x = a + b
            return x.toString()
        }

        currentTaskAnswer = getTask()

        fun answer() {
            val editAnswer = editTextAnswer.text.toString()

            if (editAnswer == currentTaskAnswer) {
                correctDecision += 1
            }

            example += 1

            if (example == 1) {
                if (correctDecision >= 1) {
                    val newItem = InventoryItem(1, "Food", 1)
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("newInventoryItem", newItem)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Incorrect decision", Toast.LENGTH_SHORT).show()
                    finish()
                }
            } else {
                editTextAnswer.text.clear()
                currentTaskAnswer = getTask()
            }
        }

        buttonAnswer.setOnClickListener {
            answer()
        }
    }
}