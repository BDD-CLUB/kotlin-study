package christmas

import christmas.data.*
import christmas.enums.Menu
import christmas.util.*

class EventService(
    private val date: Int,
    private val menuList: List<UserMenu>,
    private val getDiscountBenefit: GetDiscountBenefit = GetDiscountBenefit(date, menuList),
) {
    private var _eventBenefit = EventBenefit()
    val eventBenefit get() = _eventBenefit

    init {
        saveTotalAmountBeforeBenefit()
        getBenefit()
        getBadge()
    }

    private fun saveTotalAmountBeforeBenefit() {
        val totalAmount = menuList.sumOf { it.menu.price * it.amount }
        _eventBenefit = _eventBenefit.copy(
            totalAmountBeforeBenefit = totalAmount
        )
    }

    private fun getBenefit() {
        if (eventBenefit.totalAmountBeforeBenefit < EVENT_START_AMOUNT) {
            return
        }
        _eventBenefit = _eventBenefit.copy(
            discountBenefit = getDiscountBenefit.discountBenefit,
            presentationMenu = getPresentationMenu()
        )
    }

    private fun getPresentationMenu(): UserMenu? {
         if (eventBenefit.totalAmountBeforeBenefit >= PRESENTATION_AMOUNT) {
             return UserMenu(Menu.CHAMPAGNE,1)
         }
         return null
    }

    private fun getBadge() {
        val benefitAmount = eventBenefit.getBenefit()
        _eventBenefit = _eventBenefit.copy(
            badge = Badge.getByValue(benefitAmount)
        )
    }

}
