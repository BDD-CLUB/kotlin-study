package baseball

import camp.nextstep.edu.missionutils.test.NsTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class OpponentTest : NsTest() {

    private val opponent = Opponent()

    @Test
    fun `상대방 숫자 선택`() {
        opponent.getAnswerNumber()
        val result = opponent.answerNumber
        val isValid = result.size == 3 && result.all { it in 1..9 }
                && result.distinct().size == result.size
        assertTrue(isValid)
    }

    override fun runMain() {
    }
}