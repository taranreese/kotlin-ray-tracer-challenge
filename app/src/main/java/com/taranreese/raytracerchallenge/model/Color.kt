package com.taranreese.raytracerchallenge.model

enum class Colors(val value: Color) {
    RED(Color(1.0, 0.0, 0.0)),
    BLACK(Color(0.0, 0.0, 0.0))
}

data class Color(val red: Double, val green: Double, val blue: Double) {
    operator fun plus(color: Color): Color {
        return Color(red + color.red, green + color.green, blue + color.blue)
    }

    operator fun minus(color: Color): Color {
        return Color(red - color.red, green - color.green, blue - color.blue)
    }

    operator fun times(color: Color): Color {
        return hadamardProduct(this, color)
    }

    operator fun times(scalar: Double): Color {
        return Color(red * scalar, green * scalar, blue * scalar)
    }
}

fun hadamardProduct(c1: Color, c2: Color): Color {
    return Color(c1.red * c2.red, c1.green * c2.green, c1.blue * c2.blue)
}
