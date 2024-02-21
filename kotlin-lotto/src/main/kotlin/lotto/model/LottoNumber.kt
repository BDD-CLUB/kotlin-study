package lotto.model

data class LottoNumber(val number: Int) {

    init {
        require(number in MIN_LOTTO_VALUE..MAX_LOTTO_VALUE)
    }

    companion object {
        const val MIN_LOTTO_VALUE = 1
        const val MAX_LOTTO_VALUE = 45
    }

    override fun toString(): String {
        return number.toString()
    }
}
