package lotto._enums

import lotto.util.decimalFormat

enum class LottoResult(
    private val num: Int,
    private val isBonusCorrect: Boolean?,
    val price: Int,
) {
    THREE_CORRECT(3, null, 5000),
    FOUR_CORRECT(4, null, 50000),
    FIVE_CORRECT(5, false, 1500000),
    FIVE_BONUS_CORRECT(5, true, 30000000),
    SIX_CORRECT(6, null, 2000000000),
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
            return LottoResult.values().firstOrNull {
                it.num == value && (it.isBonusCorrect == isBonusCorrect || it.isBonusCorrect == null)
            }
        }
    }

}
