package lotto

import lotto._enums.LottoResult
import lotto.util.LOTTO_AMOUNT
import lotto.util.printProfitRateMessage
import lotto.util.roundTwo

class LottoGetResult(
    private val lottoBuyer: LottoBuyer,
    private val lottoGenerator: LottoGenerator
) {
    private val lottoResult = mutableListOf<LottoResult>()
    private var profit: Long = 0

    init {
        getResult()
    }

    private fun getResult() {
        for (lotto in lottoGenerator.lottoList) {
            val result = lotto.getResult(lottoBuyer.lottoBuyerDTO)
            if (result != null) {
                lottoResult.add(result)
                profit += result.price
            }
        }
    }

    fun printResult() {
        for (result in LottoResult.values()) {
            val count = lottoResult.count { it == result }
            val message = result.getMessage(count)
            println(message)
        }
        printProfitRate()
    }

    private fun printProfitRate() {
        val lottoPrice = lottoBuyer.lottoBuyerDTO.purchaseAmount * LOTTO_AMOUNT
        val profitRate = (profit / (lottoPrice.toDouble())) * 100
        printProfitRateMessage(profitRate.roundTwo())
    }
}
