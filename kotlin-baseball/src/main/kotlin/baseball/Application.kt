package baseball

import camp.nextstep.edu.missionutils.Console
import camp.nextstep.edu.missionutils.Randoms
import step1.*

fun main() {
    announceBaseballGameStartMessage()
    var randomNumbers = pickRandomNumbers()

    while (true) {
        announceEnterNumbersMessage()
        val readLine = Console.readLine()
        if (!isUserInputValid(readLine)) {
            throw IllegalArgumentException()
        }

        announceBaseballResult(readLine, randomNumbers)
        if (readLine.isThreeStrike(readLine, randomNumbers)) {
            announceThreeStrike()
            announceBaseballRestartMessage()
            if (Console.readLine() == "2") break
            randomNumbers = pickRandomNumbers()
        }
    }
}

fun String.isThreeStrike(numbers: String, randomNumbers: String): Boolean =
        numbers[0] == randomNumbers[0] && numbers[1] == randomNumbers[1] && numbers[2] == randomNumbers[2]
