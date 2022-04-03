package com.taranreese.raytracerchallenge

import com.taranreese.raytracerchallenge.model.Canvas
import com.taranreese.raytracerchallenge.model.Color
import com.taranreese.raytracerchallenge.model.Colors
import com.taranreese.raytracerchallenge.output.ExportPPM
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CanvasTest {

    @Test
    fun testCreatingCanvas() {
        val c = Canvas(10, 20)
        assertEquals(c.width, 10)
        assertEquals(c.height, 20)

        for (color in c.pixels) {
            assertEquals(color, Color(0.0, 0.0, 0.0))
        }
    }

    @Test
    fun testWritePixel() {
        val c = Canvas(10, 20)
        val red = Colors.RED.value
        c.writePixel(2, 3, red)
        assertEquals(c[2, 3], red)
    }

    @Test
    fun testPPMHeader() {
        val c = Canvas(5, 3)
        val ppm = ExportPPM.canvasToPPM(c)
        val stringByLines = ppm.split("\n")

        stringByLines.forEachIndexed { index, value ->
            when (index) {
                0 -> assertEquals(value, "P3")
                1 -> assertEquals(value, "5 3")
                2 -> assertEquals(value, "255")
                else -> {}
            }
        }
    }

    @Test
    fun testConstructingPPMColorValues() {
        val c = Canvas(5, 3)
        val c1 = Color(1.5, 0.0, 0.0)
        val c2 = Color(0.0, 0.5, 0.0)
        val c3 = Color(-0.5, 0.0, 1.0)

        c.writePixel(0, 0, c1)
        c.writePixel(2, 1, c2)
        c.writePixel(4, 2, c3)

        val ppm = ExportPPM.canvasToPPM(c)
        val stringByLines = ppm.split("\n")

        stringByLines.forEachIndexed { index, value ->
            when (index) {
                3 -> assertEquals(value, "255 0 0 0 0 0 0 0 0 0 0 0 0 0 0")
                4 -> assertEquals(value, "0 0 0 0 0 0 0 128 0 0 0 0 0 0 0")
                5 -> assertEquals(value, "0 0 0 0 0 0 0 0 0 0 0 0 0 0 255")
                else -> {}
            }
        }
    }

    @Test
    fun testPPMEndsWithNewline() {
        val c = Canvas(5, 3)
        val ppm = ExportPPM.canvasToPPM(c)
        assertEquals(ppm.last(), '\n')
    }
}
