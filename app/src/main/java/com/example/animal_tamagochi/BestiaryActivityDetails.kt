package com.example.animal_tamagochi

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animal_tamagochi.models.Characters
import com.example.animal_tamagochi.recycler_view.BestiaryDetailsRecyclerView

class BestiaryActivityDetails : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bestiary_details)

        val character = intent.getParcelableExtra("character", Characters::class.java)

        character?.let {
            val recycler = findViewById<RecyclerView>(R.id.bestiaryDetailsRecyclerView)
            recycler.layoutManager = LinearLayoutManager(this)
            recycler.adapter = BestiaryDetailsRecyclerView(listOf(it))
        }


    }

}