package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoPrizeStatistics
import lotto.module.LottoGenerator
import lotto.view.LottoConsoleIO

const val LOTTO_MIN_NUMBER: Int = 1
const val LOTTO_MAX_NUMBER: Int = 45
const val LOTTO_NUMBERS_COUNT: Int = 6
const val LOTTO_PRICE: Int = 1000
const val LOTTO_NUMBER_DELIMITER = ","

class LottoGame (
    private val lottoConsoleIO: LottoConsoleIO = LottoConsoleIO(),
    private val lottoGenerator: LottoGenerator = LottoGenerator()
) {
    private val lottoList = mutableListOf<Lotto>()
    fun run() {

        val amount: Int = lottoConsoleIO.getAmount()
        lottoConsoleIO.printAmount(amount)

        repeat(amount) { lottoList.add(lottoGenerator.getLotto()) }
        lottoConsoleIO.printLottoList(lottoList)

        val lottoPrizeNumbers = lottoConsoleIO.getLottoPrizeNumbers()

        val lottoPrizeStatistics = LottoPrizeStatistics.of(lottoList, lottoPrizeNumbers)
        lottoConsoleIO.printLottoPrizeStatistics(lottoPrizeStatistics)

        lottoConsoleIO.printReturnRate(lottoPrizeStatistics.returnRate)
    }

}
