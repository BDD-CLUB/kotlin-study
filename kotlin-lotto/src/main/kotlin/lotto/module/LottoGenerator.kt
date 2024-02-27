package lotto.module

import camp.nextstep.edu.missionutils.Randoms
import lotto.controller.LOTTO_MAX_NUMBER
import lotto.controller.LOTTO_MIN_NUMBER
import lotto.controller.LOTTO_NUMBERS_COUNT
import lotto.model.Lotto

class LottoGenerator {
    fun getLotto(): Lotto {
        return Lotto(Randoms.pickUniqueNumbersInRange(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER, LOTTO_NUMBERS_COUNT))
    }
}