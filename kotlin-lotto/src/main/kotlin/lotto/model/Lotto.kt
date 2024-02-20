package lotto.model

import lotto.global.Component

class Lotto(private val numbers: List<Int>) {
    init {
        require(numbers.size == 6)
        require(numbers.distinct().size == numbers.size)
    }

    companion object {
        const val LOTTO_START_NUMBER = 1
        const val LOTTO_END_NUMBER = 45
        const val LOTTO_TOTAL_COUNT = 6
    }

    fun count(winningNumbers: List<Int>): Int =
            (numbers intersect winningNumbers.toSet()).size


    fun count(bonusNumber: Int): Int =
            if (numbers.contains(bonusNumber)) 1 else 0

    override fun toString(): String =
            numbers.toString()

}
