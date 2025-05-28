package com.example.animal_tamagochi.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.animal_tamagochi.R
import com.example.animal_tamagochi.models.Characters

class BestiaryDetailsRecyclerView(private val item: List<Characters>): RecyclerView.Adapter<BestiaryDetailsRecyclerView.CardViewHolder>() {

    inner class CardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageCharacter: ImageView = itemView.findViewById(R.id.image_character_details)
        val titleCharacter: TextView = itemView.findViewById(R.id.title_character_details)
        val descriptionCharacter: TextView = itemView.findViewById(R.id.description_character_details)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bestiary_details, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CardViewHolder,
        position: Int
    ) {
        val card = item[position]
        holder.imageCharacter.setImageResource(R.drawable.ic_launcher_foreground)
        holder.titleCharacter.text = card.chapterTitle
        holder.descriptionCharacter.text = card.chapterDescription
    }

    override fun getItemCount(): Int = item.size

}