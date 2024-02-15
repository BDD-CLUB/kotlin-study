package baseball.module

interface NumberGenerator {
    fun generateRandomNumber(): Int
    fun generateThreeRandomNumbers(): List<Int>

    companion object {
        const val MIN_VALUE: Int = 1
        const val MAX_VALUE: Int = 9
    }
}