package christmas.validation

const val ERROR_PREFIX = "[ERROR] "

fun requireChristmas(value: Boolean) {
    requireChristmas(value) { "Failed requirement." }
}

inline fun requireChristmas(value: Boolean, lazyMessage: () -> Any) {
    if (!value) {
        throw ChristmasException(lazyMessage().toString())
    }
}

class ChristmasException(message: String) : IllegalArgumentException() {
    override val message = ERROR_PREFIX + message
}
