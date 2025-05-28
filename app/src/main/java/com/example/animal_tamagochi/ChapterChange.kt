package com.example.animal_tamagochi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animal_tamagochi.models.ChaptersDataClass

class ChapterChange : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chapter_change)

        val recycler = findViewById<RecyclerView>(R.id.chapterRecyclerView)

        val chapter = listOf(
            ChaptersDataClass(1,"Intro","Начало хорошей истории"),
            ChaptersDataClass(2,"Run","Description"),
            ChaptersDataClass(3,"No lighters","Description"),
            ChaptersDataClass(4,"Unknown chapter","Description"),
            ChaptersDataClass(5,"Grand finale","Description")
        )

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = ChapterChangeRecyclerView(chapter)



    }
}