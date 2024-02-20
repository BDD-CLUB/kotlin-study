package baseball.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class GameResultTest {

    @Test
    fun `모든 스트라이크일 경우의 결과를 반환한다`() {
        // given
        val result = GameResult(strike = NUMBERS_SIZE, ball = 0)

        // when
        val isAllStrike = result.isAllStrike
        val isOnlyStrike = result.isOnlyStrike
        val isNoting = result.isNoting
        val isOnlyBall = result.isOnlyBall

        // then
        assertTrue(isAllStrike)
        assertTrue(isOnlyStrike)
        assertFalse(isNoting)
        assertFalse(isOnlyBall)
    }

    @Test
    fun `낫싱 결과를 반환한다`() {
        // given
        val result = GameResult(strike = 0, ball = 0)

        // when
        val isNoting = result.isNoting
        val isAllStrike = result.isAllStrike
        val isOnlyBall = result.isOnlyBall
        val isOnlyStrike = result.isOnlyStrike

        // then
        assertTrue(isNoting)
        assertFalse(isAllStrike)
        assertFalse(isOnlyBall)
        assertFalse(isOnlyStrike)
    }

    @Test
    fun `볼만 있을 경우의 결과를 반환한다`() {
        // given
        val result = GameResult(strike = 0, ball = 1)

        // when
        val isOnlyBall = result.isOnlyBall
        val isNoting = result.isNoting
        val isAllStrike = result.isAllStrike
        val isOnlyStrike = result.isOnlyStrike

        // then
        assertTrue(isOnlyBall)
        assertFalse(isNoting)
        assertFalse(isAllStrike)
        assertFalse(isOnlyStrike)
    }

    @Test
    fun `스트라이크만 있을 경우의 결과를 반환한다`() {
        // given
        val result = GameResult(strike = 1, ball = 0)

        // when
        val isOnlyStrike = result.isOnlyStrike
        val isOnlyBall = result.isOnlyBall
        val isNoting = result.isNoting
        val isAllStrike = result.isAllStrike

        // then
        assertTrue(isOnlyStrike)
        assertFalse(isOnlyBall)
        assertFalse(isNoting)
        assertFalse(isAllStrike)
    }

    @Test
    fun `볼과 스트라이크 둘 다 있을 경우의 결과를 반환한다`() {
        // given
        val result = GameResult(strike = 1, ball = 1)

        // when
        val isOnlyStrike = result.isOnlyStrike
        val isOnlyBall = result.isOnlyBall
        val isNoting = result.isNoting
        val isAllStrike = result.isAllStrike

        // then
        assertFalse(isOnlyStrike)
        assertFalse(isOnlyBall)
        assertFalse(isNoting)
        assertFalse(isAllStrike)
    }
}
