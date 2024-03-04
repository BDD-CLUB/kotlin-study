package christmas.view

import camp.nextstep.edu.missionutils.Console
import christmas.domain.Menu
import christmas.domain.MenuType
import christmas.dto.OrderMenuDto
import christmas.global.Component

@Component
class InputView {

    companion object {
        const val ERROR_PREFIX = "[ERROR]"
    }

    fun getVisitDay(): Int {
        val visitDay = (Console.readLine()
                ?.toIntOrNull()
                ?: throw IllegalArgumentException("숫자만 입력해 주세요."))

        requireNotNull(visitDay in 1..31) {
            IllegalArgumentException("$ERROR_PREFIX 유효하지 않은 날짜입니다. 다시 입력해 주세요.")
        }

        return visitDay
    }

    fun getOrderMenuAndCount(userVisitDay: Int): OrderMenuDto {
        val orders = mutableMapOf<Menu, Int>()
        val menuName = Menu.entries.associateBy { it.mainMenuName }

        val menuAndCount = Console.readLine()
                ?.split(",")
                ?: throw IllegalArgumentException("입력 값이 없습니다.")

        menuAndCount.forEach { item ->
            val parts = item.split("-")
            val menu = menuName[parts[0]] ?: throw IllegalArgumentException("$ERROR_PREFIX 유효하지 않은 주문입니다. 다시 입력해 주세요.")
            val count = parts[1].toInt()

            require(count > 0) {
                "$ERROR_PREFIX 유효하지 않은 주문입니다. 다시 입력해 주세요."
            }

            orders[menu] = count
        }

        if (orders.keys.all { it.menuType == MenuType.DRINK }) throw IllegalArgumentException("$ERROR_PREFIX 음료만 주문 시, 주문할 수 없습니다.")


        require(orders.values.count() < 20) {
            "$ERROR_PREFIX 20개 이상의 음식은 주문하실 수 없습니다."
        }

        return OrderMenuDto(userVisitDay, orders)
    }

}