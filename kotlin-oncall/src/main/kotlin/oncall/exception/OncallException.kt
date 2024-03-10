package oncall.exception

const val ERROR_PREFIX = "[ERROR] "

inline fun requireOncall(value: Boolean, lazyMessage: () -> Any) {
    if (!value) {
        throw OncallException(lazyMessage().toString())
    }
}

class OncallException(message: String) : IllegalArgumentException() {
    override val message = ERROR_PREFIX + message
}

fun printlnOncallException(message: String) = println(ERROR_PREFIX + message)
