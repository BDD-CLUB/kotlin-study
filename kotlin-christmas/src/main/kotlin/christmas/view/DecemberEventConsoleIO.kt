package christmas.view

import camp.nextstep.edu.missionutils.Console
import christmas.exception.DecemberEventException
import christmas.model.*
import christmas.controller.DECEMBER_EVENT_END_DAY
import christmas.controller.DECEMBER_EVENT_START_DAY

const val ORDER_MENU_SEPARATOR = ","
const val ORDER_MENU_QUANTITY_SEPARATOR = "-"

class DecemberEventConsoleIO {

    fun printGreetings() {
        println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")
    }

    fun getVisitDate(): Int {
        println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)")
        return getValidInput { Console.readLine().toValidVisitDate() }
    }

    fun getOrderMenus(): OrderMenus {
        println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)")
        return getValidInput { Console.readLine().toValidOrderMenus() }
    }

    fun printUpcomingEventBenefitsAtVisitDate(visitDate: Int) {
        println("12월 ${visitDate}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!")
    }

    fun printOrderMenus(orderMenus: OrderMenus) {
        println()
        println("<주문 메뉴>")
        orderMenus.menuAndQuantityMap.entries.forEach{
            println("${it.key.itemName} ${it.value}개")
        }
    }

    fun printTotalPriceBeforeDiscount(totalPriceBeforeDiscount: Int) {
        println()
        println("<할인 전 총주문 금액>")
        println("${totalPriceBeforeDiscount.toPriceWithComma}원")
    }

    fun printGiftMenuReport(giftMenu: GiftMenu?) {
        println()
        println("<증정 메뉴>")
        println(giftMenu?.let { "${it.menu.itemName} ${it.quantity}개" } ?: "없음")
    }

    fun printBenefitReport(discount: Discount) {
        val discountDetails = mapOf(
            "크리스마스 디데이 할인" to discount.christmasDDayDiscountPrice,
            "평일 할인" to discount.dailyDiscountPrice,
            "주말 할인" to discount.holidayDiscountPrice,
            "특별 할인" to discount.specialDiscountPrice,
            "증정 이벤트" to discount.giftStuckBenefitPrice
        ).filter { it.value != 0 }

        println()
        println("<혜택 내역>")
        if (discountDetails.isEmpty()) {
            println("없음")
            return
        }

        discountDetails.forEach { (name, amount) ->
            if (amount != 0) println("${name}: -${amount.toPriceWithComma}원")
        }
    }

    fun printTotalBenefitPrice(totalBenefitPrice: Int) {
        println()
        println("<총혜택 금액>")
        if (totalBenefitPrice == 0) println("없음")
        else println("-${totalBenefitPrice.toPriceWithComma}원")
    }

    fun printPaymentPriceAfterDiscount(paymentPrice: Int) {
        println()
        println("<할인 후 예상 결제 금액>")
        println("${paymentPrice.toPriceWithComma}원")
    }

    fun printDecemberEventBadge(totalBenefitPrice: Int) {
        println()
        println("<12월 이벤트 배지>")
        println(DecemberEventBadge.getBadgeForPrice(totalBenefitPrice)?.description ?: "없음")
    }

}

private fun <T> getValidInput(inputFunction: () -> T): T {
    while (true) {
        try {
            return inputFunction()
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }
}

private fun String.toValidVisitDate(): Int {
    return this.toIntOrNull()
        ?.takeIf { it in DECEMBER_EVENT_START_DAY..DECEMBER_EVENT_END_DAY }
        ?: throw DecemberEventException("유효하지 않은 날짜입니다. 다시 입력해 주세요.")
}

private fun String.toValidOrderMenus(): OrderMenus {
    val newMenuAndQuantityMap: MutableMap<Menu, Int> = mutableMapOf()
    this.split(ORDER_MENU_SEPARATOR).forEach {
        val part = it.split(ORDER_MENU_QUANTITY_SEPARATOR)
        if (part.size != 2) throw DecemberEventException("유효하지 않은 주문입니다. 다시 입력해 주세요.")

        val menuName = part[0]
        val quantity = part[1].toIntOrNull()?.takeIf {quantity -> quantity >= 1 }
            ?: throw DecemberEventException("유효하지 않은 주문입니다. 다시 입력해 주세요.")
        val menu = Menu.findByItemName(menuName) ?: throw DecemberEventException("유효하지 않은 주문입니다. 다시 입력해 주세요.")
        if (newMenuAndQuantityMap[menu] != null) throw DecemberEventException("유효하지 않은 주문입니다. 다시 입력해 주세요.")
        newMenuAndQuantityMap[menu] = quantity
    }
    return OrderMenus(newMenuAndQuantityMap)
}

val Int.toPriceWithComma
    get() = this.toString()
        .reversed()
        .chunked(3)
        .joinToString(",")
        .reversed()