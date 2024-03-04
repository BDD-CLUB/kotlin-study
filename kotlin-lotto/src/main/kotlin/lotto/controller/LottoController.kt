package lotto.controller

import camp.nextstep.edu.missionutils.Console
import camp.nextstep.edu.missionutils.Randoms
import lotto.global.Component
import lotto.model.Lotto
import lotto.model.LottoBuyer
import lotto.model.LottoWinningRank
import lotto.view.LottoGameMessageView

@Component
class LottoController(
        private val lottoGameMessageView: LottoGameMessageView
) {

    fun purchaseLotto(): Int {
        lottoGameMessageView.announcePurchaseMessage()
        while (true) {
            try {
                return (Console.readLine()
                        ?.toIntOrNull()
                        ?: IllegalArgumentException("[ERROR] 올바른 구입금액을 입력해 주세요.")) as Int
            } catch (e: IllegalArgumentException) {
                println(e.message)
            } catch (e: ClassCastException) {
                println("[ERROR] 올바른 구매금액을 입력해주세요.")
            }
        }
    }

    fun enterWinningNumbers(): List<Int> {
        lottoGameMessageView.announceEnterWinningNumbers()
        return (Console.readLine()
                ?.split(",")
                ?.map { it.toInt() }
                ?: throw IllegalArgumentException("[ERROR] 올바른 당첨 번호를 입력해 주세요."))

    }

    fun enterBonusNumber(): Int {
        lottoGameMessageView.announceEnterBonusNumbers()
        return Console.readLine()
                ?.toIntOrNull()
                ?: throw IllegalArgumentException("[ERROR] 올바른 보너스 번호를 입력해 주세요.")
    }

    fun announceResult(lottoResult: List<LottoWinningRank?>, purchaseAmount: Int) {
        lottoGameMessageView.announceLottoResult(lottoResult, purchaseAmount)
    }

    fun announceLottoNumbers(purchaseAmount: Int): List<Lotto> {
        val randomLottos = List(purchaseAmount / 1000) { Lotto(generateRandomLottoNumbers()) }
        lottoGameMessageView.announcePurchasedLottos(randomLottos)
        return randomLottos
    }

    private fun generateRandomLottoNumbers(): List<Int> =
            Randoms.pickUniqueNumbersInRange(Lotto.LOTTO_START_NUMBER, Lotto.LOTTO_END_NUMBER, Lotto.LOTTO_TOTAL_COUNT)

}