package christmas.restaurant.event

import christmas.restaurant.MenuItem
import christmas.util.Date
import christmas.util.DateType
import christmas.util.DiscountResult

class Event {
    enum class Discount(val description: String, val amount: Int) {
        CHRISTMAS_DISCOUNT("크리스마스 디데이 할인", 1000),
        WEEKDAY_DISCOUNT("평일 할인", 2023),
        SPECIAL_DISCOUNT("특별 할인", 1000),
        GIFT_EVENT("증정 이벤트", 25000);
    }


    fun checkChristmasDiscount(date: Int): DiscountResult {
        val christmasDiscount = Discount.CHRISTMAS_DISCOUNT.amount + (date - 1) * 100
        return DiscountResult(Discount.CHRISTMAS_DISCOUNT, christmasDiscount)
    }

    fun checkWeekDiscount(date: Int, order: List<MenuItem>): DiscountResult? {
        val day = Date.getDayOfWeek(date)?.getDateType()
            ?: return null

        val discountAmount = when (day) {
            DateType.WEEKDAY -> Discount.WEEKDAY_DISCOUNT.amount * order.filter { it is MenuItem.Dessert }.size
            DateType.WEEKEND -> Discount.WEEKDAY_DISCOUNT.amount * order.filter { it is MenuItem.Main }.size
        }

        return DiscountResult(discountCode = Discount.WEEKDAY_DISCOUNT, discountAmount = discountAmount, any = day)
    }

    fun checkEventStar(date: Int): DiscountResult? = when (date) {
        in eventDate -> DiscountResult(Discount.SPECIAL_DISCOUNT, Discount.SPECIAL_DISCOUNT.amount)
        else -> null
    }


    fun checkChampagneBenefits(totalPrice: Int) =
        if (totalPrice >= 120_000) DiscountResult(Discount.GIFT_EVENT, Discount.GIFT_EVENT.amount)
        else null

    companion object {
        val eventDate = listOf(3, 10, 17, 24, 25, 31)
    }

}

enum class RewardBadge(val threshold: Int, val icon: String) {
    STAR(5000, "별"),
    TREE(10000, "트리"),
    SANTA(20000, "산타");

    companion object {
        fun findByAmount(amount: Int): RewardBadge? {
            return values().lastOrNull { amount >= it.threshold }
        }
    }
}
