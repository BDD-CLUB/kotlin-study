package lotto

import java.math.BigDecimal
import java.math.RoundingMode


data class LottoWinningStatistics(
    val amount: Int,
    val firstPrizeStatistic: LottoPrizeStatistic = LottoPrizeStatistic(LottoPrizeCategory.FIRST_PRIZE, 0),
    val secondPrizeStatistic: LottoPrizeStatistic = LottoPrizeStatistic(LottoPrizeCategory.SECOND_PRIZE, 0),
    val thirdPrizeStatistic: LottoPrizeStatistic = LottoPrizeStatistic(LottoPrizeCategory.THIRD_PRIZE, 0),
    val fourthPrizeStatistic: LottoPrizeStatistic = LottoPrizeStatistic(LottoPrizeCategory.FOURTH_PRIZE, 0),
    val fifthPrizeStatistic: LottoPrizeStatistic = LottoPrizeStatistic(LottoPrizeCategory.FIFTH_PRIZE, 0),
) {

    private val sumPrizeMoney: Long
        get() = firstPrizeStatistic.prizeCategory.prizeKoreaMoney * firstPrizeStatistic.count +
                secondPrizeStatistic.prizeCategory.prizeKoreaMoney * secondPrizeStatistic.count +
                thirdPrizeStatistic.prizeCategory.prizeKoreaMoney * thirdPrizeStatistic.count +
                fourthPrizeStatistic.prizeCategory.prizeKoreaMoney * fourthPrizeStatistic.count +
                fifthPrizeStatistic.prizeCategory.prizeKoreaMoney * fifthPrizeStatistic.count

    private val ROI: Double
        get() = sumPrizeMoney / (amount.toDouble())

    fun printROI() {
        println("총 수익률은 ${Math.round(ROI * 100) / 1000.0}%입니다.")
    }

    fun printStatistics() {
        println()
        println("당첨 통계")
        println("---")
        println("${this.fifthPrizeStatistic.prizeCategory.matchedBaseNumberCount}개 일치 (${this.fifthPrizeStatistic.prizeCategory.prizeKoreaMoneyWithComma}원) - ${this.fifthPrizeStatistic.count}개")
        println("${this.fourthPrizeStatistic.prizeCategory.matchedBaseNumberCount}개 일치 (${this.fourthPrizeStatistic.prizeCategory.prizeKoreaMoneyWithComma}원) - ${this.fourthPrizeStatistic.count}개")
        println("${this.thirdPrizeStatistic.prizeCategory.matchedBaseNumberCount}개 일치 (${this.thirdPrizeStatistic.prizeCategory.prizeKoreaMoneyWithComma}원) - ${this.thirdPrizeStatistic.count}개")
        println("${this.secondPrizeStatistic.prizeCategory.matchedBaseNumberCount}개 일치, 보너스 볼 일치 (${this.secondPrizeStatistic.prizeCategory.prizeKoreaMoneyWithComma}원) - ${this.secondPrizeStatistic.count}개")
        println("${this.firstPrizeStatistic.prizeCategory.matchedBaseNumberCount}개 일치 (${this.firstPrizeStatistic.prizeCategory.prizeKoreaMoneyWithComma}원) - ${this.firstPrizeStatistic.count}개")
    }

    companion object {
        fun of(lottoList: List<Lotto>, lottoPrizeNumbers: LottoPrizeNumbers): LottoWinningStatistics {
            val newLottoWinningStatistics = LottoWinningStatistics(amount = lottoList.size)
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

data class LottoPrizeStatistic(
    val prizeCategory: LottoPrizeCategory,
    var count: Int,
) {
    init {
        require(count in 0..6)
    }

    fun addCount() {
        count++
    }
}