package christmas.view

import camp.nextstep.edu.missionutils.Console
import christmas.data.UserMenu
import christmas.enums.Menu
import christmas.util.*

class InputView(
    private val outputView: OutputView = OutputView(),
    private val validation: Validation = Validation()
) {

    fun readDate(): Int {
        return try {
            val input = Console.readLine() ?: ""
            validation.checkDate(input)
        } catch (e: IllegalArgumentException) {
            outputView.printReadDateErrorMessage()
            readDate()
        }
    }

    fun readMenu(): List<UserMenu> {
        return try {
            val input = Console.readLine() ?: ""
            validation.checkMenu(input)
        } catch (e: IllegalArgumentException) {
            outputView.printReadMenuErrorMessage()
            readMenu()
        }
    }

}
