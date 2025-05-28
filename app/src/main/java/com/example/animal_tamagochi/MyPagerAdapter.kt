package com.example.animal_tamagochi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.animal_tamagochi.models.InfoCardDataClass

class MyPagerAdapter(private val item: List<InfoCardDataClass>) :
    RecyclerView.Adapter<MyPagerAdapter.CardViewHolder>() {

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.cardInfoImageView)
        val titleText: TextView = itemView.findViewById(R.id.cardInfoTitleText)
        val descriptionText: TextView = itemView.findViewById(R.id.cardInfoDescriptionText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_info_card, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = item[position]
        holder.imageView.setImageResource(card.imageResId)
        holder.titleText.text = card.title
        holder.descriptionText.text = card.description
    }

    override fun getItemCount(): Int = item.size


}