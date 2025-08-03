package com.example.animal_tamagochi.presentation.secondchapter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.animal_tamagochi.domain.usecase.GetFlyUseCase

class SecondChapterViewModelFactory(
    private val getFlyUseCase: GetFlyUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SecondChapterViewModel::class.java)) {
            return SecondChapterViewModel(getFlyUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}