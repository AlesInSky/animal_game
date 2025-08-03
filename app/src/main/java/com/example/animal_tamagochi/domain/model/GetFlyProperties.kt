package com.example.animal_tamagochi.domain.model

data class GetFlyProperties(
    val x: Float,
    val y: Float,
    val width: Int = 200,
    val height: Int = 200,
    val imageResId: Int
)
