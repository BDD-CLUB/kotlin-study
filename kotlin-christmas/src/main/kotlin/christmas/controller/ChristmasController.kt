package christmas.controller

import christmas.service.ChristmasService
import christmas.view.InputView
import christmas.view.OutputView

object ChristmasController {
    fun run(
        inputView: InputView,
        outputView: OutputView,
        christmasService: ChristmasService,
    ) {
        outputView.printWelcomeMessage()
        val userOrderInfo = inputView.getUserOrderInfo()
        val benefitInfo = christmasService.getBenefitInfo(userOrderInfo)
        outputView.printUserEventBenefit(userOrderInfo, benefitInfo)
    }
}
