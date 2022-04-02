package com.taranreese.raytracerchallenge.model

import java.util.*
import kotlin.collections.ArrayList

data class Canvas(val width: Int, val height: Int) {
    val pixels = ArrayList(Collections.nCopies(width * height, Colors.BLACK.value))

    operator fun get(x: Int, y: Int): Color {
        return pixels[kotlin.math.abs(y * width + x)]
    }

    operator fun set(x: Int, y: Int, color: Color) {
        pixels[kotlin.math.abs(y * width + x)] = color
    }

    fun writePixel(x: Int, y: Int, color: Color) {
        var clippedX = x
        var clippedY = y

        if (clippedX >= width) {
            clippedX = width - 1
        } else if (clippedX < 0) {
            clippedX = 0
        }

        if (clippedY >= height) {
            clippedY = height - 1
        } else if (clippedY < 0) {
            clippedY = 0
        }

        this[clippedX, clippedY] = color
    }
}


