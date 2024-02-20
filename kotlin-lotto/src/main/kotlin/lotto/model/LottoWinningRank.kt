package lotto.model

import java.util.function.BiFunction

enum class LottoWinningRank(
        val matchCount: Int,
        val bonusCount: Int,
        val lottoPrice: Long,
        val isWinningRank: (matchCount: Int, bonusCount: Int) -> Boolean
) {

    FIRST_PLACE(6, 0, 2_000_000_000, { matchCount, bonusCount -> matchCount == 6 }),
    SECOND_PLACE(5, 1, 30_000_000, { matchCount, bonusCount -> matchCount == 5 && bonusCount == 1 }),
    THIRD_PLACE(5, 0, 1_500_000, { matchCount, bonusCount -> matchCount == 5 && bonusCount == 0 }),
    FOURTH_PLACE(4, 0, 50_000, { matchCount, bonusCount -> matchCount == 4}),
    FIFTH_PLACE(3, 0, 5_000, { matchCount, bonusCount -> matchCount == 3 }),
    ;

    fun isRankMatch(matchCount: Int, bonusCount: Int) =
            isWinningRank(matchCount, bonusCount)
}