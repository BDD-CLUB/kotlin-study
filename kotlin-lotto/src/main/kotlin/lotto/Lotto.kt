package lotto

import lotto.util.*

class Lotto(private val numbers: List<Int>) {

    init {
        numbers.checkLottoSize()
        numbers.checkDuplication()
        numbers.checkInRange()
    }

    fun printNumbers() {
        println(numbers.toString())
    }

}
