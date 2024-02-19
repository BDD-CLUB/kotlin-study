package step1

import camp.nextstep.edu.missionutils.Console

fun stepOneRunner() {
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