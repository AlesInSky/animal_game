package com.example.animal_tamagochi

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
        val viewPager2 = findViewById<ViewPager2>(R.id.horizontalRecyclerView)

        val cards = listOf(
            InfoCard(R.drawable.ic_launcher_foreground,"Сюжет", "Прохождение кампании"),
            InfoCard(R.drawable.ic_launcher_foreground,"Дневник", "Записи о прошедших событиях"),
            InfoCard(R.drawable.ic_launcher_foreground,"Бестиарий", "Персонажи, встречающиеся вам на пути")
        )
        viewPager2.adapter = MyPagerAdapter(cards)
        }
    }




