package christmas.controller

import christmas.model.Discount
import christmas.view.DecemberEventConsoleIO

const val DECEMBER_EVENT_START_DAY = 1
const val DECEMBER_EVENT_END_DAY = 31
const val DECEMBER_EVENT_ORDER_MINIMUM_PRICE = 10_000
const val DECEMBER_EVENT_ORDER_MAXIMUM_QUANTITY = 20

class DecemberEventPlanner {
    private val decemberEventConsoleIO = DecemberEventConsoleIO()

    fun run() {
        val (visitDate, orderMenus) = decemberEventConsoleIO.getEventReservationOrder()
        decemberEventConsoleIO.printEventReservationOrderPreview(visitDate, orderMenus)
        val discount = Discount.of(visitDate, orderMenus)
        decemberEventConsoleIO.printDiscountsAndBenefits(orderMenus, discount)
    }

}
