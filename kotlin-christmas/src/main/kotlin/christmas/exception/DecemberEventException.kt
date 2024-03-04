package christmas.exception

const val ERROR_PREFIX = "[ERROR] "

class DecemberEventException (message: String) : IllegalArgumentException() {
    override val message = ERROR_PREFIX + message
}