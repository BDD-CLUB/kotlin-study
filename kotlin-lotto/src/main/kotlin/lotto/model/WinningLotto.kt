package lotto.model

class WinningLotto(private val lotto: Lotto, private val bonusNumber: LottoNumber) {

    fun matchCount(other: Lotto) = this.lotto.numbers.count { other.numbers.contains(it) }

    fun bonusMatch(other: Lotto) = other.numbers.contains(this.bonusNumber)
}
