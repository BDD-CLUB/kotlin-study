package christmas.discount_policy

import christmas.domain.OrderMenu
import christmas.global.Component

@Component
class ChristmasDdayDiscountPolicy: EventDiscountPolicy {

    override val discountPolicyName: String
        get() = "크리스마스 디데이 할인"

    override fun isSatisfiedBy(orderMenu: OrderMenu): Boolean =
            orderMenu.reservationDate in 1..25

    override fun calculateDiscountAmount(orderMenu: OrderMenu): Long =
            1000L + 100L * (orderMenu.reservationDate - 1)
}