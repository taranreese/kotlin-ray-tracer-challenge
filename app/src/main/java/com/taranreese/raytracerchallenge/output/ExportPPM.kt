package com.taranreese.raytracerchallenge.output

import android.content.Context
import com.taranreese.raytracerchallenge.model.Canvas
import com.taranreese.raytracerchallenge.model.Color
import com.taranreese.raytracerchallenge.output.ExportPPM.Companion.MAX_COLOR_VALUE
import java.io.File
import java.io.IOException
import kotlin.math.ceil

class ExportPPM {

    companion object {
        const val MAX_COLOR_VALUE = 255

        fun canvasToPPM(canvas: Canvas): String {
            val header = """P3
${canvas.width} ${canvas.height}
$MAX_COLOR_VALUE"""

            val colorValues = pixelColorsToString(canvas.pixels, canvas.width)
            return "${header}\n${colorValues}"
        }

        fun savePPMFileToDocuments(canvas: Canvas, filename: String, context: Context) {
            val myExternalFile = File(context.getExternalFilesDir("Documents"), "$filename.ppm")
            val ppm = canvasToPPM(canvas)

            try {
                myExternalFile.writeText(ppm)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}

private fun pixelColorsToString(colors: List<Color>, width: Int): String {
    // walk RColor array and convert each pixel's RGB values from 0-1 to 0-MaxColorValue, clipping to those bounds
    val unbrokenString: MutableList<Char> = mutableListOf()

    // add each trio of values to current string, up to width, then add new line
    colors.forEachIndexed { index, color ->
        if (index % width == 0) {
            if (unbrokenString.isNotEmpty()) {
                unbrokenString.removeLastOrNull()
            }

            if (index != 0) {
                unbrokenString.add('\n')
            }
        }

        val colorString = rColorToString(color)
        unbrokenString.addAll(colorString.toList())
        unbrokenString.add(' ')
    }

    // remove trailing space from last entry
    unbrokenString.removeLastOrNull()

    // return string with line breaks
    return unbrokenString.joinToString("") + '\n'
}

private fun rColorToString(color: Color): String {
    val rgbArray = listOf(color.red, color.green, color.blue)
    val stringifiedArray = rgbArray.map { doubleColorToInt(it) }
    return stringifiedArray.joinToString(" ")
}

private fun doubleColorToInt(double: Double): Int {
    var value = ceil(double * MAX_COLOR_VALUE.toDouble()).toInt()

    if (value < 0) {
        value = 0
    } else if (value > MAX_COLOR_VALUE) {
        value = MAX_COLOR_VALUE
    }

    return value
}
