package baseball

class ValidationChecker {

    private var target = ""

    fun checkGameNumber(input: String){
        target = input
        isAllNumber()
        isLengthValid()
        isNotDuplicated()
    }

    fun checkExitNumber(input: String){
        if (input != "1" && input != "2") {
            throw IllegalArgumentException("1 또는 2를 입력해야 합니다.")
        }
    }

    private fun isAllNumber() {
        val isAllNumber = target.all { it in '1'..'9' }
        if (!isAllNumber) {
            throw IllegalArgumentException("모든 입력값은 1~9 사이의 숫자여야 합니다.")
        }
    }

    private fun isLengthValid() {
        val isLengthValid = target.length == 3
        if (!isLengthValid) {
            throw IllegalArgumentException("입력된 숫자는 3자리 수여야 합니다.")
        }
    }

    private fun isNotDuplicated() {
        val isNotDuplicated = target.length == target.toMutableList().distinct().size
        if (!isNotDuplicated) {
            throw IllegalArgumentException("중복된 숫자는 입력되면 안됩니다.")
        }
    }
}