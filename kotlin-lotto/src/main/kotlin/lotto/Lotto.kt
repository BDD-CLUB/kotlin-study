package lotto

class Lotto(val numbers: List<Number>) {
    init {
        require(numbers.size == 6)
    }
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

