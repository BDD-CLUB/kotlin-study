package christmas

import christmas.data.DiscountBenefit
import christmas.data.UserMenu
import christmas.enums.Category
import christmas.util.*

class GetDiscountBenefit(
    private val date: Int,
    private val menuList: List<UserMenu>
) {

    private var _discountBenefit = DiscountBenefit()
    val discountBenefit get() = _discountBenefit

    init {
        getBenefit()
    }

    private fun getBenefit() {
        christmasDDaySale()
        weekdaySale()
        weekendSale()
        specialSale()
    }

    private fun christmasDDaySale() {
        if (date !in D_DAY_MIN_DATE..D_DAY_MAX_DATE) {
            return
        }
        _discountBenefit = _discountBenefit.copy(
            christmasDDay = D_DAY_MIN_PRICE + (date - 1) * D_DAY_INCREASE_PRICE
        )
    }

    private fun weekdaySale() {
        // 평일 할인(일요일~목요일): 평일에는 디저트 메뉴를 메뉴 1개당 2,023원 할인
        if(isWeekday()) {
            val dessertMenuCount = getMenuCount(Category.DESSERT)
            _discountBenefit = _discountBenefit.copy(
                weekday = dessertMenuCount * WEEKDAY_DISCOUNT
            )
        }
    }

    private fun weekendSale() {
        // 주말 할인(금요일, 토요일): 주말에는 메인 메뉴를 메뉴 1개당 2,023원 할인
        if (isWeekend()){
            val mainMenuCount = getMenuCount(Category.MAIN)
            _discountBenefit = _discountBenefit.copy(
                weekend = mainMenuCount * WEEKEND_DISCOUNT
            )
        }
    }

    private fun specialSale() {
        // 특별 할인: 이벤트 달력에 별이 있으면 총주문 금액에서 1,000원 할인
        if (isSpecialDay()){
            _discountBenefit = _discountBenefit.copy(
                special = SPECIAL_DISCOUNT
            )
        }
    }

    private fun getMenuCount(category: Category) =
        menuList.filter{ it.menu.category == category }.sumOf { it.amount }

    private fun isWeekday() = !isWeekend()

    private fun isWeekend() = date % 7 in listOf(1, 2)

    private fun isSpecialDay() = date % 7 == 3 || date == 25

}
