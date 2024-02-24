package christmas.model

import christmas.badge.Badge
import christmas.event.EventType

class UserBenefitInfo(
    val benefitList: List<EventType>,
) {
    val giveaway: EventType.Giveaway? = benefitList
        .filterIsInstance<EventType.Giveaway>().firstOrNull()

    val totalBenefitAmount: Price = benefitList.sumOf {
        when (it) {
            is EventType.Giveaway -> it.menuWithCount.menu.price.value * it.menuWithCount.count
            is EventType.Discount -> it.price.value
        }
    }.toPrice()

    val totalDiscountAmount: Price = benefitList
        .filterIsInstance<EventType.Discount>()
        .sumOf { it.price.value }
        .toPrice()

    val eventBadge = Badge.of(totalBenefitAmount)
}
