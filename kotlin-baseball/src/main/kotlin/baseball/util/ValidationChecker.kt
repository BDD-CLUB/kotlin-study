package baseball.util

class ValidationChecker {

    fun checkGameNumber(input: String) {
        require(input.length == NUM_COUNT)
        require(input.length == input.toMutableList().distinct().size)
        require(input.all { it in MIN_VALUE.digitToChar() ..MAX_VALUE.digitToChar() })
    }

    fun checkExitNumber(input: String) {
        require(input == RESTART_NUM.toString() || input == EXIT_NUM.toString())
    }

}
