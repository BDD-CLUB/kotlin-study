package christmas.menu

import christmas.model.Price

enum class Menu(val price: Price, val menuType: MenuType) {
    양송이스프(Price(6_000), MenuType.Appetizer),
    타파스(Price(5_500), MenuType.Appetizer),
    시저샐러드(Price(8_000), MenuType.Appetizer),
    티본스테이크(Price(55_000), MenuType.Main),
    바비큐립(Price(54_000), MenuType.Main),
    해산물파스타(Price(35_000), MenuType.Main),
    크리스마스파스타(Price(25_000), MenuType.Main),
    초코케이크(Price(15_000), MenuType.Dessert),
    아이스크림(Price(5_000), MenuType.Dessert),
    제로콜라(Price(3_000), MenuType.Drink),
    레드와인(Price(60_000), MenuType.Drink),
    샴페인(Price(25_000), MenuType.Drink),
    ;

}

enum class MenuType {
    Appetizer,
    Main,
    Dessert,
    Drink,
    ;
}

data class MenuWithCount(val menu: Menu, val count: Int) {
    val benefitAmount = menu.price * count

    override fun toString() = "${this.menu.name} ${this.count}개"
}
