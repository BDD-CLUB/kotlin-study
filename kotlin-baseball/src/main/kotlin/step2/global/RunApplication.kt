package step2.global

import camp.nextstep.edu.missionutils.Console
import camp.nextstep.edu.missionutils.Randoms
import step2.api.BaseballController
import step2.entity.RandomNumbers
import step2.view.BaseballView
import kotlin.system.exitProcess

class BaseballApplication(
        val baseballView: BaseballView,
        val baseballController: BaseballController
) {

    fun run() {
        // STEP1 > 게임 시작 문구 출력
        baseballView.announceBaseballGameStart()

        while (true) {
            // STEP2 > 랜덤한 숫자 저장
            baseballController.saveRandomNumbers()

            // STEP3 > 숫자 입력 메시지 출력
            baseballView.printReceiveNumberMessage()

            // STEP4 > 숫자 입력
            val userInputNumbers = getThreeUniqueNumbers()

            // STEP5 > 결과 출력
            val baseballGameResult = baseballController.getBaseballGameResult(userInputNumbers)

            // STEP6 > 게임 종료 문구 출력
            baseballView.announceGameResult(baseballGameResult)

            // STEP7 > 새로 시작 or 종료
            baseballGameResult.strikeCount.takeIf { it == 3 }
                    ?.apply { println("""
                        3개의 숫자를 모두 맞히셨습니다! 게임 종료
                        게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.
                    """.trimIndent()) }
                    ?.apply { Console.readLine()
                            .takeIf { it == "2" }
                            ?.apply { exitProcess(0) } }

        }
    }

}