package com.taranreese.raytracerchallenge

import com.taranreese.raytracerchallenge.model.Color
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ColorTest {
    @Test
    fun testConstructor() {
        val c = Color(red = -0.5, green = 0.4, blue = 1.7)
        assertEquals(c.red, -0.5)
        assertEquals(c.green, 0.4)
        assertEquals(c.blue, 1.7)
    }

    @Test
    fun testAddingColors() {
        val c1 = Color(0.9, 0.6, 0.75)
        val c2 = Color(0.7, 0.1, 0.25)
        assertEquals(c1 + c2, Color(1.6, 0.7, 1.0))
    }

    @Test
    fun testSubtractingColors() {
        val c1 = Color(0.9, 0.6, 0.75)
        val c2 = Color(0.7, 0.1, 0.25)
        assertEquals((c1 - c2).red, 0.2, 0.0001)
        assertEquals((c1 - c2).green, 0.5, 0.0001)
        assertEquals((c1 - c2).blue, 0.5, 0.0001)
    }

    @Test
    fun testMultiplyingColorByScalar() {
        val c = Color(0.2, 0.3, 0.4)
        assertEquals(c * 2.0, Color(0.4, 0.6, 0.8))
    }

    @Test
    fun testMultiplyingColors() {
        val c1 = Color(1.0, 0.2, 0.4)
        val c2 = Color(0.9, 1.0, 0.1)
        assertEquals((c1 * c2).red, 0.9, 0.0001)
        assertEquals((c1 * c2).green, 0.2, 0.0001)
        assertEquals((c1 * c2).blue, 0.04, 0.0001)
    }
}
