package christmas.data

data class EventBenefit(
    val discountBenefit: DiscountBenefit = DiscountBenefit(),
    val totalAmountBeforeBenefit: Int = 0,
    val presentationMenu: UserMenu? = null,
    val badge: Badge? = null
) {
    fun getTotalAmountAfterBenefit(): Int {
        // 할인 후 예상 결제 금액 = 할인 전 총주문 금액 - 할인 금액
        return totalAmountBeforeBenefit - discountBenefit.getTotalPrice()
    }

    fun getBenefit(): Int {
        // 총혜택 금액 = 할인 금액의 합계 + 증정 메뉴의 가격
        return discountBenefit.getTotalPrice() + getPresentationPrice()
    }

    private fun getPresentationPrice(): Int {
        if (presentationMenu == null) return 0
        return presentationMenu.menu.price * presentationMenu.amount
    }
}

data class DiscountBenefit(
    val christmasDDay: Int = 0,
    val weekday: Int = 0,
    val weekend: Int = 0,
    val special: Int = 0,
) {
    fun getTotalPrice(): Int {
        return christmasDDay + weekday + weekend + special
    }
}
