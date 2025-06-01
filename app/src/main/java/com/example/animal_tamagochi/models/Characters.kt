package com.example.animal_tamagochi.models
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Characters(
    val id: Int,
    val image: Int,
    val chapterTitle: String,
    val chapterDescription: String,
) : Parcelable