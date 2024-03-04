package christmas.model

enum class DecemberEventBadge(
    val description: String,
    val minimumPrice: Int
) {
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000)
    ;

    companion object {
        fun getBadgeForPrice(price: Int): DecemberEventBadge? {
            return entries.lastOrNull { price >= it.minimumPrice }
        }
    }
}
