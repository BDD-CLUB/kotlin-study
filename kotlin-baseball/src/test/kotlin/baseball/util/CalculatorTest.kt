package baseball.util

import baseball.data.Result
import camp.nextstep.edu.missionutils.test.NsTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CalculatorTest : NsTest() {

    private val calculator = Calculator()

    @Test
    fun `스트라이크만 있는 경우`() {
        val player = mutableListOf(1, 2, 3)
        val opponent = mutableListOf(1, 2, 4)

        val result = calculator.getResult(player, opponent)
        val expected = Result(0,2)
        assertEquals(expected, result)
    }

    @Test
    fun `볼만 있는 경우`() {
        val player = mutableListOf(1, 2, 3)
        val opponent = mutableListOf(3, 1, 2)

        val result = calculator.getResult(player, opponent)
        val expected = Result(3,0)
        assertEquals(expected, result)
    }

    @Test
    fun `스트라이크와 볼이 함께 나타난 경우`() {
        val player = mutableListOf(1, 2, 3)
        val opponent = mutableListOf(1, 3, 2)

        val result = calculator.getResult(player, opponent)
        val expected = Result(2,1)
        assertEquals(expected, result)
    }

    @Test
    fun `낫싱인 경우`() {
        val player = mutableListOf(1, 2, 3)
        val opponent = mutableListOf(4, 5, 6)

        val result = calculator.getResult(player, opponent)
        val expected = Result(0,0)
        assertEquals(expected, result)
    }

    override fun runMain() {
    }
}