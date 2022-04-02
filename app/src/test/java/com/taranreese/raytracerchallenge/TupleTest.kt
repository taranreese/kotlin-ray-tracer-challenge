package com.taranreese.raytracerchallenge

import com.taranreese.raytracerchallenge.model.Point
import com.taranreese.raytracerchallenge.model.Vector
import com.taranreese.raytracerchallenge.model.cross
import com.taranreese.raytracerchallenge.model.dot
import junit.framework.TestCase.assertEquals
import org.junit.Test
import kotlin.math.sqrt

class TupleTest {
    @Test
    fun pointHasExpectedW() {
        val p = Point(4.0, -4.0, 3.0)
        assertEquals(p.w, 1.0)
    }

    @Test
    fun vectorHasExpectedW() {
        val v = Vector(4.0, -4.0, 3.0)
        assertEquals(v.w, 0.0)
    }

    @Test
    fun addingTwoPoints() {
        val a1 = Point(3.0, -2.0, 5.0, 1.0)
        val a2 = Vector(-2.0, 3.0, 1.0, 0.0)
        assertEquals(a1 + a2, Point(1.0, 1.0, 6.0, 1.0))
    }

    @Test
    fun subtractingTwoPoints() {
        val p1 = Point(3.0, 2.0, 1.0)
        val p2 = Point(5.0, 6.0, 7.0)
        assertEquals(p1 - p2, Vector(-2.0, -4.0, -6.0))
    }

    @Test
    fun subtractingVectorFromPoint() {
        val p = Point(3.0, 2.0, 1.0)
        val v = Vector(5.0, 6.0, 7.0)
        assertEquals(p - v, Point(-2.0, -4.0, -6.0))
    }

    @Test
    fun subtractingTwoVectors() {
        val v1 = Vector(3.0, 2.0, 1.0)
        val v2 = Vector(5.0, 6.0, 7.0)
        assertEquals(v1 - v2, Vector(-2.0, -4.0, -6.0))
    }

    @Test
    fun subtractingVectorFromZeroVector() {
        val zero = Vector(0.0, 0.0, 0.0)
        val v = Vector(1.0, -2.0, 3.0)
        assertEquals(zero - v, Vector(-1.0, 2.0, -3.0))
    }

    @Test
    fun negatePoint() {
        val p = Point(1.0, -2.0, 3.0)
        assertEquals(-p, Point(-1.0, 2.0, -3.0))
    }

    @Test
    fun negateVector() {
        val v = Vector(1.0, -2.0, 3.0)
        assertEquals(-v, Vector(-1.0, 2.0, -3.0))
    }

    @Test
    fun multiplyPointByScalar() {
        val p = Point(1.0, -2.0, 3.0)
        val s = p * 3.5
        assertEquals(s, Point(3.5, -7.0, 10.5))
    }

    @Test
    fun multiplyVectorByFraction() {
        val v = Vector(1.0, -2.0, 3.0)
        val s = v * 0.5
        assertEquals(s, Vector(0.5, -1.0, 1.5))
    }

    @Test
    fun dividePointByScalar() {
        val p = Point(1.0, -2.0, 3.0)
        val s = p / 2.0
        assertEquals(s, Point(0.5, -1.0, 1.5))
    }

    @Test
    fun magnitudeOfVectors() {
        val v = Vector(1.0, 0.0, 0.0)
        assertEquals(1.0, v.magnitude())

        val v2 = Vector(0.0, 1.0, 0.0)
        assertEquals(1.0, v2.magnitude())

        val v3 = Vector(0.0, 0.0, 1.0)
        assertEquals(1.0, v3.magnitude())

        val v4 = Vector(1.0, 2.0, 3.0)
        assertEquals(sqrt(14.0), v4.magnitude())

        val v5 = Vector(-1.0, -2.0, -3.0)
        assertEquals(sqrt(14.0), v5.magnitude())
    }

    @Test
    fun normalizingVectors() {
        val v = Vector(4.0, 0.0, 0.0)
        assertEquals(v.normalize(), Vector(1.0, 0.0, 0.0))

        val v2 = Vector(1.0, 2.0, 3.0)
        assertEquals(v2.normalize(), Vector(1.0 / sqrt(14.0), 2.0 / sqrt(14.0), 3.0 / sqrt(14.0)))

        val v3 = Vector(1.0, 2.0, 3.0)
        val norm = v3.normalize()
        assertEquals(norm.magnitude(), 1.0)
    }

    @Test
    fun dotProductOfVectors() {
        val a = Vector(1.0, 2.0, 3.0)
        val b = Vector(2.0, 3.0, 4.0)
        assertEquals(dot(a, b), 20.0)
    }

    @Test
    fun crossProduct() {
        val a = Vector(1.0, 2.0, 3.0)
        val b = Vector(2.0, 3.0, 4.0)
        assertEquals(cross(a, b), Vector(-1.0, 2.0, -1.0))
        assertEquals(cross(b, a), Vector(1.0, -2.0, 1.0))
    }
}
