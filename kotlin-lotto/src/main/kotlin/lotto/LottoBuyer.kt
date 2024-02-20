package lotto

import lotto.util.*


class LottoBuyer {

    private var _lottoBuyerDTO = LottoBuyerDTO()
    val lottoBuyerDTO get() = _lottoBuyerDTO

    private val inputManager = InputManager()

    fun getPurchaseAmount() {
        try {
            val purchaseAmount = inputManager.getPurchaseAmount()
            _lottoBuyerDTO = _lottoBuyerDTO.copy(
                purchaseAmount = purchaseAmount
            )
        } catch(e: IllegalArgumentException){
            println(PURCHASE_AMOUNT_ERROR)
            getPurchaseAmount()
        }
    }

    fun getWinningNumber() {
        try {
            val winningNumber = inputManager.getWinningNumber()
            _lottoBuyerDTO = _lottoBuyerDTO.copy(
                winningNumber = winningNumber
            )
        } catch(e: IllegalArgumentException){
            println(WINNING_AMOUNT_ERROR)
            getWinningNumber()
        }
    }

    fun getBonusNumber() {
        try {
            val bonusNumber = inputManager.getBonusNumber(_lottoBuyerDTO.winningNumber)
            _lottoBuyerDTO = _lottoBuyerDTO.copy(
                bonusNumber = bonusNumber
            )
        } catch(e: IllegalArgumentException){
            println(BONUS_AMOUNT_ERROR)
            getBonusNumber()
        }
    }

    companion object {
        private const val PURCHASE_AMOUNT_ERROR
            = "$ERROR_PREFIX 로또 구입 가격은 ${LOTTO_AMOUNT}원으로 나누어 떨어져야 합니다."

        private const val WINNING_AMOUNT_ERROR
            = "$ERROR_PREFIX 당첨번호는 중복되지 않는 ${LOTTO_SIZE}개의 숫자($LOTTO_MIN_VALUE~$LOTTO_MAX_VALUE 사이)여야 합니다."

        private const val BONUS_AMOUNT_ERROR
            = "$ERROR_PREFIX 보너스 번호는 $LOTTO_MIN_VALUE~$LOTTO_MAX_VALUE 사이의 숫자이며 당첨번호와 중복되면 안됩니다."
    }
}

data class LottoBuyerDTO(
    val purchaseAmount: Int = 0,
    val winningNumber: List<Int> = emptyList(),
    val bonusNumber: Int = 0
)
