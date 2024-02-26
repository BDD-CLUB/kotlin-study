package christmas.util

import christmas.restaurant.event.Event

data class DiscountResult(
    val discountCode: Event.Discount?,
    val discountAmount: Int,
    val any: Any? = null
)
