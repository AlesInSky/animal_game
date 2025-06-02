package com.example.animal_tamagochi.models

import android.os.Parcelable
import com.example.animal_tamagochi.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class Characters(
    val id: Int,
    val image: Int,
    val chapterTitle: String,
    val chapterDescription: String,
) : Parcelable


object CharacterManager {
    val character = listOf(
        Characters(1, R.drawable.froggy_no_background, "Froggy", "Description"),
        Characters(2, R.drawable.ropuhu_no_background, "Ropuhu", "Description"),
        Characters(3, R.drawable.headers_no_background, "Tadpole", "Description"),
        Characters(4, R.drawable.slug_no_background, "The Informant is a Slug", "Description"),
        Characters(5, R.drawable.ic_launcher_foreground, "Club Lampless", "Description"),
        Characters(6, R.drawable.heron_no_background, "Heron", "Description"),
        Characters(7,R.drawable.ic_launcher_foreground, "Narrator", "Unknown")
    )


}