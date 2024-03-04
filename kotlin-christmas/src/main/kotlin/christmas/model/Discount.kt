package christmas.model


import christmas.controller.DECEMBER_EVENT_ORDER_MINIMUM_PRICE
import java.time.DayOfWeek
import java.time.LocalDate

class Discount (
    val isValid: Boolean,
    val christmasDDayDiscountPrice: Int,
    val dailyDiscountPrice: Int,
    val holidayDiscountPrice: Int,
    val specialDiscountPrice: Int,
    val giftMenu: GiftMenu?,
){

    val giftStuckBenefitPrice = giftMenu?.let { it.menu.price * it.quantity }?: 0
    val totalDiscountPrice
        get() = christmasDDayDiscountPrice +
                dailyDiscountPrice +
                holidayDiscountPrice +
                specialDiscountPrice

    val totalBenefitPrice
        get() = totalDiscountPrice + giftStuckBenefitPrice
    companion object {

        private const val CHRISTMAS_D_DAY_DISCOUNT_START_DAY = 1
        private const val CHRISTMAS_D_DAY_DISCOUNT_END_DAY = 25
        private const val CHRISTMAS_D_DAY_DISCOUNT_START_PRICE = 1_000
        private const val CHRISTMAS_D_DAY_DISCOUNT_INCREASE_PRICE = 100
        private const val DAILY_DESSERT_DISCOUNT_PRICE = 2_023
        private const val HOLIDAY_MAIN_COURSE_DISCOUNT_PRICE = 2_023
        private const val SPECIAL_DISCOUNT_PRICE = 1_000

        private val holiday = listOf(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY)
        private val starDay = listOf(3, 10, 17, 24, 25, 31)

        fun of(
            visitDate: Int,
            orderMenus: OrderMenus
        ): Discount{
            return orderMenus.totalPriceBeforeDiscount
                .takeIf { it >= DECEMBER_EVENT_ORDER_MINIMUM_PRICE }
                ?.let {
                    val dayOfWeekAtVisitDate = LocalDate.of(2023, 12, visitDate).dayOfWeek
                    Discount(
                        isValid = true,
                        christmasDDayDiscountPrice = calcChristmasDDayDiscountPrice(visitDate),
                        dailyDiscountPrice = calcDailyDiscountPrice(dayOfWeekAtVisitDate, orderMenus),
                        holidayDiscountPrice = calcHolidayDiscountPrice(dayOfWeekAtVisitDate, orderMenus),
                        specialDiscountPrice = calcSpecialDiscountPrice(visitDate),
                        giftMenu = GiftMenu.getEligibleGift(orderMenus.totalPriceBeforeDiscount)
                    )
                }?: invalidDiscount()
        }

        private fun invalidDiscount(): Discount {
            return Discount(
                isValid = false,
                christmasDDayDiscountPrice = 0,
                dailyDiscountPrice = 0,
                holidayDiscountPrice = 0,
                specialDiscountPrice = 0,
                giftMenu = null
            )
        }

        private fun calcChristmasDDayDiscountPrice(visitDate: Int): Int {
            if (visitDate in CHRISTMAS_D_DAY_DISCOUNT_START_DAY .. CHRISTMAS_D_DAY_DISCOUNT_END_DAY){
                return CHRISTMAS_D_DAY_DISCOUNT_START_PRICE + CHRISTMAS_D_DAY_DISCOUNT_INCREASE_PRICE * (visitDate - 1)
            }
            return 0
        }

        private fun calcDailyDiscountPrice(dayOfWeekAtVisitDate: DayOfWeek, orderMenus: OrderMenus): Int {
            if (dayOfWeekAtVisitDate !in holiday) {
                val dessertQuantity = orderMenus.menuAndQuantityMap.entries.filter { it.key.menuType == MenuType.DESSERT }.sumOf { it.value }
                return dessertQuantity * DAILY_DESSERT_DISCOUNT_PRICE
            }
            return 0
        }

        private fun calcHolidayDiscountPrice(dayOfWeekAtVisitDate: DayOfWeek, orderMenus: OrderMenus): Int {
            if (dayOfWeekAtVisitDate in holiday) {
                val mainCourseQuantity = orderMenus.menuAndQuantityMap.entries.filter { it.key.menuType == MenuType.MAIN_COURSE }.sumOf { it.value }
                return mainCourseQuantity * HOLIDAY_MAIN_COURSE_DISCOUNT_PRICE
            }
            return 0
        }

        private fun calcSpecialDiscountPrice(visitDate: Int): Int {
            if (visitDate in starDay) return SPECIAL_DISCOUNT_PRICE
            return 0
        }

    }
}