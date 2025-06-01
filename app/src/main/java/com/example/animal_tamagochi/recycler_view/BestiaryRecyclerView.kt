package com.example.animal_tamagochi.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.animal_tamagochi.R
import com.example.animal_tamagochi.models.Characters
import com.example.animal_tamagochi.models.InfoCard

class BestiaryRecyclerView(
    private val item: List<Characters>,
    private val onItemClick: (Characters) -> Unit,
) :
    RecyclerView.Adapter<BestiaryRecyclerView.CardViewHolder>() {

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idCharacter: TextView = itemView.findViewById(R.id.number_character)
        val titleCharacter: TextView = itemView.findViewById(R.id.title_character)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_bestiary, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = item[position]
        holder.idCharacter.text = card.id.toString()
        holder.titleCharacter.text = card.chapterTitle

        holder.itemView.setOnClickListener {
            onItemClick(card)
        }
    }

    override fun getItemCount(): Int = item.size


}