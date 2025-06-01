package com.example.animal_tamagochi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animal_tamagochi.models.Chapters
import com.example.animal_tamagochi.models.DiaryNote
import com.example.animal_tamagochi.recycler_view.ChapterChangeRecyclerView
import com.example.animal_tamagochi.recycler_view.DiaryRecyclerView

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