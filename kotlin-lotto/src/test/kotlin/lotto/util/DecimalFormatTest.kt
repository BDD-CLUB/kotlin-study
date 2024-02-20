package lotto.util

import lotto.util.decimalFormat
import lotto.util.roundTwo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DecimalFormatTest {

    @Test
    fun `소수점 둘째 자리에서 반올림 - 내림되는 경우`() {
        val value = 12.345
        val result = value.roundTwo()
        val expected = "12.3"
        assertEquals(expected, result)
    }

    @Test
    fun `소수점 둘째 자리에서 반올림 - 올림되는 경우`() {
        val value = 12.375
        val result = value.roundTwo()
        val expected = "12.4"
        assertEquals(expected, result)
    }

    @Test
    fun `소수점 둘째 자리에서 반올림 (소수점이 0인 경우)`() {
        val value = 0.0
        val result = value.roundTwo()
        val expected = "0.0"
        assertEquals(expected, result)
    }

    @Test
    fun `0 정수 포맷팅`() {
        val value = 0
        val result = value.decimalFormat()
        val expected = "0"
        assertEquals(expected, result)
    }

    @Test
    fun `한자리수 정수 포맷팅`() {
        val value = 1
        val result = value.decimalFormat()
        val expected = "1"
        assertEquals(expected, result)
    }

    @Test
    fun `두자리수 정수 포맷팅`() {
        val value = 12
        val result = value.decimalFormat()
        val expected = "12"
        assertEquals(expected, result)
    }

    @Test
    fun `세자리수 정수 포맷팅`() {
        val value = 123
        val result = value.decimalFormat()
        val expected = "123"
        assertEquals(expected, result)
    }

    @Test
    fun `네자리수 정수 포맷팅`() {
        val value = 10000
        val result = value.decimalFormat()
        val expected = "10,000"
        assertEquals(expected, result)
    }

    @Test
    fun `큰 수 정수 포맷팅`() {
        val value = 1000000000
        val result = value.decimalFormat()
        val expected = "1,000,000,000"
        assertEquals(expected, result)
    }
}
