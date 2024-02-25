package lotto.util

import camp.nextstep.edu.missionutils.Console
import lotto._const.DELIMITER
import lotto._const.LOTTO_AMOUNT

class InputManager {

    fun getPurchaseAmount(): Int {
        val purchaseAmount = getInput().toIntValue()
        purchaseAmount.checkDividable()
        purchaseAmount.checkPositive()
        return purchaseAmount / LOTTO_AMOUNT
    }

    fun getWinningNumber(): List<Int> {
        val winningNumber = getInput().split(DELIMITER)
        winningNumber.checkLottoSize()
        winningNumber.checkDuplication()

        return winningNumber.map {
            val value = it.toIntValue()
            value.checkInRange()
            value
        }
    }

    fun getBonusNumber(winningNumber: List<Int>): Int {
        val bonusNumber = getInput().toIntValue()
        bonusNumber.checkInRange()
        bonusNumber.checkDuplicationWithWinningNumber(winningNumber)
        return bonusNumber
    }

    private fun getInput() = Console.readLine() ?: ""
}
