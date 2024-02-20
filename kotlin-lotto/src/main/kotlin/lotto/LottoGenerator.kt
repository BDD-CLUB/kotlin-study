package lotto

import camp.nextstep.edu.missionutils.Randoms

class LottoGenerator {

    private val _lottoList: MutableList<Lotto> = mutableListOf()
    val lottoList: List<Lotto> = _lottoList

    fun generate(amount: Int) {
        for (i in 0 until amount) {
            val numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6)
            val lotto = Lotto(numbers.sorted())
            _lottoList.add(lotto)
        }
    }

    fun printLottoList() {
        println("${lottoList.size}개를 구매했습니다.")
        for (lotto in lottoList){
            lotto.printNumbers()
        }
    }
}
