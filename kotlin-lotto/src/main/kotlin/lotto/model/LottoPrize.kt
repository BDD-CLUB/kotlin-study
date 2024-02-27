package lotto.model

import lotto.exception.LottoException

enum class LottoPrize(
    val rank: Int,
    val prizeKoreaMoney: Long,
    val matchedBaseNumberCount: Int,
    val isBonusNumberMatched: Boolean,
) {
    FIRST_PRIZE(1, 2_000_000_000L,  6, false),
    SECOND_PRIZE(2, 30_000_000L, 5, true),
    THIRD_PRIZE(3, 1_500_000L, 5, false),
    FOURTH_PRIZE(4, 50_000L, 4, false),
    FIFTH_PRIZE(5, 5_000L, 3, false),
    ;

    val prizeKoreaMoneyWithComma
        get() = prizeKoreaMoney.toString()
        .reversed()
        .chunked(3)
        .joinToString(",")
        .reversed()

    companion object{
        fun findPrize(matchedBaseNumberCount: Int, isBonusNumberMatched: Boolean): LottoPrize? {
            return entries.firstOrNull() {
                it.matchedBaseNumberCount ==  matchedBaseNumberCount &&
                        it.isBonusNumberMatched == isBonusNumberMatched
            }
        }
    }
}
