package baseball.model

class UserNumbers(private val numbers: List<Int>) {

    init {
        validateSize(numbers)
        validateRange(numbers)
        validateDuplicate(numbers)
    }

    private fun validateSize(numbers: List<Int>) {
        if (numbers.size != NUMBERS_SIZE) {
            throw IllegalArgumentException("${NUMBERS_SIZE}개의 숫자를 입력해주세요.")
        }
    }

    private fun validateRange(numbers: List<Int>) {
        if (numbers.any { it < MIN_NUMBER || it > MAX_NUMBER }) {
            throw IllegalArgumentException("숫자의 범위는 ${MIN_NUMBER}이상 ${MAX_NUMBER}이하입니다.")
        }
    }

    private fun validateDuplicate(numbers: List<Int>) {
        if (numbers.toSet().size != numbers.size) {
            throw IllegalArgumentException("숫자는 중복될 수 없습니다.")
        }
    }

    infix fun compareTo(otherNumbers: List<Int>): GameResult {
        var strike = 0
        var ball = 0

        for (index in numbers.indices) {
            val (userNumber, otherNumber) = numbers[index] to otherNumbers[index]
            if (userNumber == otherNumber) {
                strike++
            } else if (otherNumbers.contains(userNumber)) {
                ball++
            }
        }
        return GameResult(strike = strike, ball = ball)
    }
}
