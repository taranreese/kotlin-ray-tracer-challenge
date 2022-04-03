package com.taranreese.raytracerchallenge.model

import org.jetbrains.bio.viktor.F64Array

open class Matrix(private var matrix: F64Array) {
    operator fun get(x: Int, y: Int): Double {
        return matrix[x, y]
    }

    operator fun set(x: Int, y: Int, v: Double) {
        matrix[x, y] = v
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Matrix

        if (matrix != other.matrix) return false

        return true
    }

    override fun hashCode(): Int {
        return matrix.hashCode()
    }
}
class Matrix2(private var matrix: F64Array = F64Array(2, 2),
              values: DoubleArray = doubleArrayOf(
                  0.0, 0.0,
                  0.0, 0.0)): Matrix(matrix = matrix) {
    init {
        assert(values.size == 4)

        matrix[0,0] = values[0]
        matrix[0,1] = values[1]
        matrix[1,0] = values[2]
        matrix[1,1] = values[3]
    }

    operator fun times(other: Matrix2): Matrix2 {
        val matrix = matrix * other.matrix
        return Matrix2(matrix = matrix)
    }
}

class Matrix3(private var matrix: F64Array = F64Array(3, 3),
              values: DoubleArray = doubleArrayOf(
                  0.0, 0.0, 0.0,
                  0.0, 0.0, 0.0,
                  0.0, 0.0, 0.0)): Matrix(matrix = matrix) {
    init {
        assert(values.size == 9)

        matrix[0,0] = values[0]
        matrix[0,1] = values[1]
        matrix[0,2] = values[2]
        matrix[1,0] = values[3]
        matrix[1,1] = values[4]
        matrix[1,2] = values[5]
        matrix[2,0] = values[6]
        matrix[2,1] = values[7]
        matrix[2,2] = values[8]
    }

    operator fun times(other: Matrix3): Matrix3 {
        val matrix = matrix * other.matrix
        return Matrix3(matrix = matrix)
    }
}

class Matrix4(var matrix: F64Array = F64Array(4, 4),
              values: DoubleArray = doubleArrayOf(
                  0.0, 0.0, 0.0, 0.0,
                  0.0, 0.0, 0.0, 0.0,
                  0.0, 0.0, 0.0, 0.0,
                  0.0, 0.0, 0.0, 0.0)): Matrix(matrix = matrix) {
    init {
        assert(values.size == 16)

        matrix[0,0] = values[0]
        matrix[0,1] = values[1]
        matrix[0,2] = values[2]
        matrix[0,3] = values[3]
        matrix[1,0] = values[4]
        matrix[1,1] = values[5]
        matrix[1,2] = values[6]
        matrix[1,3] = values[7]
        matrix[2,0] = values[8]
        matrix[2,1] = values[9]
        matrix[2,2] = values[10]
        matrix[2,3] = values[11]
        matrix[3,0] = values[12]
        matrix[3,1] = values[13]
        matrix[3,2] = values[14]
        matrix[3,3] = values[15]
    }

    operator fun times(other: Matrix4): Matrix4 {
        val newMatrix = Matrix4()
        for (rowIndex in 0..3) {
            for (columnIndex in 0..3) {
                newMatrix[rowIndex, columnIndex] = matrix[rowIndex, 0] * other.matrix[0, columnIndex] +
                        matrix[rowIndex, 1] * other.matrix[1, columnIndex] +
                        matrix[rowIndex, 2] * other.matrix[2, columnIndex] +
                        matrix[rowIndex, 3] * other.matrix[3, columnIndex]
            }
        }
        return newMatrix
    }

    operator fun times(other: Tuple): Tuple {
        val outputs = doubleArrayOf(0.0, 0.0, 0.0, 0.0)

        for (index in 0..3) {
            outputs[index] = matrix[index, 0] * other.x +
                    matrix[index, 1] * other.y +
                    matrix[index, 2] * other.z +
                    matrix[index, 3] * other.w
        }

        return Tuple(outputs[0], outputs[1], outputs[2], outputs[3])
    }
}