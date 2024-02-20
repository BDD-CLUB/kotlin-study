package lotto

import lotto._enums.LottoResult
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

    fun getResult(lottoBuyerDTO: LottoBuyerDTO): LottoResult? {
        val correctNum = numbers.count { num ->
            lottoBuyerDTO.winningNumber.contains(num)
        }
        val isBonusCorrect = numbers.contains(lottoBuyerDTO.bonusNumber)
        return LottoResult.get(correctNum, isBonusCorrect)
    }

}
