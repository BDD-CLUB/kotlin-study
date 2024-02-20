package baseball.model

import camp.nextstep.edu.missionutils.Randoms

const val MIN_NUMBER_INCLUDE = 1
const val MAX_NUMBER_INCLUDE = 9
const val NUMBER_LENGTH = 3

data class Numbers(val numbers: List<Number>) {
    companion object {
        fun generateRandom() =
            Numbers(
                mutableListOf<Number>().apply {
                    while (size != NUMBER_LENGTH) {
                        val pickNumber = Number(Randoms.pickNumberInRange(MIN_NUMBER_INCLUDE, MAX_NUMBER_INCLUDE))
                        if (pickNumber !in this) {
                            this.add(pickNumber)
                        }
                    }
                }.toList()
            )
    }

    init {
        require(numbers.size == NUMBER_LENGTH)
        require(numbers.distinct().size == numbers.size)
    }

    fun compare(other: Numbers): StrikeBall {
        val strike = getStrike(other)
        val ball = getBall(other)
        return StrikeBall(strike, ball)
    }

    fun getStrike(other: Numbers) =
        (0 until NUMBER_LENGTH)
            .count { this.numbers[it] == other.numbers[it] }

    fun getBall(other: Numbers) =
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
