package baseball

import camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest
import camp.nextstep.edu.missionutils.test.NsTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayerGetGameNumberTest : NsTest() {

    private val player = Player()

    @Test
    fun `게임 숫자 입력 - 성공`() {
        val input = "135"
        run(input)

        val result = player.gameNumber
        val expected = mutableListOf(1, 3, 5)

        assertEquals(expected, result)
    }

    @Test
    fun `게임 숫자 입력 - 실패(1~9 숫자 이외의 값 입력) `() {
        assertSimpleTest {
            assertThrows<IllegalArgumentException> {
                val input = "012"
                run(input)
            }
        }
    }

    @Test
    fun `게임 숫자 입력 - 실패(잘못된 길이 입력) `() {
        assertSimpleTest {
            assertThrows<IllegalArgumentException> {
                val input = "1"
                run(input)
            }
        }
    }

    @Test
    fun `게임 숫자 입력 - 실패(중복된 값 입력) `() {
        assertSimpleTest {
            assertThrows<IllegalArgumentException> {
                val input = "122"
                run(input)
            }
        }
    }

    override fun runMain() {
        player.getGameNumber()
    }

}
