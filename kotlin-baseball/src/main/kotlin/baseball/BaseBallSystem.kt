package baseball

import baseball.data.PlayerState
import baseball.util.*

class BaseBallSystem {

    private val player = Player()
    private val opponent = Opponent()
    private val calculator = Calculator()

    fun execute() {
        printsStartGameMessage()
        while(player.state == PlayerState.IN_PROGRESS){
            // 1. 상대방: 정답 숫자 선택
            opponent.getAnswerNumber()

            // 턴 시작
            var isTurnInProgress = true
            while (isTurnInProgress) {
                // 2. 플레이어: 게임 숫자 입력
                printGetGameNumberMessage()
                player.getGameNumber()

                // 3. 계산기: 결과 계산
                val result = calculator.getResult(player.gameNumber, opponent.answerNumber)
                printResult(result)

                // 4.
                // `오답인 경우` -> 2번으로 돌아가 성공할 때까지 계속 반복
                // `정답(3 스트라이크)인 경우` -> 플레이어에게 종료 여부 물어봄
                //      - 재시작: 1번으로 돌아감
                //      - 종료: 프로그램 종료
                val isAllStrike = result.strike == NUM_COUNT
                if (isAllStrike) {
                    printSuccessMessage()
                    printGetExitNumberMessage()
                    player.getExitNumber()
                    isTurnInProgress = false
                }
            }
        }
    }
}
