package baseball.model

import camp.nextstep.edu.missionutils.Randoms.pickUniqueNumbersInRange

class RandomNumbers {

    val numbers: List<Int> = pickUniqueNumbersInRange(MIN_NUMBER, MAX_NUMBER, NUMBERS_SIZE)

    fun contains(number: Int): Boolean {
        return numbers.contains(number)
    }
}
