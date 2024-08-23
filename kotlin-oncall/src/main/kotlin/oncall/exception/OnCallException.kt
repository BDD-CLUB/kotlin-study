package oncall.exception

const val ERROR_PREFIX = "[ERROR] "

class OnCallException (message: String) : IllegalArgumentException() {
    override val message = ERROR_PREFIX + message
}

fun <T : Throwable> customRequire(condition: Boolean, exceptionSupplier: () -> T) {
    if (!condition) {
        throw exceptionSupplier()
    }
}
