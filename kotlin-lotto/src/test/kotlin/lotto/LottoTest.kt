package lotto

import lotto._enums.LottoResult
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


class LottoTest {
    @Test
    fun `로또 번호의 개수가 6개가 넘어가면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(listOf(1, 2, 3, 4, 5, 6, 7))
        }
    }

    @Test
    fun `로또 번호에 중복된 숫자가 있으면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(listOf(1, 2, 3, 4, 5, 5))
        }
    }

    @Test
    fun `로또 번호에 범위 밖의 숫자가 있으면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(listOf(1, 2, 3, 4, 5, 46))
        }
    }

    @Test
    fun `결과 계산 성공 - 일치하는 결과 없음`() {
        val lottoBuyerDTO = LottoBuyerDTO(3, listOf(1,2,3,4,5,6), 7)
        val result = Lotto(listOf(1,22,33,34,44,45)).getResult(lottoBuyerDTO)
        val expected = null
        assertEquals(expected, result)
    }

    @Test
    fun `결과 계산 성공 - 3개 일치`() {
        val lottoBuyerDTO = LottoBuyerDTO(3, listOf(1,2,3,4,5,6), 7)
        val result = Lotto(listOf(1,2,3,14,15,16)).getResult(lottoBuyerDTO)
        val expected = LottoResult.THREE_CORRECT
        assertEquals(expected, result)
    }

    @Test
    fun `결과 계산 성공 - 4개 일치`() {
        val lottoBuyerDTO = LottoBuyerDTO(3, listOf(1,2,3,4,5,6), 7)
        val result = Lotto(listOf(1,2,3,4,15,16)).getResult(lottoBuyerDTO)
        val expected = LottoResult.FOUR_CORRECT
        assertEquals(expected, result)
    }

    @Test
    fun `결과 계산 성공 - 5개 일치`() {
        val lottoBuyerDTO = LottoBuyerDTO(3, listOf(1,2,3,4,5,6), 7)
        val result = Lotto(listOf(1,2,3,4,5,16)).getResult(lottoBuyerDTO)
        val expected = LottoResult.FIVE_CORRECT
        assertEquals(expected, result)
    }

    @Test
    fun `결과 계산 성공 - 5개 일치, 보너스볼 일치`() {
        val lottoBuyerDTO = LottoBuyerDTO(3, listOf(1,2,3,4,5,6), 7)
        val result = Lotto(listOf(1,2,3,4,5,7)).getResult(lottoBuyerDTO)
        val expected = LottoResult.FIVE_BONUS_CORRECT
        assertEquals(expected, result)
    }

    @Test
    fun `결과 계산 성공 - 6개 일치`() {
        val lottoBuyerDTO = LottoBuyerDTO(3, listOf(1,2,3,4,5,6), 7)
        val result = Lotto(listOf(1,2,3,4,5,6)).getResult(lottoBuyerDTO)
        val expected = LottoResult.SIX_CORRECT
        assertEquals(expected, result)
    }

}
