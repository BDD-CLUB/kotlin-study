package lotto

class LottoPrizeNumbers(
    val baseNumbers: List<Int>,
    val bonusNumber: Int,
) {
    init {
        require(baseNumbers.size == NUMBER_OF_LOTTO_NUMBERS)
    }
}