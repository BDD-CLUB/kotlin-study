package christmas.discount_policy

import christmas.domain.OrderMenu

interface EventDiscountPolicy {

    val discountPolicyName: String

    fun isSatisfiedBy(orderMenu: OrderMenu): Boolean

    fun calculateDiscountAmount(orderMenu: OrderMenu): Long

}