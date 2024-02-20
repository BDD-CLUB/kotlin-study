package lotto


data class LottoWinningStatistics(
    private val firstPrizeStatistic: LottoPrizeStatistic = LottoPrizeStatistic(LottoPrizeCategory.FIRST_PRIZE, 0),
    private val secondPrizeStatistic: LottoPrizeStatistic = LottoPrizeStatistic(LottoPrizeCategory.SECOND_PRIZE, 0),
    private val thirdPrizeStatistic: LottoPrizeStatistic = LottoPrizeStatistic(LottoPrizeCategory.THIRD_PRIZE, 0),
    private val fourthPrizeStatistic: LottoPrizeStatistic = LottoPrizeStatistic(LottoPrizeCategory.FOURTH_PRIZE, 0),
    private val fifthPrizeStatistic: LottoPrizeStatistic = LottoPrizeStatistic(LottoPrizeCategory.FIFTH_PRIZE, 0),
) {

    data class LottoPrizeStatistic(
        private val prizeCategory: LottoPrizeCategory,
        private var count: Int,
    ) {
        init {
            require(count in 0..6)
        }

        fun addCount() {
            count++
        }
    }

    companion object {
        fun of(lottoList: List<Lotto>, lottoPrizeNumbers: LottoPrizeNumbers): LottoWinningStatistics {
            val newLottoWinningStatistics = LottoWinningStatistics()
            lottoList.forEach { lotto ->
                updateStatisticsForLotto(lotto, lottoPrizeNumbers, newLottoWinningStatistics)
            }
            return newLottoWinningStatistics
        }

        private fun updateStatisticsForLotto(
            lotto: Lotto,
            lottoPrizeNumbers: LottoPrizeNumbers,
            statistics: LottoWinningStatistics
        ) {
            val matchedBaseNumberCount = lotto.getLotto.toSet().intersect(lottoPrizeNumbers.getBaseNumbers.toSet()).size
            val hitBonusNumber = lottoPrizeNumbers.getBonusNumber in lotto.getLotto

            when (matchedBaseNumberCount) {
                6 -> statistics.firstPrizeStatistic.addCount()
                5 -> {
                    if (hitBonusNumber) statistics.secondPrizeStatistic.addCount()
                    else statistics.thirdPrizeStatistic.addCount()
                }

                4 -> statistics.fourthPrizeStatistic.addCount()
                3 -> statistics.fifthPrizeStatistic.addCount()
                else -> {}
            }
        }
    }

}