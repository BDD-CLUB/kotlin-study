package christmas.restaurant

import christmas.restaurant.event.Event
import christmas.restaurant.event.EventPlanner
import christmas.util.DiscountResult

class Restaurant(private val eventPlanner: EventPlanner) {
    fun open() {
        val date = eventPlanner.receiveVisitDate().toInt()
        val orderList = getMenuItems()

        val totalPrice = calculateTotalPrice(orderList)
        val activeEventBenefits = checkEvent(date, orderList, totalPrice)
        val totalBenefits = activeEventBenefits.sumBy { it.discountAmount }

        displayEventOutcome(date, orderList, totalPrice, activeEventBenefits, totalBenefits)
    }

    private fun getMenuItems() = eventPlanner.takeOrder().flatMap { orderItem ->
        val itemName = orderItem[0]
        val quantity = orderItem[1].toInt()
        List(quantity) { items.find { item -> item.name == itemName }!! }
    }

    private fun checkEvent(
        date: Int,
        order: List<MenuItem>,
        totalPrice: Int
    ): List<DiscountResult> {
        if (totalPrice <= 10_000) {
            return listOf()
        }
        val christmasDiscount = event.checkChristmasDiscount(date)
        val weekDiscount = event.checkWeekDiscount(date, order)
        val eventStarDiscount = event.checkEventStar(date)
        val champagneBenefits = event.checkChampagneBenefits(totalPrice)

        return listOfNotNull(christmasDiscount, weekDiscount, eventStarDiscount, champagneBenefits)
    }

    private fun displayEventOutcome(
        date: Int,
        orderList: List<MenuItem>,
        totalPrice: Int,
        activeEventBenefits: List<DiscountResult>,
        totalBenefits: Int
    ) {
        eventPlanner.previewEventBenefits(date)
        eventPlanner.showOrderList(orderList)
        eventPlanner.showPriceBeforeDiscount(totalPrice)
        eventPlanner.showGiftList(activeEventBenefits)
        eventPlanner.showTotalBenefits(activeEventBenefits)
        eventPlanner.showTotalBenefitsPrice(totalBenefits)
        eventPlanner.showPriceAfterDiscount(totalPrice, totalBenefits, activeEventBenefits)
        eventPlanner.showBadge(totalBenefits)
    }


    fun calculateTotalPrice(order: List<MenuItem>): Int {
        val totalPrice = order.sumBy { it.price }
        return totalPrice
    }


    init {
        eventPlanner.greeting()
    }

    companion object {
        val event = Event()
        val items = Menu.items

    }

}
