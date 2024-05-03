package com.starostin.rockscissorspaper

import android.os.Bundle
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
            else -> throw IllegalArgumentException("Invalid choice")
        }
        userChoice.setImageResource(userChoiceImage)

        val computerChoiceId = Random.nextInt(5)
        val computerChoiceImageRes = when (computerChoiceId) {
            0 -> R.drawable.rock
            1 -> R.drawable.paper
            2 -> R.drawable.scissors
            3 -> R.drawable.lizard
            4 -> R.drawable.spock
            else -> throw IllegalStateException("Invalid random choice")
        }
        computerChoice.setImageResource(computerChoiceImageRes)

        val result = determineWinner(userChoiceId, computerChoiceId)
        resultText.text = result
    }

    private fun determineWinner(userChoice: Int, computerChoice: Int): String {
        if (userChoice == computerChoice) {
            return "It's a draw!"
        }

        return when (userChoice) {
            R.id.rock_button -> if (computerChoice == 2 || computerChoice == 3) "You win!" else if (computerChoice == 1 || computerChoice == 4) "Computer wins!" else "It's a draw!"
            R.id.paper_button -> if (computerChoice == 0 || computerChoice == 4) "You win!" else if (computerChoice == 3) "Computer wins!" else "It's a draw!"
            R.id.scissors_button -> if (computerChoice == 1 || computerChoice == 3) "You win!" else if (computerChoice == 4 || computerChoice == 0) "Computer wins!" else "It's a draw!"
            R.id.lizard_button -> if (computerChoice == 4 || computerChoice == 1) "You win!" else if (computerChoice == 0 || computerChoice == 2) "Computer wins!" else "It's a draw!"
            R.id.spock_button -> if (computerChoice == 0 || computerChoice == 2) "You win!" else if (computerChoice == 1 || computerChoice == 3) "Computer wins!" else "It's a draw!"
            else -> throw IllegalArgumentException("Invalid choice")
        }
    }
}
