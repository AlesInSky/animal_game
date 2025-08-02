package com.example.animal_tamagochi.presentation.firstchapter

import com.example.animal_tamagochi.domain.usecase.GetQuestionUseCase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FirstChapterViewModel(private val getQuestionUseCase: GetQuestionUseCase) : ViewModel() {
    private val _dialogueText = MutableLiveData<String>()
    val dialogueText: LiveData<String> = _dialogueText

    private val _buttonVisibility = MutableLiveData(true)
    val buttonVisibility: LiveData<Boolean> = _buttonVisibility

    private val _toastMessage = MutableLiveData<String?>()
    val toastMessage = _toastMessage

    private val _chapterFinished = MutableLiveData(false)
    val chapterFinished = _chapterFinished

    private var counter = 0
    private var randomNumber = listOf(1, 2, 3)

    fun selectTadpole(number: Int) {
        if (randomNumber[1] == number) {
            _toastMessage.value = "Да! Головастик врёт !"
            counter++
            _buttonVisibility.value = false
            _dialogueText.value =
                "Головастик попался на лжи ! \n Идем дальше ! \n\n (Для продолжения нажмите на головастиков)"

            if (counter >= 3) {
                _chapterFinished.value = true
            }
        } else {
            _toastMessage.value = "Попробуй еще раз !"
        }
    }

    fun getQuestion() {
        randomNumber = randomNumber.shuffled()
        val text = getQuestionUseCase(counter, randomNumber)
        _dialogueText.value = text
        _buttonVisibility.value = true
    }

    fun clearToast() {
        _toastMessage.value = null
    }

}