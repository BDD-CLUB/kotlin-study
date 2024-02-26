package lotto.model

enum class WinningLotto(
    val matchCount: Int,
    val amount: Int
) {
    ZERO_MATCH(0, 0),
    ONE_MATCH(1, 0),
    TWO_MATCH(2, 0),
    THREE_MATCH(3, 5_000),
    FOUR_MATCH(4, 50_000),
    FIVE_MATCH(5, 1_500_000),
    BONUS_MATCH(5, 30_000_000),
    SIX_MATCH(6, 2_000_000_000),
    ;

    companion object {
        fun from(
            matchCount: Int,
            isBonus: Boolean = false
        ): WinningLotto {
            if (isBonus) {
                return BONUS_MATCH
            }
            return entries.firstOrNull { it.matchCount == matchCount }
                ?: throw IllegalArgumentException("${matchCount}는 유효하지 않은 입력입니다.")
        }
    }
}
