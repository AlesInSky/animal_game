package com.example.animal_tamagochi.presentation.bestiary

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animal_tamagochi.R
import com.example.animal_tamagochi.domain.model.Characters
import com.example.animal_tamagochi.presentation.common.adapter.BestiaryDetailsRecyclerView

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