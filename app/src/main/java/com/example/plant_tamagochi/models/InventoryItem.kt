package com.example.plant_tamagochi.models

import java.io.Serializable


data class InventoryItem(
    val id: Int,
    val name: String,
    val quantity: Int
) : Serializable
