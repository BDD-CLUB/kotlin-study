package baseball.data

import baseball.util.*

enum class PlayerState(val state: Int) {
    IN_PROGRESS(RESTART_NUM),
    EXIT(EXIT_NUM),
    ;

    companion object {
        // state의 값에 따라 PlayerState를 선택함
        // ex) value에 2가 들어오면 EXIT
        infix fun from(value: Int): PlayerState =
                PlayerState.values().firstOrNull { it.state == value }
                    ?: throw IllegalArgumentException("$RESTART_NUM 또는 $EXIT_NUM 을 입력해주세요.")
    }
}
