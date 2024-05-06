package com.example.tugasbutton

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnNumber1 = findViewById<Button>(R.id.btnNumber1)
        val btnNumber2 = findViewById<Button>(R.id.btnNumber2)
        val tvScore = findViewById<TextView>(R.id.tvScore)
        val tvMessage = findViewById<TextView>(R.id.tvMessage)

        playRound(tvScore, tvMessage)

        btnNumber1.setOnClickListener {
            checkAnswer(btnNumber1.text.toString().toInt(), btnNumber2.text.toString().toInt(), tvScore, tvMessage)
        }

        btnNumber2.setOnClickListener {
            checkAnswer(btnNumber2.text.toString().toInt(), btnNumber1.text.toString().toInt(), tvScore, tvMessage)
        }

        findViewById<Button>(R.id.btnReset).setOnClickListener {
            score = 0
            tvScore.text = "Points: $score"
            tvMessage.text = ""
            playRound(tvScore, tvMessage)
        }
    }

    private fun playRound(tvScore: TextView, tvMessage: TextView) {
        val leftVal = randomNumber()
        val rightVal = randomNumber()

        findViewById<Button>(R.id.btnNumber1).text = leftVal.toString()
        findViewById<Button>(R.id.btnNumber2).text = rightVal.toString()

        if (score == 100) {
            tvMessage.text = "Congratulations! You've reached 100 points!"
            score = 0
        }
    }

    private fun randomNumber(): Int {
        return Random.nextInt(1, 101)
    }

    private fun checkAnswer(selectedNumber: Int, otherNumber: Int, tvScore: TextView, tvMessage: TextView) {
        if (selectedNumber > otherNumber) {
            score += 10
            if (score > 100) {
                score = 100
            }
            tvScore.text = "Points: $score"
            tvMessage.text = ""
        } else {
            score = 0
            tvScore.text = "Points: $score"
            tvMessage.text = "Wrong!"
        }
        playRound(tvScore, tvMessage)
    }
}