package lotto

import lotto.controller.LottoController
import lotto.view.View

fun main() {
    val lottoController = LottoController(View())
    lottoController.run()
}
