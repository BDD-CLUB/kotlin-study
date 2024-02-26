package christmas.restaurant.event

import camp.nextstep.edu.missionutils.Console
import christmas.restaurant.MenuItem
import christmas.util.DiscountResult

class EventPlanner {
    fun greeting() {
        println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")
    }

    fun receiveVisitDate(): Int {
        println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)")

        do {
            val date = Console.readLine().toIntOrNull()
            if (date == null || date !in 1..31) {
                println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.")
                continue
            }
            return date
        } while (date !in 1..31)

        return 0
    }

    fun takeOrder(): List<List<String>> {
        println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)")
        val order = Console.readLine().split(",").map { it.split("-") }
        return order
    }

    fun previewEventBenefits(date: Int) {
        println("12월 ${date}에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n")
    }

    fun showOrderList(orderList: List<MenuItem>) {
        println("<주문 메뉴>")
        orderList.groupBy { it }.forEach { (menuItem, menuItems) ->
            println("${menuItem.name} ${menuItems.size}개")
        }
        println()
    }

    fun showPriceBeforeDiscount(totalPrice: Int) {
        println("<할인 전 총주문 금액>")
        println("${totalPrice.formatAsCurrency()}원\n")
    }

    fun showGiftList(activeEventBenefits: List<DiscountResult>) {
        println("<증정 메뉴>")

        val hasGiftEvent = activeEventBenefits.any {
            it.discountCode == Event.Discount.GIFT_EVENT
        }
        println(if (hasGiftEvent) "샴페인 1개\n" else "없음\n")
    }

    fun showTotalBenefits(activeEventBenefits: List<DiscountResult>) {
        println("<혜택 내역>")

        if (activeEventBenefits.isEmpty()) println("없음")
        else activeEventBenefits.forEach { discountResult ->
            println("${discountResult.discountCode!!.description} : -${discountResult.discountAmount.formatAsCurrency()}원")
        }

        println()
    }

    fun showTotalBenefitsPrice(totalBenefits: Int) {
        println("<총혜택 금액>")

        println(
            if (totalBenefits == 0) {
                "${totalBenefits.formatAsCurrency()}원\n"
            } else {
                "-${totalBenefits.formatAsCurrency()}원\n"
            }
        )
    }

    fun showPriceAfterDiscount(totalPrice: Int, totalBenefits: Int, activeEventBenefits: List<DiscountResult>) {
        val tangibleBenefit =
            if (activeEventBenefits.any {
                    it.discountCode == Event.Discount.GIFT_EVENT
                }) totalBenefits - Event.Discount.GIFT_EVENT.amount
            else totalBenefits

        println("<할인 후 예상 결제 금액>")
        println("${(totalPrice - tangibleBenefit).formatAsCurrency()}원\n")
    }

    fun showBadge(activeEventBenefits: Int) {
        println("<12월 이벤트 배지>")

        val totalBadge = RewardBadge.findByAmount(activeEventBenefits)
        println(totalBadge?.icon ?: "없음")

    }
}

fun Int.formatAsCurrency(): String = String.format("%,d", this)
