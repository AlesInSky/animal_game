package com.example.animal_tamagochi.presentation.thirdchapter
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.animal_tamagochi.R
import com.example.animal_tamagochi.domain.model.Card

class CardAdapter(
    private var cards: List<Card>,
    private val onCardClick: (Int) -> Unit
) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    inner class CardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardImage: ImageView = view.findViewById(R.id.card_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_play_card, parent, false)
        return CardViewHolder(view)
    }

    override fun getItemCount(): Int = cards.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = cards[position]

        if (card.isFlipped) {
            holder.cardImage.setImageResource(card.image)
        } else {
            holder.cardImage.setImageResource(R.drawable.cover_card)
        }

        holder.cardImage.setOnClickListener {
            onCardClick(position)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCards(newCards: List<Card>) {
        cards = newCards.toMutableList()
        notifyDataSetChanged()
    }


}