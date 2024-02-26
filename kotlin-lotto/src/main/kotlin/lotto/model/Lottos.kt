package lotto.model

class Lottos(private val lottos: List<Lotto>) {

    companion object {
        const val BONUS_MATCH_COUNT = 5
    }

    val getLottos = lottos

    fun getLottoSize(): Int {
        return lottos.size
    }

    fun getWinningLottos(lotto: Lotto, bonusNumber: LottoNumber): List<WinningLotto> {
        return lottos.map {
            val matchCount = it.countMatch(lotto)
            if (matchCount == BONUS_MATCH_COUNT) {
                if (it.contains(bonusNumber)) {
                    WinningLotto.from(matchCount, isBonus = true)
                }
            }
            WinningLotto.from(matchCount)
        }
    }
}
