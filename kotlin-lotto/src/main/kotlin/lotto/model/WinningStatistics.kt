package lotto.model

import lotto.validation.LottoException
import java.text.DecimalFormat

class WinningStatistics(lottos: Lottos, winningLotto: WinningLotto) {
    private val result = Result(lottos, winningLotto)

    override fun toString(): String {
        return Rank.entries.joinToString("\n") { "$it - ${result[it]}개" } + "\n" +
                "총 수익률은 ${DecimalFormat("#,##0.0").format(result.rateOfReturn)}%입니다."
    }
}

fun WinningLotto.compare(other: Lotto): Rank? {
    val matchCount = this.matchCount(other)
    val bonusMatch = this.bonusMatch(other)
    return Rank.getRank(matchCount, bonusMatch)
}

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

private class Result(private val lottos: Lottos, private val winningLotto: WinningLotto) {
    private val result: Map<Rank, Int> = Rank.entries.associateWith { 0 }
        .toMutableMap()
        .apply {
            for (lotto in lottos.lottos) {
                val rank = winningLotto.compare(lotto) ?: continue
                put(rank, get(rank)!! + 1)
            }
        }

    val rateOfReturn =
        result.map { (rank, count) -> rank.winningPrice * count }.sum().toDouble() / lottos.purchasePrice * 100

    operator fun get(rank: Rank) = result[rank] ?: throw LottoException("존재하지 않는 rank입니다. rank: $rank")
}
