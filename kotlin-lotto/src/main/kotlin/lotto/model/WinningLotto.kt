package lotto.model

class WinningLotto(private val lotto: Lotto, private val bonusNumber: LottoNumber) {

    fun matchCount(other: Lotto) = this.lotto.count { other.contains(it) }

    fun bonusMatch(other: Lotto) = other.contains(this.bonusNumber)
}
