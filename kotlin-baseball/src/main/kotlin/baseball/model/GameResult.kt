package baseball.model

data class GameResult(
        val strike: Int,
        val ball: Int,
) {
    val isAllStrike: Boolean = strike == NUMBERS_SIZE
    val isNoting: Boolean = ball == 0 && strike == 0
    val isOnlyBall: Boolean = strike == 0 && ball > 0
    val isOnlyStrike: Boolean = ball == 0 && strike > 0
}
