package com.example.animal_tamagochi

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animal_tamagochi.models.Chapters
import com.example.animal_tamagochi.recycler_view.ChapterChangeRecyclerView

class ChapterChange : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chapter_change)

        val recycler = findViewById<RecyclerView>(R.id.chapterRecyclerView)

        val chapter = listOf(
            Chapters(1, "Intro", "Начало хорошей истории"),
            Chapters(2, "Run", "Description"),
            Chapters(3, "No lighters", "Description"),
            Chapters(4, "Unknown chapter", "Description"),
            Chapters(5, "Grand finale", "Description")
        )

        val adapter = ChapterChangeRecyclerView(chapter) { selectedItem ->
            val intent = when (selectedItem.number) {
                1 -> Intent(this, FirstChapter::class.java)
                2 -> Intent(this, SecondChapter::class.java)
                3 -> Intent(this, FirstChapter::class.java)
                4 -> Intent(this, FirstChapter::class.java)
                5 -> Intent(this, FirstChapter::class.java)
                else -> null
            }
            intent?.putExtra("CHAPTER_KEY",selectedItem.number)
            intent?.let { startActivity(it) }
        }
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
    }
}