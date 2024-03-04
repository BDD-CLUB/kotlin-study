package christmas.discount_policy

import christmas.domain.OrderMenu
import christmas.global.Component

@Component
class SpecialDiscountPolicy: EventDiscountPolicy {

    override val discountPolicyName: String
        get() = "특별 할인"

    override fun isSatisfiedBy(orderMenu: OrderMenu): Boolean {
        val days = listOf(3, 10, 17, 24, 25, 31)
        return orderMenu.reservationDate in days
    }

    override fun calculateDiscountAmount(orderMenu: OrderMenu): Long = 1000L


}