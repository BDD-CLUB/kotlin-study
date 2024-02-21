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

        for (generateLottoTicket in generateLottoTickets) {
            println(generateLottoTicket.numbers)
        }

        println()

        val winningNumbers = lottoStore.askWinningNumbers()
        val bonusNumber = lottoStore.askBonusNumber()

        val (prizeList, prizeMoney) = checkPrizes(generateLottoTickets, winningNumbers, bonusNumber)
        val returnRate = getReturnRate(purchasePrice, prizeMoney)

        printGameResult(prizeList, returnRate)
    }

    fun getReturnRate(purchasePrice: Int, prizeMoney: Long): Double =
        (prizeMoney.toDouble() / purchasePrice.toDouble() * 100.0)

    fun printGameResult(prizeList: List<LottoPrize>, returnRate: Double) {
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


enum class LottoPrize(
    val matchCount: Int,
    val prize: Int,
    val description: String,
    val isBonusMatched: Boolean = false
) {
    THREE_MATCH(3, 5_000, "3개 일치 (5,000원)"),
    FOUR_MATCH(4, 50_000, "4개 일치 (50,000원)"),
    FIVE_MATCH(5, 1_500_000, "5개 일치 (1,500,000원)"),
    FIVE_PLUS_BONUS_MATCH(5, 30_000_000, "5개 일치, 보너스 볼 일치 (30,000,000원)", true),
    SIX_MATCH(6, 2_000_000_000, "6개 일치 (2,000,000,000원)"),
    LOSE(0, 0, "낙첨");

    companion object {
        fun findPrize(matchCount: Int, isBonusMatched: Boolean = false): LottoPrize {
            return entries.find { it.matchCount == matchCount && it.isBonusMatched == isBonusMatched } ?: LOSE
        }
    }
}


