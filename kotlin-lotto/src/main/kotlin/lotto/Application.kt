package lotto

import lotto.config.numberGenerator
import lotto.config.view
import lotto.controller.LottoController

fun main() {
    val lottoController = LottoController(view(), numberGenerator())
    lottoController.run()
}
