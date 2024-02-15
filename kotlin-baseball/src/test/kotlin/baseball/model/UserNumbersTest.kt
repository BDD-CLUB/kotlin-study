package baseball.model

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class UserNumbersTest {

    @Test
    fun `유저 숫자의 개수가 유효하지 않다면 실패한다`() {
        //given
        val invalidList = listOf(1, 2, 3, 4)

        //when & then
        assertThrows<IllegalArgumentException> {
            UserNumbers(invalidList)
        }
    }

    @Test
    fun `유저 숫자의 범위가 유효하지 않다면 실패한다`() {
        //given
        val invalidList = listOf(MAX_NUMBER + 1, 2, 3)

        //when & then
        assertThrows<IllegalArgumentException> {
            UserNumbers(invalidList)
        }
    }


    @Test
    fun `유저 숫자에 중복이 있다면 실패한다`() {
        //given
        val invalidList = listOf(1, 1, 2)

        //when & then
        assertThrows<IllegalArgumentException> {
            UserNumbers(invalidList)
        }
    }
}
