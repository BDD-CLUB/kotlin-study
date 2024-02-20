package lotto

class LottoPrizeNumbers(
    val baseNumbers: List<Int>,
    val bonusNumber: Int,
) {
    init {
        require(baseNumbers.size == LOTTO_NUMBERS_COUNT)
    }
}