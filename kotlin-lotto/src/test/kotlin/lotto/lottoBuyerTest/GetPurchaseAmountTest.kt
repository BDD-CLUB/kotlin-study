package lotto.lottoBuyerTest

import camp.nextstep.edu.missionutils.test.NsTest
import lotto.LottoBuyer
import lotto.util.ERROR_PREFIX
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GetPurchaseAmountTest : NsTest() {

    private val lottoBuyer = LottoBuyer()
    private val successInput = "10000"

    @Test
    fun `구매 금액 입력 성공`() {
        val input = "1000"
        run(input)

        val result = lottoBuyer.lottoBuyerDTO.purchaseAmount
        val expected = 1

        assertEquals(expected, result)
    }

    @Test
    fun `구매 금액 입력 실패 - 정수가 아님`() {
        val input = "hello"
        run(input, successInput)
        assertTrue(output().contains(ERROR_PREFIX))
    }

    @Test
    fun `구매 금액 입력 실패 - LOTTO_AMOUNT 으로 안 떨어짐`() {
        val input = "12345"
        run(input, successInput)
        assertTrue(output().contains(ERROR_PREFIX))
    }

    override fun runMain() {
        lottoBuyer.getPurchaseAmount()
    }
}
