package christmas.discount_policy

import christmas.domain.MenuType
import christmas.domain.OrderMenu
import christmas.global.Component

@Component
class WeekdayDiscountPolicy: EventDiscountPolicy {

    override val discountPolicyName: String
        get() = "평일 할인"

    override fun isSatisfiedBy(orderMenu: OrderMenu): Boolean {
        val days = listOf(3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31)
        return orderMenu.reservationDate in days
    }

    override fun calculateDiscountAmount(orderMenu: OrderMenu): Long =
        2023L * orderMenu.orderList.entries
                .filter { it.key.menuType == MenuType.DESSERT }
                .sumOf { it.value }

}