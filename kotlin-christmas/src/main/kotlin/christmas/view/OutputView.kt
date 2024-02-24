package christmas.view

import christmas.event.EventType
import christmas.model.UserBenefitInfo
import christmas.model.UserOrderInfo

object OutputView {
    fun printWelcomeMessage() {
        println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")
    }

    fun printUserEventBenefit(userOrderInfo: UserOrderInfo, benefitInfo: UserBenefitInfo) {
        val month = userOrderInfo.estimatedVisitDate.month.value
        val day = userOrderInfo.estimatedVisitDate.dayOfMonth
        println(
            """
            |${month}월 ${day}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!
            |
            |<주문 메뉴>
            |${userOrderInfo.userOrderMenu}
            |
            |<할인 전 총주문 금액>
            |${userOrderInfo.totalPrice}
            |
            |<증정 메뉴>
            |${benefitInfo.giveaway?.menuWithCount ?: "없음"}
            |
            |<혜택 내역>
            |${benefitInfo.benefitList.joinToString("\n").ifEmpty { "없음" }}
            |
            |<총혜택 금액>
            |${benefitInfo.totalBenefitAmount.toMinusString()}
            |
            |<할인 후 예상 결제 금액>
            |${userOrderInfo.totalPrice - benefitInfo.totalDiscountAmount}
            |
            |<12월 이벤트 배지>
            |${benefitInfo.eventBadge ?: "없음"}
            """.trimMargin()
        )
    }
}
