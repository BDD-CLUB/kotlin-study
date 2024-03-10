package oncall

import oncall.controller.OncallController
import oncall.service.OncallService
import oncall.view.InputView
import oncall.view.OutputView

fun main() {
    OncallController.run(InputView, OutputView, OncallService)
}
