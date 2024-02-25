package christmas

import camp.nextstep.edu.missionutils.test.NsTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EventControllerTest: NsTest() {

    private val eventController = EventController()

    @Test
    fun `적용된 이벤트가 하나도 없는 경우`() {
        val date = "26"
        val menu = "타파스-1,제로콜라-1"
        run(date, menu)
        assertThat(output()).contains(
            "<주문 메뉴>",
            "타파스 1개",
            "제로콜라 1개",
            "<할인 전 총주문 금액>",
            "8,500원",
            "<증정 메뉴>",
            "없음",
            "<혜택 내역>",
            "<총혜택 금액>",
            "<할인 후 예상 결제 금액>",
            "<12월 이벤트 배지>"
        )
    }

    @Test
    fun `기대하는 '12월 이벤트 플래너'의 예시 모습`() {
        val date = "3"
        val menu = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1"
        run(date, menu)
        assertThat(output()).contains(
            "<주문 메뉴>",
            "티본스테이크 1개",
            "바비큐립 1개",
            "초코케이크 2개",
            "제로콜라 1개",
            "<할인 전 총주문 금액>",
            "142,000원",
            "<증정 메뉴>",
            "샴페인 1개",
            "<혜택 내역>",
            "크리스마스 디데이 할인: -1,200원",
            "평일 할인: -4,046원",
            "특별 할인: -1,000원",
            "증정 이벤트: -25,000원",
            "<총혜택 금액>",
            "-31,246원",
            "<할인 후 예상 결제 금액>",
            "135,754원",
            "<12월 이벤트 배지>",
            "산타"
        )
    }

    override fun runMain() {
        eventController.execute()
    }
}
