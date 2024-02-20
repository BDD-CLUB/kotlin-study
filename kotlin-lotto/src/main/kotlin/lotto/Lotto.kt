package lotto

import camp.nextstep.edu.missionutils.Randoms

class Lotto(
    val numbers: List<Int> = Randoms.pickUniqueNumbersInRange(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER, LOTTO_NUMBERS_COUNT)
) {
    init {
        require(numbers.size == LOTTO_NUMBERS_COUNT)
    }

    val sortedLotto
        get() = numbers.sorted()

}
