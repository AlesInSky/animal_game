package com.example.animal_tamagochi.presentation.firstchapter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.animal_tamagochi.domain.usecase.GetQuestionUseCase

class FirstChapterViewModelFactory(
    private val getQuestionUseCase: GetQuestionUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FirstChapterViewModel::class.java)) {
            return FirstChapterViewModel(getQuestionUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}