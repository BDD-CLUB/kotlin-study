package baseball.util

import baseball.main
import camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest
import camp.nextstep.edu.missionutils.test.NsTest
import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ValidationCheckerTest : NsTest() {

    private val validationChecker = ValidationChecker()

    // ----------------  게임 숫자 입력 테스트 -------------------
    @Test
    fun `게임 숫자 입력 - 성공`() {
        val input = "123"
        validationChecker.checkGameNumber(input)
        assertThatNoException()
    }

    @Test
    fun `게임 숫자 입력 - 실패(숫자 이외의 값 입력)`() {
        assertSimpleTest {
            assertThrows<IllegalArgumentException> {
                val input = "sdf"
                validationChecker.checkGameNumber(input)
            }
        }
    }

    @Test
    fun `게임 숫자 입력 - 실패(잘못된 길이 입력)`() {
        assertSimpleTest {
            assertThrows<IllegalArgumentException> {
                val input = "123425"
                validationChecker.checkGameNumber(input)
            }
        }
    }

    @Test
    fun `게임 숫자 입력 - 실패(중복된 값 입력)`() {
        assertSimpleTest {
            assertThrows<IllegalArgumentException> {
                val input = "111"
                validationChecker.checkGameNumber(input)
            }
        }
    }

    // ----------------  종료 숫자 입력 테스트 -------------------
    @Test
    fun `종료 숫자 입력 - 성공`() {
        val input = "1"
        validationChecker.checkExitNumber(input)
        assertThatNoException()
    }

    @Test
    fun `종료 숫자 입력 - 실패(1 또는 2가 아닌 다른 값 읿력)`() {
        assertSimpleTest {
            assertThrows<IllegalArgumentException> {
                val input = "hello"
                validationChecker.checkExitNumber(input)
            }
        }
    }


    override fun runMain() {
        main()
    }
}
