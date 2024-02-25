package christmas.enums

enum class Badge(
    val badgeName: String,
    val price: Int
){
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000),
    ;

    companion object {
        fun getByValue(value: Int): Badge? {
            return when (value) {
                in 0 until STAR.price -> null
                in STAR.price until TREE.price -> STAR
                in TREE.price until SANTA.price -> TREE
                else -> SANTA
            }
        }
    }

}
