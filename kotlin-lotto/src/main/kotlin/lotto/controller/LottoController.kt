package lotto.controller

import lotto.model.Lottos
import lotto.model.WinningStatistics
import lotto.view.View

class LottoController(private val view: View) {
    fun run() {
        val purchasePrice = view.getPurchasePrice()
        val lottos = Lottos.generate(purchasePrice)
        view.printLottos(lottos)
        val winningLotto = view.getWinningLotto()
        view.printWinningStatistics(WinningStatistics(lottos, winningLotto))
    }
}
