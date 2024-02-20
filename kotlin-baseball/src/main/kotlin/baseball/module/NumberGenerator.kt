package baseball.module

interface NumberGenerator {
    fun generateRandomNumber(): Number
    fun generateThreeRandomNumbers(): List<Number>

    companion object {
        const val MIN_VALUE: Int = 1
        const val MAX_VALUE: Int = 9
    }
}