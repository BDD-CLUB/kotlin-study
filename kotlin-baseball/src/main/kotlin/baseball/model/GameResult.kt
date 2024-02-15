package baseball.model

data class GameResult(
    val strike: Int,
    val ball: Int,
) {
    val isStrike: Boolean = strike == 3
    val isNoting: Boolean = ball == 0 && strike == 0
    val isOnlyBall: Boolean = strike == 0 && ball >= 0
    val isOnlyStrike: Boolean = ball == 0 && strike >= 0
}
