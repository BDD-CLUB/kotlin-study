package christmas.discount_policy

import christmas.domain.MenuType
import christmas.domain.OrderMenu
import christmas.global.Component

@Component
class WeekendDiscountPolicy: EventDiscountPolicy {

    override val discountPolicyName: String
        get() = "주말 할인"

    override fun isSatisfiedBy(orderMenu: OrderMenu): Boolean {
        val days = listOf(1, 2, 8, 9, 15, 16, 22, 23, 29, 30)
        return orderMenu.reservationDate in days
    }

    override fun calculateDiscountAmount(orderMenu: OrderMenu): Long =
            2023L * orderMenu.orderList.entries
                    .filter { it.key.menuType == MenuType.MAIN }
                    .sumOf { it.value }


}