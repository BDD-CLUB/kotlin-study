package lotto.util

import lotto._enums.LottoResult

fun printGetPurchaseAmountMessage() = println("구입금액을 입력해 주세요.")

fun printGetWinningNumberMessage() = println("\n당첨 번호를 입력해 주세요.")

fun printGetBonusNumberMessage() = println("\n보너스 번호를 입력해 주세요.")

fun printProfitRateMessage(profitRate: String) = println("총 수익률은 $profitRate%입니다.")

fun printLottoSizeMessage(size: Int) = println("\n${size}개를 구매했습니다.")

fun printResultMessage() = println("\n당첨 통계\n---")

fun printLottoResultMessage(lottoResult: List<LottoResult>) {
    printResultMessage()
    for (result in LottoResult.entries) {
        val count = lottoResult.count { it == result }
        val message = result.getMessage(count)
        println(message)
    }
}

fun printProfitRateMessage(profitRate: Double) = printProfitRateMessage(profitRate.roundTwo())
