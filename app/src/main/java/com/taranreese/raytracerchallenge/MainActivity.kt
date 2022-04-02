package com.taranreese.raytracerchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.taranreese.raytracerchallenge.exercises.Environment
import com.taranreese.raytracerchallenge.exercises.Projectile
import com.taranreese.raytracerchallenge.exercises.runGame
import com.taranreese.raytracerchallenge.exercises.tick
import com.taranreese.raytracerchallenge.model.Point
import com.taranreese.raytracerchallenge.model.Vector

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Chapter 2
        runGame(5.5, this)
    }
}