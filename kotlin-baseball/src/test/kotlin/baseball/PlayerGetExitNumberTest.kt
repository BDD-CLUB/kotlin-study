package baseball

import camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest
import camp.nextstep.edu.missionutils.test.NsTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayerGetExitNumberTest : NsTest() {

    private val player = Player()

    @Test
    fun `종료 숫자입력 - 성공`() {
        val input = "1"
        run(input)

        val result = player.exitNumber
        val expected = 1

        assertEquals(expected, result)
    }

    @Test
    fun `종료 숫자입력 - 실패(1, 2 이외의 값 입력) `() {
        assertSimpleTest {
            assertThrows<IllegalArgumentException> {
                val input = "0"
                run(input)
            }
        }
    }

    override fun runMain() {
        player.getExitNumber()
    }

}