package baseball.common

enum class GameMessage(val message: String) {
    START_GAME("숫자 야구 게임을 시작합니다."),
    INPUT_PROMPT("숫자를 입력해주세요 : "),
    GAME_OVER("3개의 숫자를 모두 맞히셨습니다! 게임 종료"),
    PLAY_AGAIN_OR_END("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요."),
    RESULT_FORMAT("%d볼 %d스트라이크");

    fun print(strike: Int? = null, ball: Int? = null) {
        when (this) {
            START_GAME, GAME_OVER -> println(message)
            INPUT_PROMPT, PLAY_AGAIN_OR_END -> print(message)
            RESULT_FORMAT -> {
                val formattedMessage = if (strike == 0 && ball == 0) {
                    "낫싱"
                } else if (strike == 0) {
                    "%d볼".format(ball)
                } else if (ball == 0) {
                    "%d스트라이크".format(strike)
                } else {
                    message.format(strike, ball)
                }
                println(formattedMessage)
            }
        }
    }
}

