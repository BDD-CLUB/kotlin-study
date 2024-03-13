package lotto.model

import lotto.controller.LOTTO_PRICE


class LottoPrizeStatistics(
    val amount: Int,
    val result: Map<LottoPrize, Int>,
) {

    val returnRate: Double
        get() = totalPrizeMoney.toDouble() / (amount * LOTTO_PRICE) * 100

    private val totalPrizeMoney: Long
        get() = result.entries.sumOf { it.key.prizeKoreaMoney * it.value }

    companion object {
        fun of(lottoList: List<Lotto>, lottoWinningNumbers: LottoWinningNumbers): LottoPrizeStatistics {

            return LottoPrizeStatistics(
                amount = lottoList.size,
                result = getResult(lottoList, lottoWinningNumbers)
            )
        }

        private fun getResult(lottoList: List<Lotto>, lottoWinningNumbers: LottoWinningNumbers): MutableMap<LottoPrize, Int> {
            val result = LottoPrize.entries.associateWith { 0 }.toMutableMap()
            lottoList.forEach{lotto ->
                val matchedBaseNumberCount = lotto.numbers.count { it in lottoWinningNumbers.baseNumbers }
                val isBonusNumberMatched = lottoWinningNumbers.bonusNumber in lotto.numbers
                LottoPrize.findPrize(matchedBaseNumberCount, isBonusNumberMatched)?.let { prize ->
                    result[prize] = result[prize]!!.plus(1)
                }
            }
            return result
        }
    }

}