package com.example.cats

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private val allMeows = listOf(
        R.raw.meow1, R.raw.meow2, R.raw.meow3, R.raw.meow4, R.raw.meow5
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstText: TextView = findViewById(R.id.textView1)
        val catButton1: ImageView = findViewById(R.id.imageCat1)
        val catButton2: ImageView = findViewById(R.id.imageCat2)
        val catButton3: ImageView = findViewById(R.id.imageCat3)
        val catButton4: ImageView = findViewById(R.id.imageCat4)
        val catButton5: ImageView = findViewById(R.id.imageCat5)
        val catButton6: ImageView = findViewById(R.id.imageCat6)

        val phrases = listOf(
            "Даже если упал – приземлись на лапы и иди дальше!",
            "Спи, ешь, достигай – идеальный график.",
            "Неважно, сколько раз тебя игнорируют – мурчи себе дальше.",
            "Главное – уверенность. Даже если застрял в коробке.",
            "Хочешь поймать успех? Будь хитрым, как мышь, и быстрым, как кот!",
            "Иногда нужно просто потянуться и взять своё.",
            "Если не влезаешь – влезай по-кошачьи: с шумом и хаосом!",
            "Не бойся падать с высоты – бойся не попробовать запрыгнуть.",
            "Успех – это когда тебя гладят, а не гоняют шваброй.",
            "Мир твой, если ты в нём – самый пушистый!"
        )

        // Общий обработчик для всех кнопок с анимацией
        val clickListener = { view: View ->
            // 1. Активируем встроенный ripple-эффект (если он задан через background)
            view.isPressed = true

            // 2. Анимация масштабирования
            view.animate()
                .scaleX(0.95f)
                .scaleY(0.95f)
                .setDuration(100)
                .withEndAction {
                    // Возвращаем нормальный размер
                    view.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(100)
                        .start()
                }

            // 3. Воспроизводим случайный звук
            playRandomMeowSound()

            // 4. Меняем текст
            firstText.text = phrases.random()
        }

// Назначаем обработчик на все кнопки
        listOf(catButton1, catButton2, catButton3, catButton4, catButton5, catButton6).forEach { button ->
            button.setOnClickListener(clickListener)
        }

    }

    private fun playRandomMeowSound() {
        val randomSound = allMeows.random()
        MediaPlayer.create(this, randomSound).apply {
            start()
            setOnCompletionListener { release() } // Освобождаем ресурсы после проигрывания
        }
    }
}
