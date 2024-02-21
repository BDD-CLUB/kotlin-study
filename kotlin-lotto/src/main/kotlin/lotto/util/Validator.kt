package lotto.util

const val SEPARATOR = ","

fun validatedToInt(string: String): Int {
    try {
        return string.toInt()
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException()
    } catch (e: NullPointerException) {
        throw IllegalArgumentException()
    }
}

fun validatedToList(string: String): List<Int> {
    try {
        return string.split(SEPARATOR).map { it.trim().toInt() }
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException()
    }
}
