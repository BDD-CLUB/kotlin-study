package christmas

import christmas.data.UserMenu
import christmas.view.InputView
import christmas.view.OutputView

class EventController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView()
) {

    fun execute() {
        val date = readDate()
        val menuList = readMenu()
        outputView.printEventBenefitMessage(date)
        outputView.printMenuList(menuList)

        val eventService = EventService(date, menuList)
        outputView.printResult(eventService.eventBenefit)
    }

    private fun readDate(): Int {
        outputView.printWelcomeMessage()
        outputView.printReadDateMessage()
        return inputView.readDate()
    }

    private fun readMenu(): List<UserMenu> {
        outputView.printReadMenuMessage()
        return inputView.readMenu()
    }
}
