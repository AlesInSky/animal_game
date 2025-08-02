package com.example.animal_tamagochi.domain.usecase

class GetQuestionUseCase {
    operator fun invoke(counter: Int, randomNumber: List<Int>): String {
        return when (counter) {
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
    }
}
