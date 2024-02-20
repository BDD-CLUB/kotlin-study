package lotto

data class LottoPrizeNumbers(
    private val baseNumbers: List<Int>,
    private val bonusNumber: Int,
) {
    init {
        require(baseNumbers.size == 6)
    }

}