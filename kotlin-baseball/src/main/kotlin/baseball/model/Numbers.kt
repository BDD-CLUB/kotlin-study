package baseball.model

import camp.nextstep.edu.missionutils.Randoms

const val MIN_NUMBER_INCLUDE = 1
const val MAX_NUMBER_INCLUDE = 9
const val NUMBER_LENGTH = 3

class Numbers(input: String) {
    private val numbers: List<Number> = input.map { Number(it.digitToInt()) }

    companion object {
        fun generateRandom() =
            Numbers(
                mutableListOf<Int>().apply {
                    while (size != NUMBER_LENGTH) {
                        val pickNumber = Randoms.pickNumberInRange(MIN_NUMBER_INCLUDE, MAX_NUMBER_INCLUDE)
                        if (pickNumber !in this) {
                            this.add(pickNumber)
                        }
                    }
                }.joinToString("")
            )
    }

    init {
        require(numbers.size == NUMBER_LENGTH)
        require(numbers.distinct().size == numbers.size)
    }

    fun compare(other: Numbers): StrikeBall {
        val strike = getStrike(other)
        val ball = getBall(other, strike)
        return StrikeBall(strike, ball)
    }

    fun getStrike(other: Numbers) =
        (0 until NUMBER_LENGTH)
            .count { this.numbers[it] == other.numbers[it] }

    fun getBall(other: Numbers, strike: Int) =
        (0 until NUMBER_LENGTH)
            .count { i -> isBall(i, other) }

    private fun isBall(i: Int, other: Numbers) =
        (0 until NUMBER_LENGTH)
            .any { j ->
                if (i == j) return@any false
                this.numbers[i] == other.numbers[j]
            }
}

data class Number(val number: Int) {
    init {
        require(number in MIN_NUMBER_INCLUDE..MAX_NUMBER_INCLUDE)
    }
}
