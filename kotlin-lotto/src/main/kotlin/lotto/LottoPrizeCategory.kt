package lotto

enum class LottoPrizeCategory(
    val rank: Int,
    val prizeKoreaMoney: Int,
    val matchedBaseNumberCount: Int,
    val hitBonusNumber: Boolean,
) {
    FIRST_PRIZE(1, 2_000_000_000,  6, false),
    SECOND_PRIZE(2, 30_000_000, 5, true),
    THIRD_PRIZE(2, 1_500_000, 5, false),
    FOURTH_PRIZE(2, 50_000, 4, false),
    FIFTH_PRIZE(2, 5_000, 3, false),
    ;

    val prizeKoreaMoneyWithComma
        get() = prizeKoreaMoney.toString()
        .reversed()
        .chunked(3)
        .joinToString(",")
        .reversed()
}