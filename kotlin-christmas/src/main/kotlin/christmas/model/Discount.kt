package christmas.model


import christmas.controller.DECEMBER_EVENT_ORDER_MINIMUM_PRICE

class Discount (
    val christmasDDayDiscountPrice: Int,
    val dailyDiscountPrice: Int,
    val holidayDiscountPrice: Int,
    val specialDiscountPrice: Int,
    val giftMenu: GiftMenu?,
){

    val giftStuckBenefitPrice
        get() = giftMenu?.let { it.menu.price * it.quantity }?: 0

    val totalDiscountPrice
        get() = christmasDDayDiscountPrice +
                dailyDiscountPrice +
                holidayDiscountPrice +
                specialDiscountPrice

    val totalBenefitPrice
        get() = totalDiscountPrice + giftStuckBenefitPrice

    companion object {

        fun of(visitDate: Int, orderMenus: OrderMenus): Discount{
            return orderMenus.totalPriceBeforeDiscount
                .takeIf { it >= DECEMBER_EVENT_ORDER_MINIMUM_PRICE }
                ?.let {
                    Discount(
                        christmasDDayDiscountPrice = ChristmasDDayDiscountPolicy.calculateDiscount(visitDate, orderMenus),
                        dailyDiscountPrice = DailyDiscountPolicy.calculateDiscount(visitDate, orderMenus),
                        holidayDiscountPrice = HolidayDiscountPolicy.calculateDiscount(visitDate, orderMenus),
                        specialDiscountPrice = SpecialDiscountPolicy.calculateDiscount(visitDate, orderMenus),
                        giftMenu = GiftMenu.getEligibleGift(orderMenus.totalPriceBeforeDiscount)
                    )
                }?: invalidDiscount()
        }

        private fun invalidDiscount(): Discount {
            return Discount(
                christmasDDayDiscountPrice = 0,
                dailyDiscountPrice = 0,
                holidayDiscountPrice = 0,
                specialDiscountPrice = 0,
                giftMenu = null
            )
        }
    }
}
