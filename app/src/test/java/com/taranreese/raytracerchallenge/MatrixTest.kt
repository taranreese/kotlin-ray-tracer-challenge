package com.taranreese.raytracerchallenge

import com.taranreese.raytracerchallenge.model.*
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotSame
import org.junit.Test

class MatrixTest {
    @Test
    fun test4x4Constructor() {
        val m = Matrix4(
            values = doubleArrayOf(
                1.0, 2.0, 3.0, 4.0,
                5.5, 6.5, 7.5, 8.5,
                9.0, 10.0, 11.0, 12.0, 13.5,
                14.5, 15.5, 16.5
            )
        )

        assertEquals(m[0, 0], 1.0)
        assertEquals(m[0, 3], 4.0)
        assertEquals(m[1, 0], 5.5)
        assertEquals(m[1, 2], 7.5)
        assertEquals(m[2, 2], 11.0)
        assertEquals(m[3, 0], 13.5)
        assertEquals(m[3, 2], 15.5)
    }

    @Test
    fun test2x2Constructor() {
        val m = Matrix2(
            values = doubleArrayOf(
                -3.0, 5.0,
                1.0, -2.0
            )
        )

        assertEquals(m[0, 0], -3.0)
        assertEquals(m[0, 1], 5.0)
        assertEquals(m[1, 0], 1.0)
        assertEquals(m[1, 1], -2.0)
    }

    @Test
    fun test3x3Constructor() {
        val m = Matrix3(
            values = doubleArrayOf(
                -3.0, 5.0, 0.0,
                1.0, -2.0, -7.0,
                0.0, 1.0, 1.0
            )
        )

        assertEquals(m[0, 0], -3.0)
        assertEquals(m[1, 1], -2.0)
        assertEquals(m[2, 2], 1.0)
    }

    @Test
    fun testMatrixEquality_identicalMatrices() {
        val m1 = Matrix4(
            values = doubleArrayOf(
                1.0, 2.0, 3.0, 4.0,
                5.0, 6.0, 7.0, 8.0,
                9.0, 8.0, 7.0, 6.0,
                5.0, 4.0, 3.0, 2.0
            )
        )

        val m2 = Matrix4(
            values = doubleArrayOf(
                1.0, 2.0, 3.0, 4.0,
                5.0, 6.0, 7.0, 8.0,
                9.0, 8.0, 7.0, 6.0,
                5.0, 4.0, 3.0, 2.0
            )
        )

        assertEquals(m1, m2)
    }

    @Test
    fun testMatrixEquality_differentMatrices() {
        val m1 = Matrix4(
            values = doubleArrayOf(
                1.0, 2.0, 3.0, 4.0,
                5.0, 6.0, 7.0, 8.0,
                9.0, 8.0, 7.0, 6.0,
                5.0, 4.0, 3.0, 2.0
            )
        )

        val m2 = Matrix4(
            values = doubleArrayOf(
                2.0, 3.0, 4.0, 5.0,
                6.0, 7.0, 8.0, 9.0,
                8.0, 7.0, 6.0, 5.0,
                4.0, 3.0, 2.0, 1.0
            )
        )

        assertNotSame(m1, m2)
    }

    @Test
    fun testMultiplyingMatrices() {
        val m1 = Matrix4(
            values = doubleArrayOf(
                1.0, 2.0, 3.0, 4.0,
                5.0, 6.0, 7.0, 8.0,
                9.0, 8.0, 7.0, 6.0,
                5.0, 4.0, 3.0, 2.0
            )
        )

        val m2 = Matrix4(
            values = doubleArrayOf(
                -2.0, 1.0, 2.0, 3.0,
                3.0, 2.0, 1.0, -1.0,
                4.0, 3.0, 6.0, 5.0,
                1.0, 2.0, 7.0, 8.0
            )
        )

        val m3 = Matrix4(
            values = doubleArrayOf(
                20.0, 22.0, 50.0, 48.0,
                44.0, 54.0, 114.0, 108.0,
                40.0, 58.0, 110.0, 102.0,
                16.0, 26.0, 46.0, 42.0
            )
        )

        assertEquals((m1 * m2).matrix, m3.matrix)
    }

    @Test
    fun testMultiplyMatrixByTuple() {
        val a = Matrix4(
            values = doubleArrayOf(
                1.0, 2.0, 3.0, 4.0,
                2.0, 4.0, 4.0, 2.0,
                8.0, 6.0, 4.0, 1.0,
                0.0, 0.0, 0.0, 1.0
            )
        )

        val b = Tuple(1.0, 2.0, 3.0, 1.0)
        assertEquals(a * b, Tuple(18.0, 24.0, 33.0, 1.0))
    }

    @Test
    fun testMultiplyMatrixByIdentity() {
        val a = Matrix4(
            values = doubleArrayOf(
                0.0, 1.0, 2.0, 4.0,
                1.0, 2.0, 4.0, 8.0,
                2.0, 4.0, 8.0, 16.0,
                4.0, 8.0, 16.0, 32.0
            )
        )

        assertEquals(a * identityMatrix4(), a)
    }

    @Test
    fun testMultiplyIdentityMatrixByTuple() {
        val a = Tuple(1.0, 2.0, 3.0, 4.0)
        assertEquals(identityMatrix4() * a, a)
    }

    @Test
    fun testTransposingMatrix() {
        val a = Matrix4(
            values = doubleArrayOf(
                0.0, 9.0, 3.0, 0.0,
                9.0, 8.0, 0.0, 8.0,
                1.0, 8.0, 5.0, 3.0,
                0.0, 0.0, 5.0, 8.0
            )
        )

        val b = Matrix4(
            values = doubleArrayOf(
                0.0, 9.0, 1.0, 0.0,
                9.0, 8.0, 8.0, 0.0,
                3.0, 0.0, 5.0, 5.0,
                0.0, 8.0, 3.0, 8.0
            )
        )

        assertEquals(a.transpose(), b)
    }

    @Test
    fun testTransposingIdentityMatrix() {
        val a = identityMatrix4().transpose()
        assertEquals(a, identityMatrix4())
    }

    @Test
    fun testDeterminantOfMatrix2() {
        val a = Matrix2(
            values = doubleArrayOf(
                1.0, 5.0,
                -3.0, 2.0
            )
        )

        assertEquals(a.determinant(), 17.0)
    }

    @Test
    fun testSubmatrixOf3x3is2x2() {
        val a = Matrix3(
            values = doubleArrayOf(
                1.0, 5.0, 0.0,
                -3.0, 2.0, 7.0,
                0.0, 6.0, -3.0
            )
        )

        val b = Matrix2(
            values = doubleArrayOf(
                -3.0, 2.0,
                0.0, 6.0
            )
        )

        assertEquals(a.submatrix(0, 2), b)
    }

    @Test
    fun testSubmatrixOf4x4is3x3() {
        val a = Matrix4(
            values = doubleArrayOf(
                -6.0, 1.0, 1.0, 6.0,
                -8.0, 5.0, 8.0, 6.0,
                -1.0, 0.0, 8.0, 2.0,
                -7.0, 1.0, -1.0, 1.0
            )
        )

        val b = Matrix3(
            values = doubleArrayOf(
                -6.0, 1.0, 6.0,
                -8.0, 8.0, 6.0,
                -7.0, -1.0, 1.0
            )
        )

        assertEquals(a.submatrix(2, 1), b)
    }
}