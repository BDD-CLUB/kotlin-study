package lotto._enums

import lotto._enums.LottoResult
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LottoResultTest {

    @Test
    fun `3개 일치 getMessage()`() {
        val lotto = LottoResult.THREE_CORRECT
        val result = lotto.getMessage(1)
        val expected = "3개 일치 (5,000원) - 1개"
        assertEquals(expected, result)
    }

    @Test
    fun `3개 일치 get`() {
        val value = 3
        val isBonusCorrect = false
        val result = LottoResult.get(value, isBonusCorrect)
        val expected = LottoResult.THREE_CORRECT
        assertEquals(expected, result)
    }

    @Test
    fun `4개 일치 getMessage()`() {
        val lotto = LottoResult.FOUR_CORRECT
        val result = lotto.getMessage(2)
        val expected = "4개 일치 (50,000원) - 2개"
        assertEquals(expected, result)
    }

    @Test
    fun `4개 일치 get`() {
        val value = 4
        val isBonusCorrect = false
        val result = LottoResult.get(value, isBonusCorrect)
        val expected = LottoResult.FOUR_CORRECT
        assertEquals(expected, result)
    }

    @Test
    fun `5개 일치 getMessage()`() {
        val lotto = LottoResult.FIVE_CORRECT
        val result = lotto.getMessage(3)
        val expected = "5개 일치 (1,500,000원) - 3개"
        assertEquals(expected, result)
    }

    @Test
    fun `5개 일치 get`() {
        val value = 5
        val isBonusCorrect = false
        val result = LottoResult.get(value, isBonusCorrect)
        val expected = LottoResult.FIVE_CORRECT
        assertEquals(expected, result)
    }

    @Test
    fun `5개 일치, 보너스볼 일치 getMessage()`() {
        val lotto = LottoResult.FIVE_BONUS_CORRECT
        val result = lotto.getMessage(2)
        val expected = "5개 일치, 보너스 볼 일치 (30,000,000원) - 2개"
        assertEquals(expected, result)
    }

    @Test
    fun `5개 일치, 보너스볼 일치 get`() {
        val value = 5
        val isBonusCorrect = true
        val result = LottoResult.get(value, isBonusCorrect)
        val expected = LottoResult.FIVE_BONUS_CORRECT
        assertEquals(expected, result)
    }

    @Test
    fun `6개 일치 getMessage()`() {
        val lotto = LottoResult.SIX_CORRECT
        val result = lotto.getMessage(0)
        val expected = "6개 일치 (2,000,000,000원) - 0개"
        assertEquals(expected, result)
    }

    @Test
    fun `6개 일치 get`() {
        val value = 6
        val isBonusCorrect = false
        val result = LottoResult.get(value, isBonusCorrect)
        val expected = LottoResult.SIX_CORRECT
        assertEquals(expected, result)
    }
}
