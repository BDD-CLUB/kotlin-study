package christmas.discount_policy

import christmas.dto.OrderMenuDto
import christmas.global.Component

@Component
class SpecialDiscountPolicy: EventDiscountPolicy {

    override val discountPolicyName: String
        get() = "특별 할인"

    override fun isSatisfiedBy(orderMenuDto: OrderMenuDto): Boolean {
        val days = listOf(3, 10, 17, 24, 25, 31)
        return orderMenuDto.reservationDate in days
    }

    override fun calculateDiscountAmount(orderMenuDto: OrderMenuDto): Long = 1000L


}