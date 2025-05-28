package com.example.animal_tamagochi

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animal_tamagochi.models.Characters

class BestiaryActivity : ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bestiary)

        val recycler = findViewById<RecyclerView>(R.id.bestiaryRecyclerView)

        val character = listOf(
            Characters(1, R.drawable.ic_launcher_foreground, "Froggy", "Description"),
            Characters(2, R.drawable.ic_launcher_foreground, "Froggy", "Description"),
            Characters(3, R.drawable.ic_launcher_foreground, "Froggy", "Description"),
        )

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = BestiaryRecyclerView(character)

    }
}