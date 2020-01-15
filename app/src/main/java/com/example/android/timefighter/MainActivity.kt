package com.example.android.timefighter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.Timer
import java.util.TimerTask

class GameSecond(private val task: () -> Unit) :
    TimerTask() {

    override fun run() {
        task()
    }
}

class MainActivity : AppCompatActivity() {

    private var taps = 0
    private var count = 0
    private var best = 0
    private var gameOn = false
    private lateinit var timer: Timer
    private lateinit var timerText: TextView
    private lateinit var tapsText: TextView
    private lateinit var bestText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        timerText = findViewById(R.id.time_left)
        tapsText = findViewById(R.id.taps)
        bestText = findViewById(R.id.best)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            handleClick()
        }
    }

    private fun setReadoutText(textView: TextView, stringId: Int, readout: String) {
        textView.text = getString(stringId, readout)
    }

    private fun handleClick() {
        if (gameOn) {
            taps++
            setReadoutText(tapsText, R.string.taps, taps.toString())
        } else {
            gameOn = true
            count = 10
            setReadoutText(timerText, R.string.time_left, count.toString())
            timer = Timer()
            val task = GameSecond {
                count--
                setReadoutText(timerText, R.string.time_left, count.toString())
                if (count == 0) {
                    gameOn = false
                    best = if (taps > best) taps else best
                    setReadoutText(bestText, R.string.best, best.toString())
                    taps = 0
                    setReadoutText(tapsText, R.string.taps, taps.toString())
                    timer.cancel()
                }
            }
            setReadoutText(timerText, R.string.time_left, 10.toString())
            setReadoutText(tapsText, R.string.taps, 0.toString())
            timer.scheduleAtFixedRate(task, 1000, 1000)
        }
    }
}
