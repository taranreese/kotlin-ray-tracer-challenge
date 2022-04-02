package com.taranreese.raytracerchallenge.exercises

import android.content.Context
import com.taranreese.raytracerchallenge.model.Canvas
import com.taranreese.raytracerchallenge.model.Color
import com.taranreese.raytracerchallenge.model.Point
import com.taranreese.raytracerchallenge.model.Vector
import com.taranreese.raytracerchallenge.output.ExportPPM
import kotlin.math.round

fun runGame(velocityMultiplier: Double, context: Context) {
    // Projectile starts one unit above the origin
    // Velocity is normalized to 1 unit/tick
    var p = Projectile(Point(0.0,1.0,0.0),
        Vector(1.0,1.0,0.0) * velocityMultiplier)

    // Gravity -0.1 unit/tick and wind is -0.01 unit/tick
    val e = Environment(Vector(0.0,-0.1, 0.0),
        Vector(-0.01,0.0,0.0))

    var currentTick = 0
    val c = Canvas(900, 500)

    while (p.position.y > 0.0) {
        val xPos = String.format("%.2f", p.position.x)
        val yPos = String.format("%.2f", p.position.y)
        print("Tick: $currentTick - Projectile position: (${xPos}, ${yPos})")
        p = tick(e, p)

        val clampedX = clampValue((round(p.position.x)).toInt(), c.width - 1)
        val clampedY = clampValue(c.height - (round(p.position.y)).toInt(), c.height - 1)
        c.writePixel(clampedX, clampedY, Color(1.0, 0.0, 0.0))
        currentTick += 1
    }

    ExportPPM.savePPMFileToDocuments(c, "game", context)
}

fun clampValue(value: Int, max: Int): Int {
    if (value < 0) { return 0 }
    else if ( value > max) { return max }
    return value
}
