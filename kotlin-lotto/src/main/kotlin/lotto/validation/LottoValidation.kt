package lotto.validation

const val ERROR_PREFIX = "[ERROR] "

inline fun requireLotto(value: Boolean) {
    requireLotto(value) { "Failed requirement." }
}

inline fun requireLotto(value: Boolean, lazyMessage: () -> Any) {
    if (!value) {
        throw LottoException(lazyMessage().toString())
    }
}

class LottoException(message: String): IllegalArgumentException() {
    override val message = ERROR_PREFIX + message
}
