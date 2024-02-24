package lotto.model

import camp.nextstep.edu.missionutils.Randoms
import lotto.validation.requireLotto

const val LOTTO_SIZE = 6
const val LOTTO_MIN_INCLUDE = 1
const val LOTTO_MAX_INCLUDE = 45
const val LOTTO_PRICE = 1000

class Lottos(val lottos: List<Lotto>) {
    val lottoCount = lottos.size
    val purchasePrice = lottoCount * LOTTO_PRICE

    companion object {
        fun generate(purchasePrice: PurchasePrice): Lottos {
            val lottoCount = purchasePrice.price / LOTTO_PRICE
            return Lottos(List(lottoCount) { Lotto.generate() })
        }
    }

    override fun toString(): String {
        return lottos.joinToString("\n")
    }
}

class Lotto(private val numbers: List<LottoNumber>) {
    init {
        requireLotto(numbers.size == LOTTO_SIZE)
        requireLotto(numbers.size == numbers.toSet().size)
    }

    companion object {
        fun create(numbers: List<Int>) = Lotto(numbers.map { LottoNumber(it) })
        fun generate() =
            Lotto(Randoms.pickUniqueNumbersInRange(LOTTO_MIN_INCLUDE, LOTTO_MAX_INCLUDE, LOTTO_SIZE)
                .map { LottoNumber(it) })
    }

    fun contains(number: LottoNumber) = this.numbers.contains(number)

    fun count(predicate: (LottoNumber) -> Boolean) = this.numbers.count(predicate)

    override fun toString(): String {
        return numbers.sortedBy { it.number }
            .joinToString(separator = ", ", prefix = "[", postfix = "]")
    }
}

@JvmInline
value class LottoNumber(val number: Int) {
    init {
        requireLotto(number >= LOTTO_MIN_INCLUDE)
        requireLotto(number <= LOTTO_MAX_INCLUDE)
    }

    override fun toString(): String {
        return number.toString()
    }
}
