package lotto.util

import lotto.util.*
import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ValidationCheckTest {
    @Test
    fun `문자열 숫자로 변환 성공`() {
        val value = "10"
        val result = value.toIntValue()
        val expected = 10
        assertEquals(expected, result)
        assertThatNoException()
    }

    @Test
    fun `문자열 숫자로 변환 실패`() {
        assertThrows<IllegalArgumentException> {
            val value = "hello"
            val result = value.toIntValue()
        }
    }

    @Test
    fun `LOTTO_AMOUNT 으로 나눠질 수 있는가 성공`() {
        val value = 10000
        value.checkDividable()
        assertThatNoException()
    }

    @Test
    fun `LOTTO_AMOUNT 으로 나눠질 수 있는가 실패`() {
        assertThrows<IllegalArgumentException> {
            val value = 12345
            value.checkDividable()
        }
    }

    @Test
    fun `양수인가 성공`() {
        val value = 10000
        value.checkPositive()
        assertThatNoException()
    }

    @Test
    fun `양수인가 실패`() {
        assertThrows<IllegalArgumentException> {
            val value = 0
            value.checkPositive()
        }
    }

    @Test
    fun `로또 숫자 범위 내에 존재하는가 성공`() {
        val value = 33
        value.checkInRange()
        assertThatNoException()
    }

    @Test
    fun `로또 숫자 범위 내에 존재하는가 실패`() {
        assertThrows<IllegalArgumentException> {
            val value = 1234
            value.checkInRange()
        }
    }

    @Test
    fun `당첨 번호와 중복되지는 않는가 성공`() {
        val value = 33
        val winningNumber = listOf(1,2,3,4,5,6)
        value.checkDuplicationWithWinningNumber(winningNumber)
        assertThatNoException()
    }

    @Test
    fun `당첨 번호와 중복되는가 실패`() {
        assertThrows<IllegalArgumentException> {
            val value = 33
            val winningNumber = listOf(1,2,3,4,5,33)
            value.checkDuplicationWithWinningNumber(winningNumber)
        }
    }

    @Test
    fun `LOTTO_SIZE 개 만큼의 숫자를 가지고 있는가 성공`() {
        val lottoList = listOf("1","2","3","4","5","6")
        lottoList.checkLottoSize()
        assertThatNoException()
    }

    @Test
    fun `LOTTO_SIZE 개 만큼의 숫자를 가지고 있는가 실패`() {
        assertThrows<IllegalArgumentException> {
            val lottoList = listOf("1","2","3","4")
            lottoList.checkLottoSize()
        }
    }

    @Test
    fun `리스트에 중복되는 값이 없는가 성공`() {
        val lottoList = listOf("1","2","3","4","5","6")
        lottoList.checkDuplication()
        assertThatNoException()
    }

    @Test
    fun `리스트에 중복되는 값이 없는가 실패`() {
        assertThrows<IllegalArgumentException> {
            val lottoList = listOf("1","2","3","4","6","6")
            lottoList.checkDuplication()
        }
    }
}
