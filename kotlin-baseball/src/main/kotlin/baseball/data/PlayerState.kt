package baseball.data

enum class PlayerState(val state: Int) {
    IN_PROGRESS(1),
    EXIT(2);

    companion object {
        // state의 값에 따라 PlayerState를 선택함
        // ex) value에 2가 들어오면 EXIT
        // state의 값이 없으면 default는 IN_PROGRESS
        infix fun from(value: Int): PlayerState =
                PlayerState.values().firstOrNull { it.state == value } ?: IN_PROGRESS
    }
}