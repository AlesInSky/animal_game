package com.example.animal_tamagochi.presentation.diary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animal_tamagochi.R
import com.example.animal_tamagochi.models.DiaryNote
import com.example.animal_tamagochi.presentation.common.adapter.DiaryRecyclerView

class DiaryView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_diary_view)
        val recycler = findViewById<RecyclerView>(R.id.diaryRecyclerView)

        val note = listOf(
            DiaryNote("Intro","Начало хорошей истории"),
            DiaryNote("Second","Center хорошей истории"),
        )

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = DiaryRecyclerView(note)
    }
}