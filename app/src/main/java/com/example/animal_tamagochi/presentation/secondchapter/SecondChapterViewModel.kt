package com.example.animal_tamagochi.presentation.secondchapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.animal_tamagochi.domain.model.GetFlyProperties
import com.example.animal_tamagochi.domain.usecase.GetFlyUseCase

class SecondChapterViewModel(private val getFlyUseCase: GetFlyUseCase) : ViewModel() {

    private val _flyProperties = MutableLiveData<GetFlyProperties>()
    val flyProperties: LiveData<GetFlyProperties> = _flyProperties

    private val _counter = MutableLiveData<Int>()
    val counter: LiveData<Int> = _counter

    init {
        _counter.value = 0
    }

    fun incrementCounter() {
        _counter.value = (_counter.value ?: 0) + 1
    }

    fun resetCounter() {
        _counter.value = 0
    }

    fun getCurrentCounter(): Int {
        return _counter.value ?: 0
    }

    fun getFly() {
        val props = getFlyUseCase()
        _flyProperties.value = props
    }
}