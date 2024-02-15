package step2.view

import step2.dto.BaseballResultDto

class BaseballView {

    fun announceBaseballGameStart() = println("숫자 야구 게임을 시작합니다.")

    fun printReceiveNumberMessage() = print("숫자를 입력해주세요 : ")
    fun announceGameResult(baseballGameResult: BaseballResultDto) =
            println(
                    with(baseballGameResult) {
                        listOfNotNull(
                                ballCount.takeIf { it > 0 }?.let { "${it}볼" },
                                strikeCount.takeIf { it > 0 }?.let { "${it}스트라이크" }
                        ).takeIf { it.isNotEmpty() }?.joinToString(" ") ?: "낫싱"
                    }
            )

}