package christmas.dto

import christmas.discount_policy.EventDiscountPolicy
import christmas.discount_policy.createEventDiscountPolicy
import christmas.domain.Badge
import christmas.domain.Menu


class OrderMenuDto(
        val reservationDate: Int,
        val orderList: Map<Menu, Int>,
) {
    private val eventDiscountPolicies: List<EventDiscountPolicy> = createEventDiscountPolicy()
    val amountBeforeDiscount: Long by lazy { orderList.entries.sumOf { it.key.mainMenuPrice * it.value} }
    val giveMenu: Menu by lazy { if (amountBeforeDiscount > 120_000L) Menu.CHAMPAGNE else Menu.NOTHING }

    val benefitDetails: Map<String, Long> by lazy {
        eventDiscountPolicies.filter { policy -> policy.isSatisfiedBy(this) }
                .associate { policy -> policy.discountPolicyName to policy.calculateDiscountAmount(this) }
    }

    val totalDiscountAmount: Long by lazy {
        if (amountBeforeDiscount > 10_000L) {
            return@lazy giveMenu.mainMenuPrice + benefitDetails.entries.sumOf { it.value }
        } else {
            return@lazy 0
        }
    }

    val badge: Badge by lazy {
        Badge.entries.first { totalDiscountAmount >= it.badgePrice }
    }

    override fun toString(): String {
        return buildString {
            orderList.entries.forEach {
                append(it.key.mainMenuName + " " + it.value + "개\n")
            }
        }
    }
}
