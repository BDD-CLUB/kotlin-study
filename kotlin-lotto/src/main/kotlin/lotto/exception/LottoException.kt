package lotto.exception

const val LOTTO_EXCEPTION_ERROR_PREFIX = "[ERROR] "
class LottoException(message: String) : IllegalArgumentException() {
    override val message = LOTTO_EXCEPTION_ERROR_PREFIX + message
}