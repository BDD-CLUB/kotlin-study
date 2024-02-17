package baseball.data

data class Result(
    val ball: Int,
    val strike: Int
) {
    override fun toString(): String {
        val ball = this.ball
        val strike = this.strike

        return when {
            (ball == 0 && strike == 0) -> "낫싱"
            (ball == 0) -> "${strike}스트라이크"
            (strike == 0) -> "${ball}볼"
            else -> "${ball}볼 ${strike}스트라이크"
        }
    }
}
