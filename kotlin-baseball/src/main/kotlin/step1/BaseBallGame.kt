package step1

import camp.nextstep.edu.missionutils.Randoms

fun announceBaseballGameStartMessage() = println("숫자 야구 게임을 시작합니다.")

fun announceEnterNumbersMessage() = print("숫자를 입력해주세요 : ")

fun announceThreeStrike() = println("3개의 숫자를 모두 맞히셨습니다! 게임 종료")

fun announceBaseballRestartMessage() = println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.")

fun announceBaseballResult(userInputNumbers: String, randomNumbers: String) = println(calculateBaseballGameResult(userInputNumbers, randomNumbers))

fun pickRandomNumbers(): String {
    val randomNumbers = mutableListOf<Int>()
    while (randomNumbers.size < 3) {
        val randomNumber = Randoms.pickNumberInRange(1, 9)
        if (!randomNumbers.contains(randomNumber)) {
            randomNumbers.add(randomNumber)
        }
    }
    return randomNumbers.joinToString(separator = "")
}

fun isUserInputValid(userInputNumbers: String): Boolean = userInputNumbers.length == 3 && userInputNumbers.toIntOrNull() != null


private fun calculateBaseballGameResult(userInputNumbers: String, randomNumbers: String): String {
    val result = StringBuilder()
    var strikeCount = 0
    var ballCount = 0

    for (i in randomNumbers.indices) {
        if (userInputNumbers[i] == randomNumbers[i]) {
            strikeCount++
        } else if (userInputNumbers[i] in randomNumbers) {
            ballCount++
        }
    }

    if (ballCount > 0) {
        result.append("${ballCount}볼 ")
    }

    if (strikeCount > 0) {
        result.append("${strikeCount}스트라이크")
    }

    return if (ballCount == 0 && strikeCount == 0) "낫싱" else result.toString()

}