package lotto.lottoBuyerTest

import camp.nextstep.edu.missionutils.test.NsTest
import lotto.LottoBuyer
import lotto._const.ERROR_PREFIX
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GetWinningNumberTest : NsTest() {

    private val lottoBuyer = LottoBuyer()
    private val successInput = "1,2,3,4,5,6"

    @Test
    fun `당첨 번호 입력 성공`() {
        val input = "12,23,25,32,44,45"
        run(input)

        val result = lottoBuyer.lottoBuyerDTO.winningNumber
        val expected = listOf(12, 23, 25, 32, 44, 45)

        assertEquals(expected, result)
    }

    @Test
    fun `당첨 번호 입력 실패 - 포맷이 안 맞음`() {
        val input = "[1,2,3,4,5,6]"
        run(input, successInput)
        assertTrue(output().contains(ERROR_PREFIX))
    }

    @Test
    fun `당첨 번호 입력 실패 - LOTTO_SIZE 불일치`() {
        val input = "1,2,3"
        run(input, successInput)
        assertTrue(output().contains(ERROR_PREFIX))
    }

    @Test
    fun `당첨 번호 입력 실패 - 중복`() {
        val input = "1,2,3,4,4,4"
        run(input, successInput)
        assertTrue(output().contains(ERROR_PREFIX))
    }

    @Test
    fun `당첨 번호 입력 실패 - 정수 아닌 값`() {
        val input = "a,b,c,d,2,f"
        run(input, successInput)
        assertTrue(output().contains(ERROR_PREFIX))
    }

    @Test
    fun `당첨 번호 입력 실패 - 범위 밖의 값`() {
        val input = "0,1,2,3,4,5"
        run(input, successInput)
        assertTrue(output().contains(ERROR_PREFIX))
    }

    override fun runMain() {
        lottoBuyer.getWinningNumber()
    }

}
