package baseball.util

import baseball.data.Result

fun printsStartGameMessage() = println("숫자 야구 게임을 시작합니다.")

fun printGetGameNumberMessage() = print("숫자를 입력해주세요 : ")

fun printResult(result: Result) = println(result)

fun printSuccessMessage() = println("${NUM_COUNT}개의 숫자를 모두 맞히셨습니다! 게임 종료")

fun printGetExitNumberMessage() {
    println("게임을 새로 시작하려면 ${RESTART_NUM}, 종료하려면 ${EXIT_NUM}를 입력하세요.")
}
