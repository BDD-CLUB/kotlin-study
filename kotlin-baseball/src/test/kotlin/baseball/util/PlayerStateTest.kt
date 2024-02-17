package baseball.util

import baseball.data.PlayerState
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayerStateTest {

    @Test
    fun `재시작일 경우(1 입력)` () {
        val value = 1
        val result = PlayerState.from(value)
        val expected = PlayerState.IN_PROGRESS
        assertEquals(expected, result)
    }

    @Test
    fun `종료일 경우(2 입력)` () {
        val value = 2
        val result = PlayerState.from(value)
        val expected = PlayerState.EXIT
        assertEquals(expected, result)
    }

    @Test
    fun `이외의 값 입력된 경우` () {
        assertThrows<IllegalArgumentException> {
            val value = 0
            val result = PlayerState from value
        }
    }

}
