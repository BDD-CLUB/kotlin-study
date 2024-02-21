package lotto.controller

import lotto.generator.NumberGenerator
import lotto.model.*
import lotto.view.LottoView

class LottoController(
    private val lottoView: LottoView,
    private val numberGenerator: NumberGenerator
) {

    fun run() {
        val purchaseAmount = getPurchaseAmount()
        val numberOfLotto = purchaseAmount / AMOUNT_UNIT

        val lottos = getLottos(numberOfLotto)
        lottoView.printPurchasedLotto(lottos)

        val (userLotto, bonusNumber) = getUserLotto()
        val winningLottos = lottos.getWinningLottos(userLotto, bonusNumber)
        val totalReturn = getTotalReturn(winningLottos, purchaseAmount)
        lottoView.printWinningStatistics(winningLottos, totalReturn)
    }

    private fun getLottos(numberOfLotto: Int): Lottos {
        val lottos = mutableListOf<Lotto>()
        for (i in 0 until numberOfLotto) {
            val generate = numberGenerator.generate()
            lottos.add(Lotto(getLottoNumbers(generate)))
        }
        return Lottos(lottos)
    }

    private fun getPurchaseAmount(): Int {
        while (true) {
            try {
                val purchaseAmount = lottoView.inputPurchaseAmount()
                if (purchaseAmount % AMOUNT_UNIT != 0) {
                    throw IllegalArgumentException()
                }
                return purchaseAmount
            } catch (e: IllegalArgumentException) {
                println("[ERROR] 올바르지 않은 값입니다. 다시 입력해주세요.")
            }
        }
    }

    private fun getLottoNumbers(list: List<Int>): List<LottoNumber> {
        return list.map { LottoNumber(it) }
    }

    private fun getUserLotto(): Pair<Lotto, LottoNumber> {
        while (true) {
            try {
                val winningNumbers = lottoView.inputWinningNumbers()
                val bonusNumber = lottoView.inputBonusNumber()
                if (winningNumbers.contains(bonusNumber)) {
                    throw IllegalArgumentException()
                }
                return Pair(Lotto(getLottoNumbers(winningNumbers)), LottoNumber(bonusNumber))
            } catch (e: IllegalArgumentException) {
                println("[ERROR] 올바르지 않은 값입니다. 다시 입력해주세요.")
            }
        }
    }

    private fun getTotalReturn(winningLottos: List<WinningLotto>, purchaseAmount: Int): Double {
        return ((winningLottos.sumOf { it.amount }.toDouble() / purchaseAmount.toDouble())) * 100
    }

    companion object {
        const val AMOUNT_UNIT = 1_000
    }
}
