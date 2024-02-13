package KtInActionUtil

const val UNIT_LINE_SEPARATOR = "\n"

fun <T> joinToString(collection: Collection<T>,
                             separator: String,
                             prefix: String,
                             postfix: String): String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}

fun String.lastChar(): Char = this.get(length - 1)

var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value: Char) {
        this.setCharAt(length - 1, value)
    }

fun <T> Collection<T>.joinToString2(
        separator: String = ", ",
        prefix: String = "",
        postfix: String = ""
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

fun Collection<String>.joinToString3(
        separator: String = ", ",
        prefix: String = "",
        postfix: String = ""
) = joinToString2(separator, prefix, postfix)