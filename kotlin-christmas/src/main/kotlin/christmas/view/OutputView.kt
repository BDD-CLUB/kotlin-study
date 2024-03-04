package christmas.view

import christmas.domain.Menu
import christmas.domain.OrderMenu
import christmas.global.Component

@Component
class OutputView {

    fun printEventMessage() {
        println("""
            안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.
            12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)
        """.trimIndent())
    }

    fun printMenuAndCountMessage() {
        println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2, 레드와인-1, 초코케이크-1)")
    }

    fun printEventListMessage(userVisitDay: Int) {
        println("12월 ${userVisitDay}에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n")
    }

    fun printReceipt(ordersDto: OrderMenu) {
        val giveawayMenu = if (ordersDto.giveMenu != Menu.NOTHING) {
            ordersDto.giveMenu.mainMenuName + " 1개"
        } else {
            "없음"
        }

        println("<주문 메뉴>")
        ordersDto.orderList.forEach { (menu, count) ->
            println("${menu.mainMenuName}: $count 개")
        }

        println("\n<할인 전 총주문 금액>")
        println(String.format("%,d원", ordersDto.amountBeforeDiscount))

        println("\n<증정 메뉴>")
        println(giveawayMenu)

        println("\n<혜택 내역>")
        ordersDto.benefitDetails.forEach { (key, value) ->
            println("$key: ${String.format("-%,d원", value)}")
        }

        println("\n<총혜택 금액>")
        println(String.format("-%,d원", ordersDto.totalDiscountAmount))

        println("\n<할인 후 예상 결제 금액>")
        println(String.format("%,d원", ordersDto.amountBeforeDiscount - ordersDto.totalDiscountAmount + ordersDto.giveMenu.mainMenuPrice))

        println("\n<12월 이벤트 배지>")
        println(ordersDto.badge.badgeName)
    }

}