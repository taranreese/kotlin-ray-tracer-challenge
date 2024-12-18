package com.taranreese.raytracerchallenge.model

import org.jetbrains.bio.viktor.F64Array

abstract class Matrix(internal open var matrix: F64Array, internal val size: Int) {
    operator fun get(x: Int, y: Int): Double {
        return matrix[x, y]
    }

    operator fun set(x: Int, y: Int, v: Double) {
        matrix[x, y] = v
    }

    internal fun cofactor(row: Int, column: Int): Double {
        val minor = minor(row, column)

        if ((row + column) % 2 == 0) {
            return minor
        }

        return -minor
    }

    internal fun minor(row: Int, column: Int): Double {
        val subM = submatrix(row, column)
        return subM.determinant()
    }

    internal fun isInvertible(): Boolean {
        return this.determinant() != 0.0
    }

    abstract fun determinant(): Double
    abstract fun submatrix(rowToRemove: Int, columnToRemove: Int): Matrix
    abstract fun transpose(): Matrix

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

class Matrix2(
    override var matrix: F64Array = F64Array(2, 2),
    size: Int = 2,
    values: DoubleArray = doubleArrayOf(
        0.0, 0.0,
        0.0, 0.0
    )
) : Matrix(matrix = matrix, size = size) {
    init {
        assert(values.size == 4)

        matrix[0, 0] = values[0]
        matrix[0, 1] = values[1]
        matrix[1, 0] = values[2]
        matrix[1, 1] = values[3]
    }

    override fun determinant(): Double {
        return (matrix[0, 0] * matrix[1, 1]) - (matrix[0, 1] * matrix[1, 0])
    }

    override fun submatrix(rowToRemove: Int, columnToRemove: Int): Matrix {
        // No such as thing as a submatrix of 2x2 matrix. This method should not be called on a Matrix2
        print("Warning - don't call submatrix() on Matrix2")
        return this
    }

    override fun transpose(): Matrix2 {
        val transposedMatrix = Matrix2()

        for (row in 0 until size) {
            for (column in 0..1) {
                transposedMatrix[column, row] = matrix[row, column]
            }
        }

        return transposedMatrix
    }
}

class Matrix3(
    override var matrix: F64Array = F64Array(3, 3),
    size: Int = 3,
    values: DoubleArray = doubleArrayOf(
        0.0, 0.0, 0.0,
        0.0, 0.0, 0.0,
        0.0, 0.0, 0.0
    )
) : Matrix(matrix = matrix, size = size) {
    init {
        assert(values.size == 9)

        matrix[0, 0] = values[0]
        matrix[0, 1] = values[1]
        matrix[0, 2] = values[2]
        matrix[1, 0] = values[3]
        matrix[1, 1] = values[4]
        matrix[1, 2] = values[5]
        matrix[2, 0] = values[6]
        matrix[2, 1] = values[7]
        matrix[2, 2] = values[8]
    }

    override fun determinant(): Double {
        var determinant = 0.0

        for (column in 0 until size) {
            determinant += matrix[0, column] * cofactor(0, column)
        }

        return determinant
    }

    override fun submatrix(rowToRemove: Int, columnToRemove: Int): Matrix2 {
        val newValues = arrayListOf<Double>()

        for (rowIndex in 0 until size) {
            if (rowIndex == rowToRemove) {
                continue
            }

            for (columnIndex in 0 until size) {
                if (columnIndex == columnToRemove) {
                    continue
                } else {
                    newValues.add(matrix[rowIndex, columnIndex])
                }
            }
        }

        return Matrix2(values = newValues.toDoubleArray())
    }

    override fun transpose(): Matrix3 {
        val transposedMatrix = Matrix3()

        for (row in 0 until size) {
            for (column in 0 until size) {
                transposedMatrix[column, row] = matrix[row, column]
            }
        }

        return transposedMatrix
    }
}

class Matrix4(
    override var matrix: F64Array = F64Array(4, 4),
    size: Int = 4,
    values: DoubleArray = doubleArrayOf(
        0.0, 0.0, 0.0, 0.0,
        0.0, 0.0, 0.0, 0.0,
        0.0, 0.0, 0.0, 0.0,
        0.0, 0.0, 0.0, 0.0
    )
) : Matrix(matrix = matrix, size = size) {
    init {
        assert(values.size == 16)

        matrix[0, 0] = values[0]
        matrix[0, 1] = values[1]
        matrix[0, 2] = values[2]
        matrix[0, 3] = values[3]
        matrix[1, 0] = values[4]
        matrix[1, 1] = values[5]
        matrix[1, 2] = values[6]
        matrix[1, 3] = values[7]
        matrix[2, 0] = values[8]
        matrix[2, 1] = values[9]
        matrix[2, 2] = values[10]
        matrix[2, 3] = values[11]
        matrix[3, 0] = values[12]
        matrix[3, 1] = values[13]
        matrix[3, 2] = values[14]
        matrix[3, 3] = values[15]
    }

    override fun determinant(): Double {
        var determinant = 0.0

        for (column in 0 until size) {
            determinant += matrix[0, column] * cofactor(0, column)
        }

        return determinant
    }

    override fun submatrix(rowToRemove: Int, columnToRemove: Int): Matrix3 {
        val newValues = arrayListOf<Double>()

        for (rowIndex in 0 until size) {
            if (rowIndex == rowToRemove) {
                continue
            }

            for (columnIndex in 0 until size) {
                if (columnIndex == columnToRemove) {
                    continue
                } else {
                    newValues.add(matrix[rowIndex, columnIndex])
                }
            }
        }

        return Matrix3(values = newValues.toDoubleArray())
    }

    override fun transpose(): Matrix4 {
        val transposedMatrix = Matrix4()

        for (row in 0 until size) {
            for (column in 0 until size) {
                transposedMatrix[column, row] = matrix[row, column]
            }
        }

        return transposedMatrix
    }

    operator fun times(otherMatrix: Matrix4): Matrix4 {
        val newMatrix = Matrix4()
        for (rowIndex in 0 until size) {
            for (columnIndex in 0 until size) {
                newMatrix[rowIndex, columnIndex] = matrix[rowIndex, 0] * otherMatrix.matrix[0, columnIndex] +
                        matrix[rowIndex, 1] * otherMatrix.matrix[1, columnIndex] +
                        matrix[rowIndex, 2] * otherMatrix.matrix[2, columnIndex] +
                        matrix[rowIndex, 3] * otherMatrix.matrix[3, columnIndex]
            }
        }
        return newMatrix
    }

    fun timesTwo(firstInt: Int, secondInt: Int): Int {
        return firstInt * secondInt
    }

    operator fun times(tuple: Tuple): Tuple {
        val outputs = doubleArrayOf(0.0, 0.0, 0.0, 0.0)

        for (index in 0 until size) {
            outputs[index] = matrix[index, 0] * tuple.x +
                    matrix[index, 1] * tuple.y +
                    matrix[index, 2] * tuple.z +
                    matrix[index, 3] * tuple.w
        }

        return when (tuple.w) {
            0.0 -> Vector(outputs[0], outputs[1], outputs[2], outputs[3])
            1.0 -> Point(outputs[0], outputs[1], outputs[2], outputs[3])
            else -> Tuple(outputs[0], outputs[1], outputs[2], outputs[3])
        }
    }
}

fun identityMatrix4(): Matrix4 {
    val m = Matrix4()
    m[0, 0] = 1.0
    m[1, 1] = 1.0
    m[2, 2] = 1.0
    m[3, 3] = 1.0

    return m
}

// Returns nil if matrix argument is not invertible
fun inverse(matrix: Matrix): Matrix? {
    if (!matrix.isInvertible()) { return null }

    val newMatrix = when (matrix.size) {
        3 -> { Matrix3() }
        4 -> { Matrix4() }
        else -> { return null }
    }

    for (rowIndex in 0 until matrix.size) {
        for (columnIndex in 0 until matrix.size) {
            val cofactor = matrix.cofactor(rowIndex, columnIndex)
            newMatrix[columnIndex, rowIndex] = cofactor / matrix.determinant()
        }
    }

    return newMatrix
}

fun translation(x: Double, y: Double, z: Double): Matrix4 {
    val m = identityMatrix4()
    m[0,3] = x
    m[1,3] = y
    m[2,3] = z

    return m
}