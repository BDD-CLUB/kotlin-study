package lotto.view

import camp.nextstep.edu.missionutils.Console
import lotto.model.*
import lotto.validation.LottoException

class View {
    fun getPurchasePrice(): PurchasePrice {
        println("구입금액을 입력해 주세요.")
        return tryGetUserInput { Console.readLine().toPurchasePrice() }
    }

    fun printLottos(lottos: Lottos) {
        println()
        println("${lottos.lottoCount}개를 구매했습니다.")
        println(lottos)
    }

    fun getWinningLotto(): WinningLotto {
        println()
        println("당첨 번호를 입력해 주세요.")
        val lotto = tryGetUserInput { Console.readLine().toLotto() }
        println("보너스 번호를 입력해 주세요.")
        val bonusNumber = tryGetUserInput { Console.readLine().toLottoNumber() }
        return WinningLotto(lotto, bonusNumber)
    }

    fun printWinningStatistics(winningStatistics: WinningStatistics) {
        println()
        println("당첨 통계")
        println("---")
        println(winningStatistics)
    }

    private fun <T> tryGetUserInput(inputFunction: () -> T): T {
        while (true) {
            try {
                return inputFunction()
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }
}

fun String.toPurchasePrice(): PurchasePrice {
    try {
        return PurchasePrice(this.toInt())
    } catch (e: NumberFormatException) {
        throw LottoException(e.message ?: "")
    }
}

fun String.toLotto(): Lotto {
    try {
        return Lotto(this.split(",").map(String::trim).map(String::toLottoNumber))
    } catch (e: NumberFormatException) {
        throw LottoException(e.message ?: "")
    }
}

fun String.toLottoNumber(): LottoNumber {
    try {
        return LottoNumber(this.toInt())
    } catch (e: NumberFormatException) {
        throw LottoException(e.message ?: "")
    }
}
