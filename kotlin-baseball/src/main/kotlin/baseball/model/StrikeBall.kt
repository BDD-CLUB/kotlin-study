package baseball.model

data class StrikeBall(val strike: Int, val ball: Int) {
    override fun toString() =
        buildString {
            if (ball != 0) {
                append("${ball}볼 ")
            }
            if (strike != 0) {
                append("${strike}스트라이크 ")
            }
            if (strike == 0 && ball == 0) {
                append("낫싱")
            }
            return toString()
        }
}
