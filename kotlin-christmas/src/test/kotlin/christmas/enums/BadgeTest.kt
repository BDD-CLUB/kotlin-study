package christmas.enums

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class BadgeTest {

    @Test
    fun `뱃지 - null`() {
        val value = 1_000
        val result = Badge.getByValue(value)
        val expected = null
        assertTrue(result == expected)
    }

    @Test
    fun `뱃지 - 별`() {
        val value = 5_000
        val result = Badge.getByValue(value)
        val expected = Badge.STAR
        assertEquals(expected, result)
    }

    @Test
    fun `뱃지 - 별 중간값`() {
        val value = 7_500
        val result = Badge.getByValue(value)
        val expected = Badge.STAR
        assertEquals(expected, result)
    }

    @Test
    fun `뱃지 - 트리`() {
        val value = 10_000
        val result = Badge.getByValue(value)
        val expected = Badge.TREE
        assertEquals(expected, result)
    }

    @Test
    fun `뱃지 - 트리 중간값`() {
        val value = 10_000
        val result = Badge.getByValue(value)
        val expected = Badge.TREE
        assertEquals(expected, result)
    }

    @Test
    fun `뱃지 - 산타`() {
        val value = 20_000
        val result = Badge.getByValue(value)
        val expected = Badge.SANTA
        assertEquals(expected, result)
    }

    @Test
    fun `뱃지 - 산타 초과`() {
        val value = 120_000
        val result = Badge.getByValue(value)
        val expected = Badge.SANTA
        assertEquals(expected, result)
    }
}
