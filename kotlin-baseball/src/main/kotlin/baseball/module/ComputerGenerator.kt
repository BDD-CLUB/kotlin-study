package baseball.module

import baseball.module.NumberGenerator.Companion.MAX_VALUE
import baseball.module.NumberGenerator.Companion.MIN_VALUE
import camp.nextstep.edu.missionutils.Randoms

class ComputerGenerator : NumberGenerator {
    override fun generateRandomNumber(): Number {
        return Randoms.pickNumberInRange(MIN_VALUE, MAX_VALUE)
    }

    override fun generateThreeRandomNumbers(): List<Number> {
        val numbers = mutableSetOf<Number>()
        while (numbers.size < 3) {
            val number = generateRandomNumber()
            numbers.add(number)
        }
        return numbers.toList()
    }
}