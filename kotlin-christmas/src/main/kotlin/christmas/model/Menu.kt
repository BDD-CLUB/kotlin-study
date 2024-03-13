package christmas.model

import christmas.model.MenuType.*

enum class Menu (
    val menuType: MenuType,
    val itemName: String,
    val price: Int
) {

    CREAM_OF_MUSHROOM_SOUP(APPETIZER, "양송이수프", 6_000),
    TAPAS(APPETIZER, "타파스", 5_500),
    CAESAR_SALAD(APPETIZER, "시저샐러드", 8_000),

    T_BONE_STEAK(MAIN_COURSE, "티본스테이크", 55_000),
    BBQ_RIBS(MAIN_COURSE, "바비큐립", 54_000),
    SEAFOOD_PASTA(MAIN_COURSE, "해산물파스타", 35_000),
    CHRISTMAS_PASTA(MAIN_COURSE, "크리스마스파스타", 25_000),

    CHOCOLATE_CAKE(DESSERT, "초코케이크", 15_000),
    ICE_CREAM(DESSERT, "아이스크림", 5_000),

    ZERO_COLA(DRINK, "제로콜라", 3_000),
    RED_WINE(DRINK, "레드와인", 60_000),
    CHAMPAGNE(DRINK, "샴페인", 25_000),
    ;

    companion object {
        fun findByItemName(itemName: String): Menu? {
            return Menu.entries.firstOrNull { it.itemName == itemName }
        }
    }
}

enum class MenuType (
    val menuSectionName: String
) {
    APPETIZER("애피타이저"),
    MAIN_COURSE("메인"),
    DESSERT("디저트"),
    DRINK("음료")
    ;
}