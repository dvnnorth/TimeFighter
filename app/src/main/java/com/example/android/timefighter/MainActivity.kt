package com.example.android.timefighter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var clicks = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            handleClick()
        }
    }

    private fun handleClick() {
        val textOutput = findViewById<TextView>(R.id.clicks)
        clicks += 1
        textOutput.text = clicks.toString()
    }
}
