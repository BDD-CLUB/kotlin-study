package lotto

import lotto.LottoPrize.Companion.findPrize


class LottoGame {
    companion object {
        val lottoStore = LottoStore()
    }

    fun run() {
        val (purchasePrice, lottoCount) = lottoStore.receivePaymentForLotto()
        println("${lottoCount}개를 구매했습니다.")
        val generateLottoTickets = lottoStore.generateLottoTickets(lottoCount)

        printGenerateTickets(generateLottoTickets)

        val winningNumbers = lottoStore.askWinningNumbers()
        val bonusNumber = lottoStore.askBonusNumber()

        val (prizeList, prizeMoney) = checkPrizes(generateLottoTickets, winningNumbers, bonusNumber)
        val returnRate = getReturnRate(purchasePrice, prizeMoney)

        printGameResult(prizeList, returnRate)
    }

    private fun getReturnRate(purchasePrice: Int, prizeMoney: Long): Double =
        (prizeMoney.toDouble() / purchasePrice.toDouble() * 100.0)

    private fun printGameResult(prizeList: List<LottoPrize>, returnRate: Double) {
        println("당첨 통계")
        println("---")


        val result = prizeList.map { it.name }.groupingBy { it }.eachCount()

        listOf(
            LottoPrize.THREE_MATCH,
            LottoPrize.FOUR_MATCH,
            LottoPrize.FIVE_MATCH,
            LottoPrize.FIVE_PLUS_BONUS_MATCH,
            LottoPrize.SIX_MATCH,
        ).map { o ->
            println("${o.description} - ${result.get(o.name) ?: 0}개")
        }

        println("총 수익률은 ${returnRate}%입니다.")
    }

    private fun printGenerateTickets(generateLottoTickets: List<Lotto>) {
        for (generateLottoTicket in generateLottoTickets) {
            println(generateLottoTicket.numbers)
        }
        println()
    }
}

fun checkPrizes(
    generatedLottoTickets: List<Lotto>,
    winningNumbers: List<Int>,
    bonusNumber: Int
): Pair<List<LottoPrize>, Long> {
    var prizeMoney: Long = 0
    val prizeList = generatedLottoTickets.map { lotto ->
        val matchCount = lotto.numbers.count { it in winningNumbers }
        val isBonusMatched = bonusNumber in lotto.numbers
        findPrize(matchCount, isBonusMatched)
    }

    prizeList.forEach { o ->
        prizeMoney += o.prize
    }

    return Pair(prizeList, prizeMoney)
}


