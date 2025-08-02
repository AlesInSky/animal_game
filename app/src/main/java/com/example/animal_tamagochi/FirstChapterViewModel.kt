package com.example.animal_tamagochi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FirstChapterViewModel : ViewModel() {
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

    fun getQuestion() {
        val text = when (counter) {
            0 -> "Головастик ${randomNumber[0]}: «Я видел, как кто-то крался к королевской кувшинке вчера вечером.»\n\n" +
                    "Головастик ${randomNumber[1]}: «Я был с Головастиком ${randomNumber[2]} весь вечер, мы ловили комаров.»\n\n" +
                    "Головастик ${randomNumber[2]}: «Это неправда! Я весь вечер прятался от совы один.»"

            1 -> "Головастик ${randomNumber[0]}: «Я слышал хлопок крыльев. Наверное, это была цапля.»\n\n" +
                    "Головастик ${randomNumber[1]}: «Нет, в это время шёл дождь, ничего не было слышно!»\n\n" +
                    "Головастик ${randomNumber[2]}: «Дождь пошёл только поздно ночью, я тогда уже спал.»"

            2 -> "Головастик ${randomNumber[0]}: «Я нашёл улики — сломанные ветки около логова ужа.»\n\n" +
                    "Головастик ${randomNumber[1]}: «Это я случайно сломал, когда прятался.»\n\n" +
                    "Головастик ${randomNumber[2]}: «Ты в это время спал в своей норе, я проходил мимо и слышал, как ты храпишь.»"

            else -> "Unknown error"
        }
        _dialogueText.value = text
        _buttonVisibility.value = true
    }

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

    fun clearToast() {
        _toastMessage.value = null
    }

}