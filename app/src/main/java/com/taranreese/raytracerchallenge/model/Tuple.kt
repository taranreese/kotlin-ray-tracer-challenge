package com.taranreese.raytracerchallenge.model

import kotlin.math.sqrt
import kotlin.math.pow

open class Tuple(
    open val x: Double = 0.0,
    open val y: Double = 0.0,
    open val z: Double = 0.0,
    open val w: Double = 0.0
) {

    // returns NaN for zero magnitude vectors
    fun magnitude(): Double {
        val sum = x.pow(2.0) + y.pow(2.0) + z.pow(2.0) + w.pow(2.0)
        return sqrt(sum)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Tuple

        if (x != other.x) return false
        if (y != other.y) return false
        if (z != other.z) return false
        if (w != other.w) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x.hashCode()
        result = 31 * result + y.hashCode()
        result = 31 * result + z.hashCode()
        result = 31 * result + w.hashCode()
        return result
    }

}

data class Point(
    override val x: Double,
    override val y: Double,
    override val z: Double,
    override val w: Double = 1.0
) : Tuple() {

    operator fun plus(point: Point): Point {
        return Point(x + point.x, y + point.y, z + point.z)
    }

    operator fun plus(vector: Vector): Point {
        return Point(x + vector.x, y + vector.y, z + vector.z)
    }

    operator fun minus(point: Point): Vector {
        return Vector(x - point.x, y - point.y, z - point.z)
    }

    operator fun minus(vector: Vector): Point {
        return Point(x - vector.x, y - vector.y, z - vector.z)
    }

    operator fun unaryMinus() = Point(-x, -y, -z)

    operator fun times(scalar: Double): Point {
        return Point(x * scalar, y * scalar, z * scalar)
    }

    operator fun div(scalar: Double): Point {
        return Point(x / scalar, y / scalar, z / scalar)
    }

    fun normalize(): Point {
        val magnitude = magnitude()

        if (magnitude == 0.0) {
            return Point(magnitude, magnitude, magnitude, magnitude)
        }

        return Point(x / magnitude, y / magnitude, z / magnitude, w / magnitude)
    }
}

data class Vector(
    override val x: Double,
    override val y: Double,
    override val z: Double,
    override val w: Double = 0.0
) : Tuple() {

    operator fun plus(point: Point): Point {
        return Point(x + point.x, y + point.y, z + point.z)
    }

    operator fun plus(vector: Vector): Vector {
        return Vector(x + vector.x, y + vector.y, z + vector.z)
    }

    operator fun minus(point: Point): Point {
        return Point(x - point.x, y - point.y, z - point.z)
    }

    operator fun minus(vector: Vector): Vector {
        return Vector(x - vector.x, y - vector.y, z - vector.z)
    }

    operator fun unaryMinus() = Vector(-x, -y, -z)

    operator fun times(scalar: Double): Vector {
        return Vector(x * scalar, y * scalar, z * scalar)
    }

    operator fun div(scalar: Double): Vector {
        return Vector(x / scalar, y / scalar, z / scalar)
    }

    fun normalize(): Vector {
        val magnitude = magnitude()

        if (magnitude == 0.0) {
            return Vector(magnitude, magnitude, magnitude, magnitude)
        }

        return Vector(x / magnitude, y / magnitude, z / magnitude, w / magnitude)
    }
}

// Order of arguments matters
fun cross(v1: Vector, v2: Vector): Vector {
    if (v1.w != 0.0 || v2.w != 0.0) {
        print("Point passed to cross(), expected vector")
    }

    return Vector(
        (v1.y * v2.z) - (v1.z * v2.y),
        (v1.z * v2.x) - (v1.x * v2.z),
        (v1.x * v2.y) - (v1.y * v2.x)
    )
}

fun dot(v1: Vector, v2: Vector): Double {
    if (v1.w != 0.0 || v2.w != 0.0) {
        print("Point passed to dot(), expected vector")
    }

    return (v1.x * v2.x +
            v1.y * v2.y +
            v1.z * v2.z +
            v1.w * v2.w)
}
