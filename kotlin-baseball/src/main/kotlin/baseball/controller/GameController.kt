package baseball.controller

import baseball.model.*
import baseball.model.GameStatus.STOP
import baseball.printResult
import baseball.printStartMessage
import baseball.readNumber
import baseball.readNumbers

class GameController(
        private val computerNumbers: GameNumbers,
) {
    fun run() {
        printStartMessage()
        while (true) {
            computerNumbers.setNumbers()
            while (true) {
                val result = gameAndGetResult()
                if (result.isAllStrike) {
                    break
                }
            }
            if (askRestartOrStop() == STOP) {
                break
            }
        }
    }

    private fun gameAndGetResult(): GameResult {
        val userNumbers = UserNumbers(readNumbers())
        val result = userNumbers compareTo computerNumbers.get()
        printResult(result)
        return result
    }

    private fun askRestartOrStop(): GameStatus = GameStatus.from(readNumber())
}
