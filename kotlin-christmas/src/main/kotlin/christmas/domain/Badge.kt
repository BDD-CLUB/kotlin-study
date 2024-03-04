package christmas.domain

enum class Badge(
        val badgeName: String,
        val badgePrice: Long
) {

    SANTA("산타", 20_000L),
    TREE("트리", 10_000L),
    STAR("별", 5_000L),
    NOTHING("없음", 0L),

}