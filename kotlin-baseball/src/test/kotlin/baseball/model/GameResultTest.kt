package baseball.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameResultTest {

    @Test
    fun `모든 스트라이크일 경우의 결과를 반환한다`() {
        // given
        val result = GameResult(strike = 3, ball = 0)

        // when
        val isAllStrike = result.isAllStrike
        val isOnlyStrike = result.isOnlyStrike
        val isNoting = result.isNoting
        val isOnlyBall = result.isOnlyBall

        // then
        assertThat(isAllStrike).isTrue
        assertThat(isOnlyStrike).isTrue
        assertThat(isNoting).isFalse
        assertThat(isOnlyBall).isFalse
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
        assertThat(isNoting).isTrue
        assertThat(isAllStrike).isFalse
        assertThat(isOnlyBall).isFalse
        assertThat(isOnlyStrike).isFalse
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
        assertThat(isOnlyBall).isTrue
        assertThat(isNoting).isFalse
        assertThat(isAllStrike).isFalse
        assertThat(isOnlyStrike).isFalse
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
        assertThat(isOnlyStrike).isTrue
        assertThat(isOnlyBall).isFalse
        assertThat(isNoting).isFalse
        assertThat(isAllStrike).isFalse
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
        assertThat(isOnlyStrike).isFalse
        assertThat(isOnlyBall).isFalse
        assertThat(isNoting).isFalse
        assertThat(isAllStrike).isFalse
    }
}
