package christmas.model

import christmas.menu.MenuType
import christmas.menu.MenuWithCount
import christmas.validation.requireChristmas
import java.time.LocalDate

private const val MAX_AVAILABLE_MENU_COUNT = 20

class UserOrderInfo(
    val userOrderMenu: UserOrderMenu,
    estimatedVisitDay: VisitDay,
) {
    val estimatedVisitDate: LocalDate = LocalDate.of(2023, 12, estimatedVisitDay.day)
    val totalPrice: Price = userOrderMenu.getTotalPrice()

    fun getSumPriceOfMenu(menuType: MenuType) =
        this.userOrderMenu.menus.sumOf { if (it.menu.menuType == menuType) it.count else 0 }

    @JvmInline
    value class VisitDay(val day: Int) {
        init {
            requireChristmas(day in 1..31) { "유효하지 않은 날짜입니다. 다시 입력해 주세요." }
        }
    }

    @JvmInline
    value class UserOrderMenu(val menus: List<MenuWithCount>) {
        init {
            requireChristmas(menus.sumOf { it.count } <= MAX_AVAILABLE_MENU_COUNT) { "totalMenuCount는 ${MAX_AVAILABLE_MENU_COUNT}개를 넘을 수 없습니다." }
            requireChristmas((menus.all { it.menu.menuType == MenuType.Drink }).not()) { "음료만 주문할 수 없습니다. 주문 메뉴: $menus" }
        }

        fun getTotalPrice() = this.menus.sumOf { it.menu.price.value * it.count }.toPrice()

        override fun toString() = menus.joinToString("\n")
    }
}
