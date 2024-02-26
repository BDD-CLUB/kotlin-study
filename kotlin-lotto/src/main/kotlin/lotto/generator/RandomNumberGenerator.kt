package lotto.generator

import camp.nextstep.edu.missionutils.Randoms
import lotto.model.Lotto.Companion.MAX_LOTTO_SIZE
import lotto.model.LottoNumber.Companion.MAX_LOTTO_VALUE
import lotto.model.LottoNumber.Companion.MIN_LOTTO_VALUE

class RandomNumberGenerator : NumberGenerator {
    override fun generate(): List<Int> {
        return Randoms.pickUniqueNumbersInRange(MIN_LOTTO_VALUE, MAX_LOTTO_VALUE, MAX_LOTTO_SIZE)
    }
}
