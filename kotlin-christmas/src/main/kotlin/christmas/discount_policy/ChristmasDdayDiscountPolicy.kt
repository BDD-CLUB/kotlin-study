package christmas.discount_policy

import christmas.dto.OrderMenuDto
import christmas.global.Component

@Component
class ChristmasDdayDiscountPolicy: EventDiscountPolicy {

    override val discountPolicyName: String
        get() = "크리스마스 디데이 할인"

    override fun isSatisfiedBy(orderMenuDto: OrderMenuDto): Boolean =
            orderMenuDto.reservationDate in 1..25

    override fun calculateDiscountAmount(orderMenuDto: OrderMenuDto): Long =
            1000L + 100L * (orderMenuDto.reservationDate - 1)
}