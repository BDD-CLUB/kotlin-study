package lotto.view

import camp.nextstep.edu.missionutils.Console
import lotto.model.Lottos
import lotto.model.WinningLotto
import lotto.model.WinningLotto.BONUS_MATCH
import lotto.model.WinningLotto.entries
import lotto.util.validatedToInt
import lotto.util.validatedToList
import java.text.NumberFormat.getNumberInstance
import java.util.Locale.US

class ConsoleLottoView : LottoView {
    override fun inputPurchaseAmount(): Int {
        println("구입금액을 입력해 주세요.")
        return validatedToInt(Console.readLine())
    }

    override fun inputWinningNumbers(): List<Int> {
        println("당첨 번호를 입력해 주세요.")
        return validatedToList(Console.readLine())
    }

    override fun inputBonusNumber(): Int {
        println("보너스 번호를 입력해 주세요.")
        return validatedToInt(Console.readLine())
    }

    override fun printPurchasedLotto(lottos: Lottos) {
        println("${lottos.getLottoSize()}개를 구매했습니다.")
        lottos.getLottos.forEach { println(it) }
    }

    override fun printWinningStatistics(
        winningLottos: List<WinningLotto>,
        totalReturn: Double
    ) {
        println("당첨 통계")
        println("---")
        val lottoCountMap = winningLottos.groupingBy { it }.eachCount()
        entries.forEach { winningLotto ->
            val count = lottoCountMap[winningLotto] ?: 0
            if (winningLotto == BONUS_MATCH) {
                println("${winningLotto.matchCount}개 일치, 보너스 볼 일치 (${formatAmount(winningLotto.amount)}원) - ${count}개")
                return@forEach
            }
            println("${winningLotto.matchCount}개 일치 (${formatAmount(winningLotto.amount)}원) - ${count}개")
        }
        println("총 수익률은 ${"%.1f".format(totalReturn)}%입니다.")
    }

    private fun formatAmount(number: Int): String {
        return getNumberInstance(US).format(number)
    }
}
