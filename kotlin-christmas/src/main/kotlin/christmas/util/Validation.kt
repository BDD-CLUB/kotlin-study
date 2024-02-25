package christmas.util

import christmas.data.UserMenu
import christmas.enums.Category
import christmas.enums.Menu

class Validation {

    fun checkDate(input: String): Int {
        val inputInt = input.toIntOrNull()
        require(inputInt != null)
        require(inputInt in MIN_DATE..MAX_DATE)
        return inputInt
    }

    fun checkMenu(input: String): List<UserMenu> {
        val inputList = input.split(',')
        val menuList = inputList.map { menu ->
            UserMenu(getMenu(menu), getPrice(menu))
        }
        checkMenuList(menuList)
        return menuList
    }

    private fun checkMenuList(menuList: List<UserMenu>) {
        // 중복 메뉴가 있으면 안됨
        require(menuList.distinctBy { it.menu.koreanName }.size == menuList.size)

        // 음료만 주문하면 안됨
        require(!menuList.all { it.menu.category == Category.BEVERAGE })

        // 메뉴 개수는 총 20개 이하여야 함
        require(menuList.sumOf { it.amount } <= MENU_MAX_AMOUNT)
    }

    private fun getPrice(input: String): Int {
        val price = input.substringAfter('-').toIntOrNull()
        require(price != null)
        require(price > 0)
        return price
    }

    private fun getMenu(input: String): Menu {
        val name = input.substringBefore('-')
        return Menu.getByKoreanName(name)
    }

}
