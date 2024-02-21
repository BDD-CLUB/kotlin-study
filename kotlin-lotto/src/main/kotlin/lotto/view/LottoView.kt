package lotto.view

import lotto.model.Lottos
import lotto.model.WinningLotto

interface LottoView {

    fun inputPurchaseAmount(): Int

    fun inputWinningNumbers(): List<Int>

    fun inputBonusNumber(): Int

    fun printPurchasedLotto(lottos: Lottos)

    fun printWinningStatistics(
        winningLottos: List<WinningLotto>,
        totalReturn: Double
    )
}
