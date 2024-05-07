package com.starostin.rockscissorspaper

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var userChoice: ImageView
    private lateinit var computerChoice: ImageView
    private lateinit var resultText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userChoice = findViewById(R.id.user_choice)
        computerChoice = findViewById(R.id.computer_choice)
        resultText = findViewById(R.id.result_text)
    }

    fun onUserChoice(view: View) {
        val userChoiceId = view.id
        val userChoiceImage = when (userChoiceId) {
            R.id.rock_button -> R.drawable.rock
            R.id.paper_button -> R.drawable.paper
            R.id.scissors_button -> R.drawable.scissors
            R.id.lizard_button -> R.drawable.lizard
            R.id.spock_button -> R.drawable.spock
            else -> throw IllegalArgumentException("Некорректный выбор")
        }
        userChoice.setImageResource(userChoiceImage)

        val computerChoiceId = Random.nextInt(5)
        val computerChoiceImageRes = when (computerChoiceId) {
            0 -> R.drawable.rock
            1 -> R.drawable.paper
            2 -> R.drawable.scissors
            3 -> R.drawable.lizard
            4 -> R.drawable.spock
            else -> throw IllegalStateException("Некорректный случайный выбор")
        }
        computerChoice.setImageResource(computerChoiceImageRes)

        val result = determineWinner(userChoiceId, computerChoiceId)
        resultText.text = result
    }

    private fun determineWinner(userChoice: Int, computerChoice: Int): SpannableString {
        val result = when (userChoice) {
            R.id.rock_button -> if (computerChoice == 2 || computerChoice == 3) "Вы выиграли!" else if (computerChoice == 1 || computerChoice == 4) "Компьютер выиграл!" else "Ничья!"
            R.id.paper_button -> if (computerChoice == 0 || computerChoice == 4) "Вы выиграли!" else if (computerChoice == 3 || computerChoice == 2) "Компьютер выиграл!" else "Ничья!"
            R.id.scissors_button -> if (computerChoice == 1 || computerChoice == 3) "Вы выиграли!" else if (computerChoice == 4 || computerChoice == 0) "Компьютер выиграл!" else "Ничья!"
            R.id.lizard_button -> if (computerChoice == 4 || computerChoice == 1) "Вы выиграли!" else if (computerChoice == 0 || computerChoice == 2) "Компьютер выиграл!" else "Ничья!"
            R.id.spock_button -> if (computerChoice == 0 || computerChoice == 2) "Вы выиграли!" else if (computerChoice == 1 || computerChoice == 3) "Компьютер выиграл!" else "Ничья!"
            else -> throw IllegalArgumentException("Некорректный выбор")
        }

        val spannableResult = SpannableString(result)

        val color = when (result) {
            "Вы выиграли!" -> Color.GREEN
            "Компьютер выиграл!" -> Color.RED
            else -> Color.BLACK
        }

        spannableResult.setSpan(ForegroundColorSpan(color), 0, result.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        return spannableResult
    }
}
