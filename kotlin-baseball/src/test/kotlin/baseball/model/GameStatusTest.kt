package baseball.model

import baseball.model.GameStatus.RESTART
import baseball.model.GameStatus.STOP
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GameStatusTest {

    @Test
    fun `재시작 입력에 대한 값을 반환한다`() {
        //given
        val restartSignal = 1

        //when
        val status = GameStatus.from(restartSignal)

        //then
        assertEquals(status, RESTART)
    }

    @Test
    fun `종료 입력에 대한 값을 반환한다`() {
        //given
        val stopSignal = 2

        //when
        val status = GameStatus.from(stopSignal)

        //then
        assertEquals(status, STOP)
    }
}
