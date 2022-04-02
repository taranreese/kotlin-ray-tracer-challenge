package com.taranreese.raytracerchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.taranreese.raytracerchallenge.exercises.Environment
import com.taranreese.raytracerchallenge.exercises.Projectile
import com.taranreese.raytracerchallenge.exercises.tick
import com.taranreese.raytracerchallenge.model.Point
import com.taranreese.raytracerchallenge.model.Vector

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var p = Projectile(Point(0.0, 1.0, 0.0),
            Vector(1.0, 1.0, 0.0).normalize())

        val e = Environment(
            Vector(0.0, -0.1, 0.0),
            Vector(-0.01, 0.0, 0.0)
        )

        while(p.position.y >= 0.0) {
            p = tick(e, p)
            Log.d(p.position.toString(), p.velocity.toString())
        }
    }
}