package com.example.animal_tamagochi

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image

class ThirdChapter : ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_third_chapter)
        val gridLayout = findViewById<GridLayout>(R.id.third_chapter)

        fun getGrid(level: Int, rows: Int, columns: Int) {
            for (row in 0 until rows) {
                for (col in 0 until columns) {
                    val useTriple = (0..1).random() == 1

                    val imageView = ImageView(this).apply {
                        id = View.generateViewId()
                        val rand = (0..2).random()
                        val tagValue = when(rand){
                            0 -> "triple"
                            1 -> "default"
                            2 -> "turn"
                            else -> "unknown"
                        }

                        val imageRes = when(tagValue){
                            "triple" -> R.drawable.plum_default_triple
                            "default" -> R.drawable.plum_default_horizontal
                            "turn" -> R.drawable.plum_default_turn
                            else -> R.drawable.plum_default_turn
                        }

                        tag = tagValue
                        setImageResource(imageRes)
                        layoutParams = GridLayout.LayoutParams().apply {
                            width = 200
                            height = 200
                            rowSpec = GridLayout.spec(row)
                            columnSpec = GridLayout.spec(col)
                        }
                        scaleType = ImageView.ScaleType.CENTER_CROP

                        val possibleAngles = when (tagValue) {
                            "default" -> listOf(0, 90)           // две позиции
                            "triple", "turn" -> listOf(0, 90, 180, 270)  // четыре позиции
                            else -> listOf(0)
                        }
                        var currentRotation = possibleAngles.random()
                        rotation = currentRotation.toFloat()

                        var currentCounter = when (tagValue) {
                            "default" -> if (currentRotation == 0) 0 else 1
                            else -> 0
                        }

                        setOnClickListener {
                            when (tag) {
                                "default" -> {
                                    currentCounter = (currentCounter + 1) % 2
                                    currentRotation = if (currentCounter == 0) 0 else 90
                                }
                                "triple" -> {
                                    currentRotation = (currentRotation + 90) % 360
                                }
                                "turn" -> {
                                    // Своя логика поворота для special, например:
                                    currentRotation = (currentRotation + 90) % 360
                                }
                            }
                            rotation = currentRotation.toFloat()
                        }
                    }
                    gridLayout.addView(imageView)
                }
            }
        }

        getGrid(1, 5, 5)





}
}