package christmas

import christmas.controller.ChristmasController
import christmas.service.ChristmasService
import christmas.view.InputView
import christmas.view.OutputView

fun main() {
    ChristmasController.run(
        InputView,
        OutputView,
        ChristmasService
    )
}
