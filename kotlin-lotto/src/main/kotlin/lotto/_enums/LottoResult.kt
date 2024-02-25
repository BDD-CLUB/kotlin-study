package lotto._enums

import lotto.util.decimalFormat

enum class LottoResult(
    private val num: Int,
    private val isBonusCorrect: Boolean?,
    val price: Int,
) {
    THREE_CORRECT(3, null, 5_000),
    FOUR_CORRECT(4, null, 50_000),
    FIVE_CORRECT(5, false, 1_500_000),
    FIVE_BONUS_CORRECT(5, true, 30_000_000),
    SIX_CORRECT(6, null, 2_000_000_000),
    ;

    fun getMessage(count: Int) : String {
        var message = "${this.num}개 일치"
        if (this.isBonusCorrect == true) {
            message += ", 보너스 볼 일치"
        }
        message += " (${this.price.decimalFormat()}원) - ${count}개"
        return message
    }

    companion object {
        fun get(value: Int, isBonusCorrect: Boolean): LottoResult? {
            return entries.firstOrNull {
                it.num == value && (it.isBonusCorrect == isBonusCorrect || it.isBonusCorrect == null)
            }
        }
    }

}
