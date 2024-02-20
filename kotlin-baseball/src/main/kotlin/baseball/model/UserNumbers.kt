package baseball.model

class UserNumbers(private val numbers: List<Int>) {

    init {
        require(numbers.size == NUMBERS_SIZE)
        require(numbers.any { it in MIN_NUMBER..MAX_NUMBER })
        require(numbers.toSet().size == numbers.size)
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
