package lotto.model

class WinningLotto(private val lotto: Lotto, private val bonusNumber: LottoNumber) {
    fun compare(other: Lotto): Rank? {
        val matchCount = this.matchCount(other)
        val bonusMatch = this.bonusMatch(other)
        return Rank.getRank(matchCount, bonusMatch)
    }

    private fun matchCount(other: Lotto) = this.lotto.count { other.contains(it) }

    private fun bonusMatch(other: Lotto) = other.contains(this.bonusNumber)
}
