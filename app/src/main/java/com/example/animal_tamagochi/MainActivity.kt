package com.example.animal_tamagochi

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.viewpager2.widget.ViewPager2
import com.example.animal_tamagochi.models.InfoCard


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val viewPager2 = findViewById<ViewPager2>(R.id.horizontalViewPager)

        val cards = listOf(
            InfoCard(R.drawable.main_quest,"Сюжет", "Прохождение кампании"),
            InfoCard(R.drawable.main_diary,"Дневник", "Записи о прошедших событиях"),
            InfoCard(R.drawable.main_bestiary,"Бестиарий", "Персонажи, встречающиеся вам на пути")
        )
        val adapter = MyPagerAdapter(cards) {selectedItem ->
            val intent = when (selectedItem.title) {
                "Сюжет" -> Intent(this, ChapterChange::class.java)
                "Дневник" -> Intent(this, DiaryView::class.java)
                "Бестиарий" -> Intent(this, BestiaryActivity::class.java)
                else -> null
            }
            intent?.let { startActivity(it) }
        }
        viewPager2.adapter = adapter
        }
    }




