package com.example.animal_tamagochi.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.animal_tamagochi.R
import com.example.animal_tamagochi.models.Chapters

class ChapterChangeRecyclerView(private val item: List<Chapters>) :
    RecyclerView.Adapter<ChapterChangeRecyclerView.CardViewHolder>() {

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val numberChapter: TextView = itemView.findViewById(R.id.numberChapter)
        val titleChapter: TextView = itemView.findViewById(R.id.titleChapter)
        val descriptionChapter: TextView = itemView.findViewById(R.id.descriptionChapter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chapter, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = item[position]
        holder.numberChapter.text = card.number.toString()
        holder.titleChapter.text = card.chapterTitle
        holder.descriptionChapter.text = card.chapterDescription
    }

    override fun getItemCount(): Int = item.size


}