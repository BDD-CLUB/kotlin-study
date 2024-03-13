package christmas.model

import java.time.DayOfWeek
import java.time.LocalDate

interface DiscountPolicy {
    fun calculateDiscount(visitDate: Int, orderMenus: OrderMenus): Int

    val holiday: List<DayOfWeek>
        get() = listOf(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY)
    val starDay: List<Int>
        get() = listOf(3, 10, 17, 24, 25, 31)

    fun Int.toDayOfWeekInDecember2023(): DayOfWeek {
        return LocalDate.of(2023, 12, this).dayOfWeek
    }
}

object ChristmasDDayDiscountPolicy : DiscountPolicy {
    private const val CHRISTMAS_D_DAY_DISCOUNT_START_DAY = 1
    private const val CHRISTMAS_D_DAY_DISCOUNT_END_DAY = 25
    private const val CHRISTMAS_D_DAY_DISCOUNT_START_PRICE = 1_000
    private const val CHRISTMAS_D_DAY_DISCOUNT_INCREASE_PRICE = 100
    override fun calculateDiscount(visitDate: Int, orderMenus: OrderMenus): Int =
        if (visitDate in CHRISTMAS_D_DAY_DISCOUNT_START_DAY..CHRISTMAS_D_DAY_DISCOUNT_END_DAY)
            CHRISTMAS_D_DAY_DISCOUNT_START_PRICE + CHRISTMAS_D_DAY_DISCOUNT_INCREASE_PRICE * (visitDate - 1)
        else 0
}

object DailyDiscountPolicy : DiscountPolicy {
    private const val DAILY_DESSERT_DISCOUNT_PRICE = 2_023
    override fun calculateDiscount(visitDate:Int, orderMenus: OrderMenus): Int {
        if (visitDate.toDayOfWeekInDecember2023() !in holiday) {
            val dessertQuantity = orderMenus.menuAndQuantityMap.entries
                .filter { it.key.menuType == MenuType.DESSERT }
                .sumOf { it.value }
            return dessertQuantity * DAILY_DESSERT_DISCOUNT_PRICE
        }
        return 0
    }
}

object HolidayDiscountPolicy : DiscountPolicy {
    private const val HOLIDAY_MAIN_COURSE_DISCOUNT_PRICE = 2_023
    override fun calculateDiscount(visitDate:Int, orderMenus: OrderMenus): Int {
        if (visitDate.toDayOfWeekInDecember2023() in holiday) {
            val mainCourseQuantity = orderMenus.menuAndQuantityMap.entries
                .filter { it.key.menuType == MenuType.MAIN_COURSE }
                .sumOf { it.value }
            return mainCourseQuantity * HOLIDAY_MAIN_COURSE_DISCOUNT_PRICE
        }
        return 0
    }
}

object SpecialDiscountPolicy : DiscountPolicy {
    private const val SPECIAL_DISCOUNT_PRICE = 1_000
    override fun calculateDiscount(visitDate:Int, orderMenus: OrderMenus): Int {
        if (visitDate in starDay) return SPECIAL_DISCOUNT_PRICE
        return 0
    }
}
