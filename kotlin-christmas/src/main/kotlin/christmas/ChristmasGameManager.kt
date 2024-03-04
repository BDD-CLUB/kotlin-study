package christmas

import christmas.global.Component
import christmas.view.InputView
import christmas.view.OutputView

@Component
class ChristmasGameManager(
    private val inputView: InputView,
    private val outputView: OutputView,
) {

    fun run() {
        // STEP1 > 식당 예상 방문 날짜 입력 받기
        outputView.printEventMessage()
        val userVisitDay = inputView.getVisitDay()

        // STEP2 > 주문할 메뉴와 개수 입력 받기
        outputView.printMenuAndCountMessage()
        val ordersDto = inputView.getOrderMenuAndCount(userVisitDay)

        // STEP3 > 이벤트 혜택 미리보기 출력
        outputView.printEventListMessage(userVisitDay)

        // STEP4 > 주문 메뉴 출력
        outputView.printReceipt(ordersDto)
    }

}