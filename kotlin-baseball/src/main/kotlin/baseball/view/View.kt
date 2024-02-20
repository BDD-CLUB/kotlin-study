package baseball.view

import baseball.model.NUMBER_LENGTH
import baseball.model.Number
import baseball.model.Numbers
import baseball.model.StrikeBall
import baseball.model.UserContinueGame
import camp.nextstep.edu.missionutils.Console

class View {
    fun printStartMessage() {
        println("숫자 야구 게임을 시작합니다.")
    }

    fun getUserNumbers(): Numbers {
        print("숫자를 입력해주세요 : ")
        return Numbers(Console.readLine().map { Number(it.digitToInt()) })
    }

    fun getUserContinueGame() = UserContinueGame.of(Console.readLine())

    fun printStrikeBall(strikeBall: StrikeBall) {
        println(strikeBall)
    }

    fun printEndMessage() {
        println("${NUMBER_LENGTH}개의 숫자를 모두 맞히셨습니다! 게임 종료")
        println("게임을 새로 시작하려면 ${UserContinueGame.RESTART.value}, 종료하려면  ${UserContinueGame.END.value}를 입력하세요.")
    }
}
