package baseball

import baseball.model.GameResult
import baseball.model.GameStatus.RESTART
import baseball.model.GameStatus.STOP
import baseball.model.NUMBERS_SIZE
import camp.nextstep.edu.missionutils.Console

fun printStartMessage() {
    println("숫자 야구 게임을 시작합니다.")
}

fun readNumbers(): List<Int> {
    print("숫자를 입력해주세요 : ")
    try {
        return Console.readLine().map { it.toString().toInt() }
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException("유효하지 않은 숫자 형식입니다.")
    }
}

fun readNumber(): Int {
    try {
        return Console.readLine().toString().toInt()
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException("유효하지 않은 숫자 형식입니다.")
    }
}

fun printResult(gameResult: GameResult) {
    when {
        gameResult.isAllStrike -> {
            println(
                """
               ${NUMBERS_SIZE}스트라이크
                ${NUMBERS_SIZE}개의 숫자를 모두 맞히셨습니다! 게임 종료
                게임을 새로 시작하려면 ${RESTART.status}, 종료하려면 ${STOP.status}를 입력하세요.
                """.trimIndent()
            )
        }

        gameResult.isNoting -> println("낫싱")
        gameResult.isOnlyBall -> println("${gameResult.ball}볼")
        gameResult.isOnlyStrike -> println("${gameResult.strike}스트라이크")
        else -> println("${gameResult.ball}볼 ${gameResult.strike}스트라이크")
    }
}
