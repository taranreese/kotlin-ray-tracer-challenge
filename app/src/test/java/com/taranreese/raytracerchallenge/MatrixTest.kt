package com.taranreese.raytracerchallenge

import com.taranreese.raytracerchallenge.model.*
import junit.framework.TestCase.*
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
        val actualResult = a * b
        assertEquals(actualResult.x, 18.0)
        assertEquals(actualResult.y, 24.0)
        assertEquals(actualResult.z, 33.0)
        assertEquals(actualResult.w, 1.0)
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

    @Test
    fun testMinorOfMatrix3() {
        val m = Matrix3(values = doubleArrayOf(
            3.0, 5.0, 0.0,
            2.0, -1.0, -7.0,
            6.0, -1.0, 5.0
        ))

        val b = m.submatrix(1, 0)
        assertEquals(b.determinant(), 25.0)
        assertEquals(m.minor(1, 0), 25.0)
    }

    @Test
    fun testCofactor() {
        val m = Matrix3(values = doubleArrayOf(
            3.0, 5.0, 0.0,
            2.0, -1.0, -7.0,
            6.0, -1.0, 5.0
        ))

        assertEquals(m.minor(0, 0), -12.0)
        assertEquals(m.cofactor(0, 0), -12.0)
        assertEquals(m.minor(1, 0), 25.0)
        assertEquals(m.cofactor(1, 0), -25.0)
    }

    @Test
    fun testDeterminantMatrix3() {
        val a = Matrix3(values = doubleArrayOf(
            1.0, 2.0, 6.0,
            -5.0, 8.0, -4.0,
            2.0, 6.0, 4.0
        ))

        assertEquals(a.cofactor(0, 0), 56.0)
        assertEquals(a.cofactor(0, 1), 12.0)
        assertEquals(a.cofactor(0, 2), -46.0)
        assertEquals(a.determinant(), -196.0)
    }

    @Test
    fun testDeterminantMatrix4() {
        val a = Matrix4(values = doubleArrayOf(
            -2.0, -8.0, 3.0, 5.0,
            -3.0, 1.0, 7.0, 3.0,
            1.0, 2.0, -9.0, 6.0,
            -6.0, 7.0, 7.0, -9.0
        ))

        assertEquals(a.cofactor(0, 0), 690.0)
        assertEquals(a.cofactor(0, 1), 447.0)
        assertEquals(a.cofactor(0, 2), 210.0)
        assertEquals(a.cofactor(0, 3), 51.0)
        assertEquals(a.determinant(), -4071.0)
    }

    @Test
    fun testInvertibleMatrix() {
        val a = Matrix4(values = doubleArrayOf(
            6.0, 4.0, 4.0, 4.0,
            5.0, 5.0, 7.0, 6.0,
            4.0, -9.0, 3.0, -7.0,
            9.0, 1.0, 7.0, -6.0
        ))

        assertEquals(a.determinant(), -2120.0)
        assertTrue(a.isInvertible())
    }

    @Test
    fun testNoninvertibleMatrix() {
        val a = Matrix4(values = doubleArrayOf(
            -4.0, 2.0, -2.0, -3.0,
            9.0, 6.0, 2.0, 6.0,
            0.0, -5.0, 1.0, -5.0,
            0.0, 0.0, 0.0, 0.0
        ))

        assertEquals(a.determinant(), 0.0)
        assertFalse(a.isInvertible())
    }

    @Test
    fun testCalculatingMatrixInverse() {
        val a = Matrix4(values = doubleArrayOf(
            -5.0, 2.0, 6.0, -8.0,
            1.0, -5.0, 1.0, 8.0,
            7.0, 7.0, -6.0, -7.0,
            1.0, -3.0, 7.0, 4.0
        ))

        val b = inverse(a)
        assertNotNull(b)
        assertEquals(532.0, a.determinant())
        assertEquals(-160.0, a.cofactor(2, 3))
        assertEquals(105.0, a.cofactor(3, 2))

        val bExpected = Matrix4(values = doubleArrayOf(
            0.21805, 0.45113, 0.24060, -0.04511,
            -0.80827, -1.45677, -0.44361, 0.52068,
            -0.07895, -0.22368, -0.05263, 0.19737,
            -0.52256, -0.81391, -0.30075, 0.30639
        ))

        b?.let {
            assertEquals(it[3,2], -160.0/532.0)
            assertEquals(it[2,3], 105.0/532.0)
            assertEquals(it[0, 0], bExpected[0, 0], 0.00001)
            assertEquals(it[0, 1], bExpected[0, 1], 0.00001)
            assertEquals(it[0, 2], bExpected[0, 2], 0.00001)
            assertEquals(it[0, 3], bExpected[0, 3], 0.00001)
            assertEquals(it[1, 0], bExpected[1, 0], 0.00001)
            assertEquals(it[1, 1], bExpected[1, 1], 0.00001)
            assertEquals(it[1, 2], bExpected[1, 2], 0.00001)
            assertEquals(it[1, 3], bExpected[1, 3], 0.00001)
            assertEquals(it[2, 0], bExpected[2, 0], 0.00001)
            assertEquals(it[2, 1], bExpected[2, 1], 0.00001)
            assertEquals(it[2, 2], bExpected[2, 2], 0.00001)
            assertEquals(it[2, 3], bExpected[2, 3], 0.00001)
            assertEquals(it[3, 0], bExpected[3, 0], 0.00001)
            assertEquals(it[3, 1], bExpected[3, 1], 0.00001)
            assertEquals(it[3, 2], bExpected[3, 2], 0.00001)
            assertEquals(it[3, 3], bExpected[3, 3], 0.00001)
        }
    }

    @Test
    fun testCalculatingMatrixInverse2() {
        val a = Matrix4(values = doubleArrayOf(
            8.0, -5.0, 9.0, 2.0,
            7.0, 5.0, 6.0, 1.0,
            -6.0, 0.0, 9.0, 6.0,
            -3.0, 0.0, -9.0, -4.0
        ))

        val b = inverse(a)
        assertNotNull(b)

        val bExpected = Matrix4(values = doubleArrayOf(
            -0.15385, -0.15385, -0.28205, -0.53846,
            -0.07692, 0.12308, 0.02564, 0.03077,
            0.35897, 0.35897,  0.43590, 0.92308,
            -0.69231, -0.69231, -0.76923, -1.92308
        ))

        b?.let {
            assertEquals(it[0, 0], bExpected[0, 0], 0.00001)
            assertEquals(it[0, 1], bExpected[0, 1], 0.00001)
            assertEquals(it[0, 2], bExpected[0, 2], 0.00001)
            assertEquals(it[0, 3], bExpected[0, 3], 0.00001)
            assertEquals(it[1, 0], bExpected[1, 0], 0.00001)
            assertEquals(it[1, 1], bExpected[1, 1], 0.00001)
            assertEquals(it[1, 2], bExpected[1, 2], 0.00001)
            assertEquals(it[1, 3], bExpected[1, 3], 0.00001)
            assertEquals(it[2, 0], bExpected[2, 0], 0.00001)
            assertEquals(it[2, 1], bExpected[2, 1], 0.00001)
            assertEquals(it[2, 2], bExpected[2, 2], 0.00001)
            assertEquals(it[2, 3], bExpected[2, 3], 0.00001)
            assertEquals(it[3, 0], bExpected[3, 0], 0.00001)
            assertEquals(it[3, 1], bExpected[3, 1], 0.00001)
            assertEquals(it[3, 2], bExpected[3, 2], 0.00001)
            assertEquals(it[3, 3], bExpected[3, 3], 0.00001)
        }
    }

    @Test
    fun testCalculatingMatrixInverse3() {
        val a = Matrix4(values = doubleArrayOf(
            9.0, 3.0, 0.0, 9.0,
            -5.0, -2.0, -6.0, -3.0,
            -4.0, 9.0, 6.0, 4.0,
            -7.0, 6.0, 6.0, 2.0
        ))

        val b = inverse(a)
        assertNotNull(b)

        val bExpected = Matrix4(values = doubleArrayOf(
            -0.04074, -0.07778, 0.14444, -0.22222,
            -0.07778, 0.03333, 0.36667, -0.33333,
            -0.02901, -0.14630,  -0.10926, 0.12963,
            0.17778, 0.06667, -0.26667, 0.33333
        ))

        b?.let {
            assertEquals(it[0, 0], bExpected[0, 0], 0.00001)
            assertEquals(it[0, 1], bExpected[0, 1], 0.00001)
            assertEquals(it[0, 2], bExpected[0, 2], 0.00001)
            assertEquals(it[0, 3], bExpected[0, 3], 0.00001)
            assertEquals(it[1, 0], bExpected[1, 0], 0.00001)
            assertEquals(it[1, 1], bExpected[1, 1], 0.00001)
            assertEquals(it[1, 2], bExpected[1, 2], 0.00001)
            assertEquals(it[1, 3], bExpected[1, 3], 0.00001)
            assertEquals(it[2, 0], bExpected[2, 0], 0.00001)
            assertEquals(it[2, 1], bExpected[2, 1], 0.00001)
            assertEquals(it[2, 2], bExpected[2, 2], 0.00001)
            assertEquals(it[2, 3], bExpected[2, 3], 0.00001)
            assertEquals(it[3, 0], bExpected[3, 0], 0.00001)
            assertEquals(it[3, 1], bExpected[3, 1], 0.00001)
            assertEquals(it[3, 2], bExpected[3, 2], 0.00001)
            assertEquals(it[3, 3], bExpected[3, 3], 0.00001)
        }
    }

    @Test
    fun testMultiplyingInverse() {
        val a = Matrix4(values = doubleArrayOf(
            3.0, -9.0, 7.0, 3.0,
            3.0, -8.0, 2.0, -9.0,
            -4.0, 4.0, 4.0, 1.0,
            -6.0, 5.0, -1.0, 1.0
        ))

        val b = Matrix4(values = doubleArrayOf(
            8.0, 2.0, 2.0, 2.0,
            3.0, -1.0, 7.0, 0.0,
            7.0, 0.0, 5.0, 4.0,
            6.0, -2.0, 0.0, 5.0
        ))

        val c = a * b
        val inverseProduct = c * inverse(b) as Matrix4
        inverseProduct.let {
            assertEquals(it[0, 0], a[0, 0], 0.00001)
            assertEquals(it[0, 1], a[0, 1], 0.00001)
            assertEquals(it[0, 2], a[0, 2], 0.00001)
            assertEquals(it[0, 3], a[0, 3], 0.00001)
            assertEquals(it[1, 0], a[1, 0], 0.00001)
            assertEquals(it[1, 1], a[1, 1], 0.00001)
            assertEquals(it[1, 2], a[1, 2], 0.00001)
            assertEquals(it[1, 3], a[1, 3], 0.00001)
            assertEquals(it[2, 0], a[2, 0], 0.00001)
            assertEquals(it[2, 1], a[2, 1], 0.00001)
            assertEquals(it[2, 2], a[2, 2], 0.00001)
            assertEquals(it[2, 3], a[2, 3], 0.00001)
            assertEquals(it[3, 0], a[3, 0], 0.00001)
            assertEquals(it[3, 1], a[3, 1], 0.00001)
            assertEquals(it[3, 2], a[3, 2], 0.00001)
            assertEquals(it[3, 3], a[3, 3], 0.00001)
        }
    }
    @Test
    fun testMultiplyingByTranslationMatrix() {
        val transform = translation(5.0, -3.0, 2.0)
        val p = Point(-3.0, 4.0, 5.0)
        val actualResult = transform * p
        assertEquals(2.0, actualResult.x)
        assertEquals(1.0, actualResult.y)
        assertEquals(7.0, actualResult.z)
    }

    @Test
    fun testMultiplyingByInverseOfTranslationMatrix() {
        val transform = translation(5.0, -3.0, 2.0)
        val inv = inverse(transform) as? Matrix4
        assertNotNull(inv)
        inv?.let {
            val p = Point(-3.0, 4.0, 5.0)
            val actualResult = it * p
            assertEquals(actualResult.x,-8.0)
            assertEquals(actualResult.y,7.0)
            assertEquals(actualResult.z,3.0)
        }
    }

    @Test
    fun testTranslationDoesNotAffectVectors() {
        val transform = translation(5.0, -3.0, 2.0)
        val v = Vector(-3.0, 4.0, 5.0)
        val actualResult = transform * v
        assertEquals(actualResult.x, -3.0)
        assertEquals(actualResult.y, 4.0)
        assertEquals(actualResult.z, 5.0)
    }
}