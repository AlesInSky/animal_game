package com.example.animal_tamagochi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge

class FirstChapter : ComponentActivity() {
    @SuppressLint("MissingInflatedId", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_first_chapter)
        val dialogueText = findViewById<TextView>(R.id.dialogue_text)
        val firstTadpoleButton = findViewById<Button>(R.id.tadpole_first)
        val secondTadpoleButton = findViewById<Button>(R.id.tadpole_second)
        val thirstTadpoleButton = findViewById<Button>(R.id.tadpole_thirst)
        val imageTadpole = findViewById<ImageView>(R.id.tadpole_image)
        var randomNumber = listOf(1, 2, 3)

        Log.e("AAA", randomNumber.toString())
        var counter = 0


        @SuppressLint("SetTextI18n")
        fun getQuestion() {
            randomNumber = randomNumber.shuffled()
            return when (counter) {
                0 -> dialogueText.text =
                    "Головастик ${randomNumber[0]}: «Я видел, как кто-то крался к королевской кувшинке вчера вечером.»\n\n" +
                            "Головастик ${randomNumber[1]}: «Я был с Головастиком ${randomNumber[2]} весь вечер, мы ловили комаров.»\n\n" +
                            "Головастик ${randomNumber[2]}: «Это неправда! Я весь вечер прятался от совы один.»"

                1 -> dialogueText.text =
                    "Головастик ${randomNumber[0]}: «Я слышал хлопок крыльев. Наверное, это была цапля.»\n\n" +
                            "Головастик ${randomNumber[1]}: «Нет, в это время шёл дождь, ничего не было слышно!»\n\n" +
                            "Головастик ${randomNumber[2]}: «Дождь пошёл только поздно ночью, я тогда уже спал.»"

                2 -> dialogueText.text =
                    "Головастик ${randomNumber[0]}: «Я нашёл улики — сломанные ветки около логова ужа.»\n\n" +
                            "Головастик ${randomNumber[1]}: «Это я случайно сломал, когда прятался.»\n\n" +
                            "Головастик ${randomNumber[2]}: «Ты в это время спал в своей норе, я проходил мимо и слышал, как ты храпишь.»"

                else -> {
                    dialogueText.text = "Unknown error"
                }
            }
        }

        firstTadpoleButton.setOnClickListener() {
            if (randomNumber[1] == 1) {
                Toast.makeText(this, "Да! Головастик врёт !", Toast.LENGTH_LONG).show()
                counter++
                firstTadpoleButton.visibility = View.INVISIBLE
                secondTadpoleButton.visibility = View.INVISIBLE
                thirstTadpoleButton.visibility = View.INVISIBLE
                dialogueText.text =
                    "Головастик попался на лжи ! \n Идем дальше ! \n\n (Для продолжения нажмите на головастиков)"
            } else {Toast.makeText(this, "Попробуй еще раз!", Toast.LENGTH_LONG).show()
                firstTadpoleButton.visibility = View.INVISIBLE}
        }
        secondTadpoleButton.setOnClickListener() {
            if (randomNumber[1] == 2) {
                Toast.makeText(this, "Да! Головастик врёт !", Toast.LENGTH_LONG).show()
                counter++
                firstTadpoleButton.visibility = View.INVISIBLE
                secondTadpoleButton.visibility = View.INVISIBLE
                thirstTadpoleButton.visibility = View.INVISIBLE
                dialogueText.text =
                    "Головастик попался на лжи ! \n Идем дальше ! \n\n (Для продолжения нажмите на головастиков)"
            } else {Toast.makeText(this, "Попробуй еще раз!", Toast.LENGTH_LONG).show()
                secondTadpoleButton.visibility = View.INVISIBLE}
        }
        thirstTadpoleButton.setOnClickListener() {
            if (randomNumber[1] == 3) {
                Toast.makeText(this, "Да! Головастик врёт !", Toast.LENGTH_LONG).show()
                counter++
                firstTadpoleButton.visibility = View.INVISIBLE
                secondTadpoleButton.visibility = View.INVISIBLE
                thirstTadpoleButton.visibility = View.INVISIBLE
                dialogueText.text =
                    "Головастик попался на лжи ! \n Идем дальше ! \n\n (Для продолжения нажмите на головастиков)"
            } else {Toast.makeText(this, "Попробуй еще раз!", Toast.LENGTH_LONG).show()
                thirstTadpoleButton.visibility = View.INVISIBLE}
        }

        imageTadpole.setOnClickListener() {
            if (counter == 3){
                val intent = Intent(this, DialogueActivity::class.java)
                intent.putExtra("CHAPTER_KEY",2)
                startActivity(intent)

            } else
            getQuestion()
            firstTadpoleButton.visibility = View.VISIBLE
            secondTadpoleButton.visibility = View.VISIBLE
            thirstTadpoleButton.visibility = View.VISIBLE
        }
    }


}