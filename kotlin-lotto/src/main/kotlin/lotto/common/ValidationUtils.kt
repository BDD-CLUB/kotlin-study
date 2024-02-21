package lotto.common

fun require(condition: Boolean, createException: () -> Exception) {
    if (!condition) {
        val exception = createException()
        println(exception.message)
    }
}
