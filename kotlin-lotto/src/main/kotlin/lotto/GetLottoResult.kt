package lotto

import lotto._const.LOTTO_AMOUNT
import lotto._enums.LottoResult

class GetLottoResult(
    private val lottoBuyer: LottoBuyer,
    private val lottoGenerator: LottoGenerator
) {
    private var profit: Long = 0

    fun getResult(): List<LottoResult> {
        val lottoResult = mutableListOf<LottoResult>()
        for (lotto in lottoGenerator.lottoList) {
            val result = lotto.getResult(lottoBuyer.lottoBuyerDTO)
            if (result != null) {
                lottoResult.add(result)
                profit += result.price
            }
        }
        return lottoResult
    }

    fun getProfitRate(): Double {
        val lottoPrice = lottoBuyer.lottoBuyerDTO.purchaseAmount * LOTTO_AMOUNT
        return (profit / (lottoPrice.toDouble())) * 100
    }
}
