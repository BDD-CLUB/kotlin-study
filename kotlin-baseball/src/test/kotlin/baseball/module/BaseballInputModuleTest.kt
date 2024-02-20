package baseball.module

import baseball.main
import camp.nextstep.edu.missionutils.test.Assertions
import camp.nextstep.edu.missionutils.test.NsTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BaseballInputModuleTest : NsTest() {
    private lateinit var baseballInputModule: BaseballInputModule

    @BeforeEach
    fun setUp() {
        baseballInputModule = BaseballInputModule()
    }

    @Test
    fun `입력은 3자리를 초과하면 안된다`() {
        Assertions.assertSimpleTest {
            assertThrows<IllegalArgumentException> { runException("1234") }
        }
    }

    @Test
    fun `입력은 3자리 미만이면 안된다`() {
        Assertions.assertSimpleTest {
            assertThrows<IllegalArgumentException> { runException("12") }
        }
    }

    @Test
    fun `입력은 null이면 안된다`() {
        Assertions.assertSimpleTest {
            assertThrows<IllegalArgumentException> { runException(null) }
        }
    }

    @Test
    fun `입력은 숫자가 아니면 안된다`() {
        Assertions.assertSimpleTest {
            assertThrows<IllegalArgumentException> { runException("@@@") }
        }
    }
    

    override fun runMain() {
        main()
    }
}