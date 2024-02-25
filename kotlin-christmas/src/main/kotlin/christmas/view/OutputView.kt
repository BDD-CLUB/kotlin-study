package christmas.view

import christmas.data.*
import christmas.enums.Badge
import christmas.util.*
import java.text.DecimalFormat

class OutputView {

    fun printReadDateErrorMessage() = println("$ERROR_PREFIX 유효하지 않은 날짜입니다. 다시 입력해 주세요.")

    fun printReadMenuErrorMessage() = println("$ERROR_PREFIX 유효하지 않은 주문입니다. 다시 입력해 주세요.")

    fun printWelcomeMessage() = println("안녕하세요! 우테코 식당 ${EVENT_MONTH}월 이벤트 플래너입니다.")

    fun printReadDateMessage() = println("${EVENT_MONTH}월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)")

    fun printReadMenuMessage() = println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)")

    fun printEventBenefitMessage(date: Int) = println("${EVENT_MONTH}월 ${date}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n")

    fun printMenuList(menuList: List<UserMenu>) {
        println("<주문 메뉴>")
        menuList.forEach{ println("${it.menu.koreanName} ${it.amount}개") }
        println()
    }

    fun printResult(benefit: EventBenefit) {
        printTotalAmountBeforeBenefit(benefit.totalAmountBeforeBenefit)
        printPresentationMenu(benefit.presentationMenu)
        printBenefits(benefit)
        printTotalBenefit(benefit.getBenefit())
        printTotalAmountAfterBenefit(benefit.getTotalAmountAfterBenefit())
        printBadge(benefit.badge)
    }

    private fun printTotalAmountBeforeBenefit(totalAmountBeforeBenefit: Int) {
        println("<할인 전 총주문 금액>")
        println("${totalAmountBeforeBenefit.decimalFormat()}원\n")
    }

    private fun printPresentationMenu(presentationMenu: UserMenu?) {
        val output = if (presentationMenu == null) NOTHING
            else "${presentationMenu.menu.koreanName} ${presentationMenu.amount}개"
        println("<증정 메뉴>")
        println("$output\n")
    }

    private fun printBenefits(eventBenefit: EventBenefit) {
        println("<혜택 내역>")
        if (eventBenefit.getBenefit() == 0) {
            println("$NOTHING\n")
            return
        }
        val discountBenefit = eventBenefit.discountBenefit
        val presentationMenu = eventBenefit.presentationMenu
        val presentationPrice = getPresentationPrice(presentationMenu)
        printAllBenefits(discountBenefit, presentationPrice)
    }

    private fun printTotalBenefit(benefit: Int) {
        val output = if (benefit == 0) "0원" else "-${benefit.decimalFormat()}원"
        println("<총혜택 금액>")
        println("$output\n")
    }

    private fun printTotalAmountAfterBenefit(totalAmountAfterBenefit: Int) {
        println("<할인 후 예상 결제 금액>")
        println("${totalAmountAfterBenefit.decimalFormat()}원\n")
    }

    private fun printBadge(badge: Badge?) {
        println("<${EVENT_MONTH}월 이벤트 배지>")
        println(badge?.badgeName ?: NOTHING)
    }

    private fun printAllBenefits(discountBenefit: DiscountBenefit, presentationPrice: Int) {
        printBenefit("크리스마스 디데이 할인", discountBenefit.christmasDDay)
        printBenefit("평일 할인", discountBenefit.weekday)
        printBenefit("주말 할인", discountBenefit.weekend)
        printBenefit("특별 할인", discountBenefit.special)
        printBenefit("증정 이벤트", presentationPrice)
        println()
    }

    private fun getPresentationPrice(presentationMenu: UserMenu?) =
        if (presentationMenu == null) 0 else presentationMenu.amount * presentationMenu.menu.price

    private fun printBenefit(eventName: String, price: Int) {
        if (price == 0) return
        println("$eventName: -${price.decimalFormat()}원")
    }

    private fun Int.decimalFormat(): String {
        return DecimalFormat("#,##0").format(this)
    }

}
