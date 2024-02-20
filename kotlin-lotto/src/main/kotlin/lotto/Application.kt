package lotto

import lotto.controller.LottoController
import lotto.global.DIContainer
import lotto.global.componentScan
import lotto.model.LottoGameManager
import lotto.view.LottoGameMessageView

class Application

fun main() {
    componentScan(Application::class)

    val lottoGameManager = DIContainer.getInstance(LottoGameManager::class)
    lottoGameManager.run()

}
