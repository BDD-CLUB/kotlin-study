package lotto.model

import lotto.controller.LOTTO_NUMBERS_COUNT

class LottoWinningNumbers(
    val baseNumbers: List<Int>,
    val bonusNumber: Int,
) {
    init {
        require(baseNumbers.size == LOTTO_NUMBERS_COUNT)
        require(bonusNumber !in baseNumbers)
    }
}