package christmas.event

import christmas.menu.MenuWithCount
import christmas.model.Price

sealed class EventType(
    private val benefitAmount: Price,
    private val eventPolicyName: String
) {
    class Discount(val price: Price, eventPolicyName: String) : EventType(price, eventPolicyName)

    class Giveaway(val menuWithCount: MenuWithCount, eventPolicyName: String) :
        EventType(menuWithCount.benefitAmount, eventPolicyName)

    override fun toString() = "${eventPolicyName}: ${benefitAmount.toMinusString()}"
}
