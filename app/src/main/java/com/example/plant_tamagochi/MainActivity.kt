package com.example.plant_tamagochi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.example.animal_tamagochi.R
import com.example.plant_tamagochi.models.AnimalParams
import com.example.plant_tamagochi.models.InventoryItem


class MainActivity : ComponentActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n")

    private val myAnimal = AnimalParams("Animal", 100, 50, 50)
    private val inventory = mutableListOf<InventoryItem>()

    private lateinit var textViewAnimalInfo: TextView
    private lateinit var textViewInventoryInfo: TextView

    private fun getAnimalInfo(): String {
        val animalInfo = "Name: ${myAnimal.name} \n" +
                "Mood: ${myAnimal.mood} \n" +
                "Hungry: ${myAnimal.hungry} \n"
        return animalInfo
    }

    private fun getInventoryInfo(): String {
        val info = StringBuilder()
        info.append("Inventory:\n")
        for (item in inventory) {
            info.append("${item.name}, quantity: ${item.quantity}\n")
        }
        return info.toString()
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        inventory.add(InventoryItem(1, "Test", 1))

        textViewAnimalInfo = findViewById(R.id.animal_params)
        textViewInventoryInfo = findViewById(R.id.inventory_params)
        textViewInventoryInfo.movementMethod =
            android.text.method.ScrollingMovementMethod.getInstance()
        val buttonWalk = findViewById<Button>(R.id.button_walk)
        val buttonHungry = findViewById<Button>(R.id.button_hungry)
        val buttonGame = findViewById<Button>(R.id.button_play)

        textViewAnimalInfo.text = getAnimalInfo()
        textViewInventoryInfo.text = getInventoryInfo()

        buttonWalk.setOnClickListener() {
            myAnimal.mood += 5
            if (myAnimal.mood >= 100) {
                myAnimal.mood = 100
            }
            textViewAnimalInfo.text = getAnimalInfo()
            textViewInventoryInfo.text = getInventoryInfo()
        }

        buttonGame.setOnClickListener() {
            myAnimal.hungry -= 5
            myAnimal.mood += 15
            if (myAnimal.mood >= 100) {
                myAnimal.mood = 100
            }
            if (myAnimal.hungry <= 0) {
                myAnimal.mood = 0
            }
            val intent = Intent(this, WorkActivity::class.java)
            startActivity(intent)
            textViewAnimalInfo.text = getAnimalInfo()
            textViewInventoryInfo.text = getInventoryInfo()
        }

        buttonHungry.setOnClickListener() {
            myAnimal.hungry += 10
            textViewAnimalInfo.text = getAnimalInfo()
            if (myAnimal.hungry >= 100) {
                myAnimal.hungry = 100
            }
        }

        updateUI()
        Log.e("AAA", inventory.toString())


    }

    override fun onResume() {
        super.onResume()

        val newItem = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("newInventoryItem", InventoryItem::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("newInventoryItem") as? InventoryItem
        }

        newItem?.let {
            inventory.add(it)
            updateUI()
        }
    }

    private fun updateUI() {
        textViewAnimalInfo.text = getAnimalInfo()
        textViewInventoryInfo.text = getInventoryInfo()

    }
}