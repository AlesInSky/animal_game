package com.example.animal_tamagochi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animal_tamagochi.models.Chapters

class ChapterChange : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chapter_change)

        val recycler = findViewById<RecyclerView>(R.id.chapterRecyclerView)

        val chapter = listOf(
            Chapters(1,"Intro","Начало хорошей истории"),
            Chapters(2,"Run","Description"),
            Chapters(3,"No lighters","Description"),
            Chapters(4,"Unknown chapter","Description"),
            Chapters(5,"Grand finale","Description")
        )

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = ChapterChangeRecyclerView(chapter)



    }
}