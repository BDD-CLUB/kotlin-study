package christmas.discount_policy

import christmas.dto.OrderMenuDto

interface EventDiscountPolicy {

    val discountPolicyName: String

    fun isSatisfiedBy(orderMenuDto: OrderMenuDto): Boolean

    fun calculateDiscountAmount(orderMenuDto: OrderMenuDto): Long

}