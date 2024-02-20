package baseball

import camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest
import camp.nextstep.edu.missionutils.test.NsTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ViewTest : NsTest() {

    @Test
    fun `숫자가 아닌 입력은 예외를 발생시킨다`() {
        assertSimpleTest {
            assertThrows<IllegalArgumentException> { runException("asdf") }
        }
    }

    @Test
    fun `공백 입력은 예외를 발생시킨다`() {
        assertSimpleTest {
            assertThrows<IllegalArgumentException> { runException("  ") }
        }
    }

    override fun runMain() {
        main()
    }
}
