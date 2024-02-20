package lotto.model

class LottoBuyer(
        private val purchasedLottos : List<Lotto>,
        private val winningNumbers: List<Int>,
        private val bonusNumber: Int
) {

    fun compareLottoNumbers(): List<LottoWinningRank?> =
            purchasedLottos.map { lotto ->
                LottoWinningRank.entries.firstOrNull { rank ->
                    rank.isRankMatch(lotto.count(winningNumbers), lotto.count(bonusNumber))
                }
            }

}
