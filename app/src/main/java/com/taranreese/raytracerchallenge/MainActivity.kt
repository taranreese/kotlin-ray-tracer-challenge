package com.taranreese.raytracerchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.taranreese.raytracerchallenge.exercises.runGame

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Chapter 2
        runGame(5.5, this)
    }
}