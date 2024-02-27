package lotto.view

import camp.nextstep.edu.missionutils.Console
import lotto.controller.*
import lotto.exception.LottoException
import lotto.model.Lotto
import lotto.model.LottoPrizeStatistics
import lotto.model.LottoWinningNumbers

class LottoConsoleIO {
    private fun <T> getUserInput(inputFunction: () -> T): T {
        while (true) {
            try {
                return inputFunction()
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    fun getAmount(): Int{
        println("구입 금액을 입력해 주세요.")
        return getUserInput { Console.readLine().toValidAmountByPrice() }
    }

    fun getLottoPrizeNumbers(): LottoWinningNumbers {
        val lottoPrizeBaseNumber = getLottoPrizeBaseNumbers()
        val lottoPrizeBonusNumber = getLottoPrizeBonusNumber(lottoPrizeBaseNumber)
        return LottoWinningNumbers(
            baseNumbers = lottoPrizeBaseNumber,
            bonusNumber = lottoPrizeBonusNumber
        )
    }

    private fun getLottoPrizeBaseNumbers(): List<Int> {
        println()
        println("당첨 번호를 입력해 주세요.")
        return getUserInput { Console.readLine().toValidLottoPrizeBaseNumbers() }
    }

    private fun getLottoPrizeBonusNumber(lottoPrizeBaseNumber: List<Int>): Int {
        println()
        println("보너스 번호를 입력해 주세요.")
        return getUserInput { Console.readLine().toValidLottoPrizeBonusNumbers(lottoPrizeBaseNumber) }
    }


    fun printAmount(amount: Int) {
        println()
        println("${amount}개를 구매했습니다.")
    }

    fun printLottoList(lottoList: List<Lotto>) {
        for (lotto in lottoList) {
            println(lotto.sortedLottoNumbers)
        }
    }

    fun printLottoPrizeStatistics(statistics: LottoPrizeStatistics) {
        println()
        println("당첨 통계")
        println("---")
        statistics.result.entries.forEach {
            print("${it.key.matchedBaseNumberCount}개 일치")
            if(it.key.isBonusNumberMatched) print(", 보너스 볼 일치")
            print(" (${it.key.prizeKoreaMoneyWithComma}원) - ")
            println("${it.value}개")
        }
    }

    fun printReturnRate(returnRate: Double) {
        println("총 수익률은 ${String.format("%.1f", returnRate)}%입니다.")
    }

}private fun String.toValidAmountByPrice(): Int {
    val validInput = this.toIntOrNull()
        ?: throw LottoException("${this}은(는) 유효하지 않은 구입 금액입니다. ${LOTTO_PRICE}원 단위 숫자만 입력 가능합니다. \n구입 금액을 다시 입력해 주세요.")

    if (validInput % LOTTO_PRICE != 0 || validInput <= 0) {
        throw LottoException("${this}은(는) 유효하지 않은 구입 금액입니다. ${LOTTO_PRICE}원 단위 숫자만 입력 가능합니다. \n구입 금액을 다시 입력해 주세요.")
    }
    return validInput / LOTTO_PRICE
}

private fun String.toValidLottoPrizeBaseNumbers(): List<Int> {
    return this.split(LOTTO_NUMBER_DELIMITER).map { part ->
        part.toIntOrNull()
            ?.takeIf { it in LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER }
            ?: throw LottoException("로또 번호는 ${LOTTO_MIN_NUMBER}부터 $LOTTO_MAX_NUMBER 사이의 숫자여야 합니다. \n당첨 번호를 다시 입력해 주세요.")
    }.also { numbers ->
        if (numbers.size != LOTTO_NUMBERS_COUNT) {
            throw LottoException("입력된 숫자의 개수가 ${LOTTO_NUMBERS_COUNT}개가 아닙니다. 정확히 ${LOTTO_NUMBERS_COUNT}개의 숫자를 입력해야 합니다. \n당첨 번호를 다시 입력해 주세요.")
        }
        if (numbers.toSet().size != numbers.size) {
            throw LottoException("로또 번호에 중복된 숫자가 있습니다. 모든 숫자는 고유해야 합니다. \n당첨 번호를 다시 입력해 주세요.")
        }
    }
}

private fun String.toValidLottoPrizeBonusNumbers(lottoPrizeBaseNumber: List<Int>): Int {
    val bonusNumber =  this.toIntOrNull()
        ?.takeIf { it in LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER }
        ?: throw IllegalArgumentException("{$this}은(는) ${LOTTO_MIN_NUMBER}부터 $LOTTO_MAX_NUMBER 사이의 숫자여야 합니다. \n보너스 번호를 다시 입력해 주세요.")

    if (bonusNumber in lottoPrizeBaseNumber) {
        throw IllegalArgumentException("${bonusNumber}가 당첨번호와 중복됩니다. 보너스 번호는 당첨번호와 중복되면 안됩니다. \n보너스 번호를 다시 입력해 주세요.")
    }

    return bonusNumber
}
