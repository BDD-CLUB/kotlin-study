package christmas.model

import christmas.controller.DECEMBER_EVENT_ORDER_MAXIMUM_QUANTITY


class OrderMenus(
    val menuAndQuantityMap: Map<Menu, Int>,
) {

    val totalPriceBeforeDiscount
        get() = menuAndQuantityMap.entries.sumOf { it.key.price * it.value }

    init {
        require(menuAndQuantityMap.entries.sumOf { it.value } <= DECEMBER_EVENT_ORDER_MAXIMUM_QUANTITY )
        require(!(menuAndQuantityMap.keys.all { it.menuType == MenuType.DRINK }))
    }
}