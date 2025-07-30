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
        Characters(5, R.drawable.bad_guy_no_background, "Club Lampless", "Description"),
        Characters(6, R.drawable.heron_no_background, "Heron", "Description"),
        Characters(7, 0, "Narrator", "Unknown"),
        Characters(8, R.drawable.king_no_background, "King", "Unknown"),
        Characters(9, R.drawable.bad_froggy_head_no_background, "Bad Froggy", "Unknown"),
        Characters(10, R.drawable.bad_froggy_advisor_no_background, "Bad Froggy advisor", "Unknown"),
        Characters(11, R.drawable.good_froggy_advisor_no_background, "Good Froggy advisor", "Unknown")
    )
}