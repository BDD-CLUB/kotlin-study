package oncall.controller

import oncall.entity.Order
import oncall.service.OncallService
import oncall.view.InputView
import oncall.view.OutputView

object OncallController {

    fun run(inputView: InputView, outputView: OutputView, oncallService: OncallService) {
        val oncallDate = inputView.getOncallDate()
        val (weekdayOrder: Order, holidayOrder: Order) = inputView.getOrder()
        val oncallTable = oncallService.getOncallTable(
            oncallDate, weekdayOrder = weekdayOrder, holidayOrder = holidayOrder
        )
        outputView.printOncallTable(oncallTable)
    }
}
