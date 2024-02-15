package baseball

import baseball.NumberGenerator.Companion.MAX_VALUE
import baseball.NumberGenerator.Companion.MIN_VALUE
import camp.nextstep.edu.missionutils.Randoms

class ComputerGenerator : NumberGenerator {
    override fun generateRandomNumber(): Int {
        return Randoms.pickNumberInRange(MIN_VALUE, MAX_VALUE)
    }

    override fun generateThreeRandomNumbers(): List<Int> {
        val numbers = mutableListOf<Int>()
        for (i in 1..3) {
            numbers.add(generateRandomNumber())
        }
        return numbers
    }
}