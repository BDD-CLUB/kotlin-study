package lotto

import camp.nextstep.edu.missionutils.Randoms
import lotto.util.*

class LottoGenerator {

    private val _lottoList: MutableList<Lotto> = mutableListOf()
    val lottoList: List<Lotto> = _lottoList

    fun generate(amount: Int) {
        repeat(amount) {
            val numbers = Randoms.pickUniqueNumbersInRange(LOTTO_MIN_VALUE, LOTTO_MAX_VALUE, LOTTO_SIZE)
            val lotto = Lotto(numbers.sorted())
            _lottoList.add(lotto)
        }
    }

    fun printLottoList() {
        printLottoSizeMessage(lottoList.size)
        for (lotto in lottoList){
            lotto.printNumbers()
        }
    }
}
