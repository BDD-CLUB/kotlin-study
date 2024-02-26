package christmas.restaurant


sealed class MenuItem(val name: String, val price: Int) {
    class Appetizer(name: String, price: Int) : MenuItem(name, price)
    class Main(name: String, price: Int) : MenuItem(name, price)
    class Dessert(name: String, price: Int) : MenuItem(name, price)
    class Drink(name: String, price: Int) : MenuItem(name, price)
}

object Menu {
    val items = listOf(
        MenuItem.Appetizer("양송이수프", 6000),
        MenuItem.Appetizer("타파스", 5500),
        MenuItem.Appetizer("시저샐러드", 8000),
        MenuItem.Main("티본스테이크", 55000),
        MenuItem.Main("바비큐립", 54000),
        MenuItem.Main("해산물파스타", 35000),
        MenuItem.Main("크리스마스파스타", 25000),
        MenuItem.Dessert("초코케이크", 15000),
        MenuItem.Dessert("아이스크림", 5000),
        MenuItem.Drink("제로콜라", 3000),
        MenuItem.Drink("레드와인", 60000),
        MenuItem.Drink("샴페인", 25000)
    )
}
