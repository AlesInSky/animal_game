package com.example.animal_tamagochi

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge

class ThirdChapter : ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_third_chapter)
        val gridLayout = findViewById<GridLayout>(R.id.third_chapter)
        val rows = 5
        var level = 1

        fun getGrid() {

        }




    }
}