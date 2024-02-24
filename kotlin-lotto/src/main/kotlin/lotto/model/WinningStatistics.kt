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
