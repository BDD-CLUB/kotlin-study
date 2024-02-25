package lotto.lottoBuyerTest

import camp.nextstep.edu.missionutils.test.NsTest
import lotto.LottoBuyer
import lotto._const.ERROR_PREFIX
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GetBonusNumberTest : NsTest() {

    private val lottoBuyer = LottoBuyer()
    private val successInput = "10"

    @Test
    fun `보너스 번호 입력 성공`() {
        val input = "1"
        run(input)

        val result = lottoBuyer.lottoBuyerDTO.bonusNumber
        val expected = 1

        assertEquals(expected, result)
    }

    @Test
    fun `보너스 번호 입력 실패 - 정수가 아님`() {
        val input = "hello"
        run(input, successInput)
        assertTrue(output().contains(ERROR_PREFIX))
    }

    @Test
    fun `보너스 번호 입력 실패 - 범위 밖의 수 입력`() {
        val input = "12345"
        run(input, successInput)
        assertTrue(output().contains(ERROR_PREFIX))
    }

    override fun runMain() {
        lottoBuyer.getBonusNumber()
    }

}
