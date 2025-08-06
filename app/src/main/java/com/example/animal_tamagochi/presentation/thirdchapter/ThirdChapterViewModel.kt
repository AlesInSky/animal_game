package com.example.animal_tamagochi.presentation.thirdchapter

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.animal_tamagochi.R
import com.example.animal_tamagochi.domain.model.Card

class ThirdChapterViewModel: ViewModel() {

    private var _cards = MutableLiveData<MutableList<Card>>()
    var cards: LiveData<MutableList<Card>> = _cards

    private var _flippedCards = MutableLiveData<MutableList<Int>>(mutableListOf())

    private var _matchedCount = MutableLiveData(0)
    var matchedCount: LiveData<Int> = _matchedCount

    init {
        _cards.value = flippedCards()
    }


    private fun flippedCards(): MutableList<Card>{
        return mutableListOf(
            Card(false, R.drawable.card1),
            Card(false, R.drawable.card1),
            Card(false, R.drawable.card2),
            Card(false, R.drawable.card2),
            Card(false, R.drawable.card3),
            Card(false, R.drawable.card3),
            Card(false, R.drawable.card4),
            Card(false, R.drawable.card4),
            Card(false, R.drawable.card5),
            Card(false, R.drawable.card5),
            Card(false, R.drawable.card6),
            Card(false, R.drawable.card6)
        ).shuffled().toMutableList()
    }

    fun handleCardClick(index: Int) {
        val currentCards = _cards.value?.toMutableList() ?: return
        val flipped = _flippedCards.value?.toMutableList() ?: mutableListOf()

        if (currentCards[index].isFlipped || flipped.size == 2) return

        currentCards[index].isFlipped = true
        flipped.add(index)

        _cards.value = currentCards
        _flippedCards.value = flipped

        if (flipped.size == 2) {
            val first = flipped[0]
            val second = flipped[1]

            if (currentCards[first].image == currentCards[second].image) {
                _matchedCount.value = (_matchedCount.value ?: 0) + 2
                _flippedCards.value = mutableListOf()
            } else {
                Handler().postDelayed({
                    currentCards[first].isFlipped = false
                    currentCards[second].isFlipped = false
                    _cards.postValue(currentCards)
                    _flippedCards.postValue(mutableListOf())
                }, 650)
            }
        }
    }

}