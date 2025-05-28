package com.example.animal_tamagochi.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.animal_tamagochi.R
import com.example.animal_tamagochi.models.DiaryNote

class DiaryRecyclerView(private val item: List<DiaryNote>) :
    RecyclerView.Adapter<DiaryRecyclerView.CardViewHolder>() {

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleDiary: TextView = itemView.findViewById(R.id.title_diary)
        val descriptionDiary: TextView = itemView.findViewById(R.id.description_diary)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_diary, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CardViewHolder,
        position: Int,
    ) {
        val card = item[position]
        holder.titleDiary.text = card.title
        holder.descriptionDiary.text = card.description
    }

    override fun getItemCount(): Int = item.size

}