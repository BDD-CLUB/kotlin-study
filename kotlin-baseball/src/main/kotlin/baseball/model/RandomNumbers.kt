package baseball.model

import camp.nextstep.edu.missionutils.Randoms.pickUniqueNumbersInRange

class RandomNumbers : GameNumbers {

    private var numbers: List<Int>

    init {
        numbers = generate()
    }

    fun contains(number: Int): Boolean {
        return numbers.contains(number)
    }

    override fun get(): List<Int> {
        return numbers
    }

    override fun generate(): List<Int> {
        return pickUniqueNumbersInRange(MIN_NUMBER, MAX_NUMBER, NUMBERS_SIZE)
    }

    override fun setNumbers() {
        numbers = generate()
    }
}
