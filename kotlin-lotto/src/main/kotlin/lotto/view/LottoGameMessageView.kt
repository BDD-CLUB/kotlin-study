package lotto.view

import lotto.model.Lotto
import lotto.global.Component
import lotto.model.LottoWinningRank

@Component
class LottoGameMessageView {

    fun announcePurchaseMessage() = println("구입금액을 입력해 주세요.")

    fun announcePurchasedLottos(purchasedLottos: List<Lotto>) {
        println("\n${purchasedLottos.size}개를 구매했습니다.")
        purchasedLottos.forEach{ println(it) }
    }

    fun announceEnterWinningNumbers() = println("\n당첨 번호를 입력해 주세요.")

    fun announceEnterBonusNumbers() = println("\n보너스 번호를 입력해 주세요.")

    fun announceLottoResult(lottoResultDto: List<LottoWinningRank?>, purchaseAmount: Int) =
            println("""
                
                당첨 통계
                ---
                3개 일치 (5,000원) - ${lottoResultDto.count { it == LottoWinningRank.FIFTH_PLACE}}개
                4개 일치 (50,000원) - ${lottoResultDto.count { it == LottoWinningRank.FOURTH_PLACE }}개
                5개 일치 (1,500,000원) - ${lottoResultDto.count { it == LottoWinningRank.THIRD_PLACE }}개
                5개 일치, 보너스 볼 일치 (30,000,000원) - ${lottoResultDto.count { it == LottoWinningRank.SECOND_PLACE }}개
                6개 일치 (2,000,000,000원) - ${lottoResultDto.count { it == LottoWinningRank.FIRST_PLACE }}개
                총 수익률은 ${"%.1f".format(calculateTotalRateOfReturn(lottoResultDto, purchaseAmount))}%입니다.
            """.trimIndent())

    private fun calculateTotalRateOfReturn(lottoResultDto: List<LottoWinningRank?>, purchaseAmount: Int): Double =
        lottoResultDto.sumOf { it?.lottoPrice ?: 0L }.toDouble() / purchaseAmount * 100

}