package baseball

import baseball.model.GameResult
import baseball.model.GameStatus
import baseball.model.GameStatus.STOP
import baseball.model.RandomNumbers
import baseball.model.UserNumbers

fun run() {
    printStartMessage()
    while (true) {
        val randomNumbers = RandomNumbers()
        while (true) {
            println(randomNumbers.numbers)
            val result = gameAndGetResult(randomNumbers)
            if (result.isAllStrike) {
                break
            }
        }
        if (askRetryOrStop() == STOP) {
            break
        }
    }
}

fun gameAndGetResult(randomNumbers: RandomNumbers): GameResult {
    val userNumbers = UserNumbers(readNumbers())
    val result = userNumbers compareTo randomNumbers
    printResult(result)
    return result
}

fun askRetryOrStop(): GameStatus {
    return GameStatus.from(readNumber())
}
