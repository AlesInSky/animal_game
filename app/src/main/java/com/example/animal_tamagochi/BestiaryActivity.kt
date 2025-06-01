package com.example.animal_tamagochi

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animal_tamagochi.models.Characters
import com.example.animal_tamagochi.recycler_view.BestiaryRecyclerView

class BestiaryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bestiary)

        val recycler = findViewById<RecyclerView>(R.id.bestiaryRecyclerView)

        val character = listOf(
            Characters(1, R.drawable.froggy_no_background, "Froggy", "Description"),
            Characters(2, R.drawable.ropuhu_no_background, "Ropuhu", "Description"),
            Characters(3, R.drawable.headers_no_background, "Tadpole", "Description"),
            Characters(4, R.drawable.slug_no_background, "The Informant is a Slug", "Description"),
            Characters(5, R.drawable.ic_launcher_foreground, "Club Lampless", "Description"),
            Characters(6, R.drawable.heron_no_background, "Heron", "Description"),
        )

        val adapter = BestiaryRecyclerView(character) {selectedItem ->
            val intent = Intent(this,BestiaryActivityDetails::class.java).apply {
                putExtra("character", selectedItem)
            }
            startActivity(intent)
        }

        recycler.layoutManager = GridLayoutManager(this,2)
        recycler.adapter = adapter

    }
}