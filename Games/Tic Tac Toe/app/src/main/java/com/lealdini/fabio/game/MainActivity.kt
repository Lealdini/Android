package com.lealdini.fabio.game

import android.content.Context
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var activePlayer: Int = 0
    var gameState = arrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
    private var winningPositions = arrayOf(
        arrayOf(0, 1, 2),
        arrayOf(3, 4, 5),
        arrayOf(6, 7, 8),
        arrayOf(0, 3, 6),
        arrayOf(1, 4, 7),
        arrayOf(2, 5, 8),
        arrayOf(0, 4, 8),
        arrayOf(2, 4, 6)
    )
    var gameActive: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun dropIn(view: View) {
        val counter: ImageView = view as ImageView
        val tappedCounter: Int = counter.tag.toString().toInt()

        if (gameState[tappedCounter] == 2 && gameActive) {

            gameState[tappedCounter] = activePlayer
            counter.translationY = (-1500).toFloat()
            activePlayer = if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow)
                1
            } else {
                counter.setImageResource(R.drawable.red)
                0
            }
            counter.animate().translationYBy(1500F).duration = 300

            for (i: Array<Int> in winningPositions) {
                if (gameState[i[0]] == gameState[i[1]] && gameState[i[1]] == gameState[i[2]] && gameState[i[0]] != 2) {

                    gameActive = false

                    val winner: String = if (activePlayer == 1) {
                        "Yellow"
                    } else {
                        "Red"
                    }
                    Toast.makeText(this, "$winner has won", Toast.LENGTH_SHORT).show()
                    winnerTextView.text = "$winner has won"
                    winnerLayout.visibility = View.VISIBLE
                }
            }
        }
    }

    fun playAgain(view: View) {
        winnerLayout.visibility = View.INVISIBLE
        for (i in 0 until gridLayout.childCount) run {
            var counter: ImageView = gridLayout.getChildAt(i) as ImageView
            counter.setImageDrawable(null)
        }
        gameState = arrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
        activePlayer = 0
        gameActive = true
    }
}
