package baseball.model

enum class GameStatus(private val status: Int) {
    RESTART(1),
    STOP(2),
    ;

    companion object {
        fun from(status: Int): GameStatus {
            return values().firstOrNull { it.status == status }
                    ?: throw IllegalArgumentException("${status}는 유효하지 않은 입력입니다.")
        }
    }
}
