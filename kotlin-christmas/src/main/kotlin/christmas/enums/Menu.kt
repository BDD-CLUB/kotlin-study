package christmas.enums

enum class Menu (
    val koreanName: String,
    val price: Int,
    val category: Category
) {
    // 애피타이저
    MUSHROOM_SOUP("양송이수프", 6_000, Category.APPETIZER),
    TAPAS("타파스", 5_500, Category.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8_800, Category.APPETIZER),

    // 메인
    T_BONE_STEAK("티본스테이크", 55_000, Category.MAIN),
    BBQ_LIP("바비큐립", 54_000, Category.MAIN),
    SEAFOOD_PASTA("해산물파스타", 35_000, Category.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, Category.MAIN),

    // 디저트
    CHOCOLATE_CAKE("초코케이크", 15_000, Category.DESSERT),
    ICE_CREAM("아이스크림", 5_000, Category.DESSERT),

    // 음료
    ZERO_COKE("제로콜라", 3_000, Category.BEVERAGE),
    RED_WINE("레드와인", 60_000, Category.BEVERAGE),
    CHAMPAGNE("샴페인", 25_000, Category.BEVERAGE),
    ;

    companion object {
        fun getByKoreanName(name: String): Menu {
            return entries.firstOrNull { it.koreanName == name } ?: throw IllegalArgumentException()
        }
    }
}

enum class Category(
    private val koreanName: String
) {
    APPETIZER("애피타이저"),
    MAIN("메인"),
    DESSERT("디저트"),
    BEVERAGE("음료"),
}
