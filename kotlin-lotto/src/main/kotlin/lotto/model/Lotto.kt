package lotto.model

class Lotto(private val lottoNumbers: List<LottoNumber>) {

    init {
        require(lottoNumbers.size == MAX_LOTTO_SIZE)
    }

    fun countMatch(other: Lotto): Int {
        return lottoNumbers.count { it in other.lottoNumbers }
    }

    fun contains(bonusNumber: LottoNumber): Boolean {
        return lottoNumbers.contains(bonusNumber)
    }

    companion object {
        const val MAX_LOTTO_SIZE = 6
    }

    override fun toString(): String {
        return lottoNumbers.sortedBy { it.number }.toString()
    }
}
