package lotto

import lotto.util.*

class LottoGame {

    private val lottoBuyer = LottoBuyer()
    private val lottoGenerator = LottoGenerator()

    fun execute() {
        getPurchaseAmount()
        generateLottoNumbers()
        getLottoNumber()
        printLottoResult()
    }

    private fun getPurchaseAmount() {
        printGetPurchaseAmountMessage()
        lottoBuyer.getPurchaseAmount()
    }

    private fun generateLottoNumbers() {
        val amount = lottoBuyer.lottoBuyerDTO.purchaseAmount
        lottoGenerator.generate(amount)
        lottoGenerator.printLottoList()
    }

    private fun getLottoNumber() {
        printGetWinningNumberMessage()
        lottoBuyer.getWinningNumber()

        printGetBonusNumberMessage()
        lottoBuyer.getBonusNumber()
    }

    private fun printLottoResult() {
        val lottoGetResult = LottoGetResult(lottoBuyer, lottoGenerator)
        lottoGetResult.printResult()
    }

}
