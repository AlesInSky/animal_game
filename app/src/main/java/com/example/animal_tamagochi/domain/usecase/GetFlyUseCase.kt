package com.example.animal_tamagochi.domain.usecase

import android.content.res.Resources
import com.example.animal_tamagochi.R
import com.example.animal_tamagochi.domain.model.GetFlyProperties
import kotlin.random.Random

class GetFlyUseCase {
    operator fun invoke(): GetFlyProperties{

            val displayMetrics = Resources.getSystem().displayMetrics
            val screenWidth = displayMetrics.widthPixels
            val screenHeight = displayMetrics.heightPixels

            val randomX = Random.nextInt(0, screenWidth - 150)
            val randomY = Random.nextInt(0, screenHeight - 150)

            return GetFlyProperties(
                x = randomX.toFloat(),
                y = randomY.toFloat(),
                imageResId = R.drawable.fly_no_background
            )
        }
    }
