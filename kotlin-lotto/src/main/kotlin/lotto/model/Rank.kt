package lotto.model

import java.text.DecimalFormat

/**
 * 출력하고자 하는 순서대로 상수들을 선언해주셔야 올바른 순서로 출력됩니다.
 */
enum class Rank(
    private val matchCount: Int,
    private val bonusMatch: Bonus,
    val winningPrice: Long,
    val descriptionMessage: String
) {
    FIFTH(3, Bonus.ALL, 5_000, "3개 일치"),
    FOURTH(4, Bonus.ALL, 50_000, "4개 일치"),
    THIRD(5, Bonus.MISMATCH, 1_500_000, "5개 일치"),
    SECOND(5, Bonus.MATCH, 30_000_000, "5개 일치, 보너스 볼 일치"),
    FIRST(6, Bonus.ALL, 2_000_000_000, "6개 일치");

    companion object {
        fun getRank(matchCount: Int, bonusMatch: Boolean): Rank? {
            for (rank in entries) {
                if (rank.matchCount == matchCount && rank.bonusMatch.isMatch(bonusMatch)) {
                    return rank
                }
            }
            return null
        }
    }

    internal enum class Bonus(val isMatch: (Boolean) -> Boolean) {
        MATCH({ bonusMatch -> bonusMatch }),
        MISMATCH({ bonusMatch -> !bonusMatch }),
        ALL({ true });
    }

    override fun toString(): String {
        return "$descriptionMessage (${DecimalFormat("#,###").format(winningPrice)}원)"
    }
}
