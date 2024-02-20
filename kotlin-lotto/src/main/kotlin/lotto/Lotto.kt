package lotto

import camp.nextstep.edu.missionutils.Randoms

class Lotto(
    val numbers: List<Int> = Randoms.pickUniqueNumbersInRange(1, 45, 6)
) {
    init {
        require(numbers.size == 6)
    }

    val sortedLotto
        get() = numbers.sorted()

}
