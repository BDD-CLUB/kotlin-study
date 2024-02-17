package baseball.util

import baseball.data.Result
import camp.nextstep.edu.missionutils.test.NsTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PrinterTest : NsTest() {

    @Test
    fun `낫싱`() {
        val result = Result(0,0)
        printResult(result)
        assertEquals("낫싱", output())
    }

    @Test
    fun `스트라이크만`() {
        val result = Result(0,2)
        printResult(result)
        assertEquals("2스트라이크", output())
    }

    @Test
    fun `볼만`() {
        val result = Result(2,0)
        printResult(result)
        assertEquals("2볼", output())
    }

    @Test
    fun `스트라이크, 볼 둘 다`() {
        val result = Result(1,1)
        printResult(result)
        assertEquals("1볼 1스트라이크", output())
    }

    override fun runMain() {

    }
}
