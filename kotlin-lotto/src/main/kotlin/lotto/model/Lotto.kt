package lotto.model

import lotto.controller.LOTTO_NUMBERS_COUNT

class Lotto(
    val numbers: List<Int>
) {
    init {
        require(numbers.size == LOTTO_NUMBERS_COUNT)
        require(numbers.size == numbers.toSet().size)
    }

    val sortedLottoNumbers
        get() = numbers.sorted()

}
