package christmas.view

import camp.nextstep.edu.missionutils.Console
import christmas.menu.Menu
import christmas.menu.MenuWithCount
import christmas.model.UserOrderInfo
import christmas.model.UserOrderInfo.UserOrderMenu
import christmas.model.UserOrderInfo.VisitDay
import christmas.validation.ChristmasException

object InputView {
    fun getUserOrderInfo(): UserOrderInfo {
        val visitDay = tryGetUserInput({ println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)") })
        { Console.readLine().toVisitDay() }
        val userOrderMenu = tryGetUserInput({ println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)") })
        { Console.readLine().toUserOrderMenu() }
        return UserOrderInfo(userOrderMenu, visitDay)
    }
}

fun String.toVisitDay() =
    try {
        VisitDay(this.toInt())
    } catch (e: ChristmasException) {
        throw e
    } catch (e: Exception) {
        throw ChristmasException("유효하지 않은 날짜입니다. 다시 입력해 주세요.")
    }

fun String.toUserOrderMenu() =
    try {
        val userOrderMenu = mutableListOf<MenuWithCount>()
        for (menuAndCount in this.split(",")) {
            val (menuString, countString) = menuAndCount.split("-")
            val menu = Menu.valueOf(menuString)
            val count = countString.toInt()
            val menuWithCount = MenuWithCount(menu, count)
            if (userOrderMenu.contains(menuWithCount)) {
                throw IllegalArgumentException()
            }
            userOrderMenu.add(menuWithCount)
        }
        UserOrderMenu(userOrderMenu)
    } catch (e: ChristmasException) {
        throw e
    } catch (e: Exception) {
        throw ChristmasException("유효하지 않은 주문입니다. 다시 입력해 주세요.")
    }

private fun <T> tryGetUserInput(guideMessage: (() -> Unit)? = null, inputFunction: () -> T): T {
    while (true) {
        if (guideMessage != null) {
            guideMessage()
        }
        try {
            return inputFunction()
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }
}
