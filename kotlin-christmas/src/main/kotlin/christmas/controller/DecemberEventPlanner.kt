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
        decemberEventConsoleIO.printGreetings()
        val visitDate = decemberEventConsoleIO.getVisitDate()
        val orderMenus = decemberEventConsoleIO.getOrderMenus()
        decemberEventConsoleIO.printUpcomingEventBenefitsAtVisitDate(visitDate)
        decemberEventConsoleIO.printOrderMenus(orderMenus)
        decemberEventConsoleIO.printTotalPriceBeforeDiscount(orderMenus.totalPriceBeforeDiscount)
        val discount = Discount.of(visitDate, orderMenus)
        decemberEventConsoleIO.printGiftMenuReport(discount.giftMenu)
        decemberEventConsoleIO.printBenefitReport(discount)
        decemberEventConsoleIO.printTotalBenefitPrice(discount.totalBenefitPrice)
        val paymentPrice = orderMenus.totalPriceBeforeDiscount - discount.totalDiscountPrice
        decemberEventConsoleIO.printPaymentPriceAfterDiscount(paymentPrice)
        decemberEventConsoleIO.printDecemberEventBadge(discount.giftStuckBenefitPrice)
    }

}