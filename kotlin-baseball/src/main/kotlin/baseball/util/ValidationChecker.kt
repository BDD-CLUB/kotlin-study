package baseball.util

class ValidationChecker {

    private var target = ""

    fun checkGameNumber(input: String){
        target = input
        numberCheck()
        lengthCheck()
        duplicationCheck()
    }

    fun checkExitNumber(input: String){
        if (input != "1" && input != "2") {
            throw IllegalArgumentException("1 또는 2를 입력해야 합니다.")
        }
    }

    private fun numberCheck() {
        val isInvalid = target.any { it !in '1'..'9' }
        if (isInvalid) {
            throw IllegalArgumentException("모든 입력값은 1~9 사이의 숫자여야 합니다.")
        }
    }

    private fun lengthCheck() {
        val isInvalid = target.length != 3
        if (isInvalid) {
            throw IllegalArgumentException("입력된 숫자는 3자리 수여야 합니다.")
        }
    }

    private fun duplicationCheck() {
        val isInvalid = target.length != target.toMutableList().distinct().size
        if (isInvalid) {
            throw IllegalArgumentException("중복된 숫자는 입력되면 안됩니다.")
        }
    }
}