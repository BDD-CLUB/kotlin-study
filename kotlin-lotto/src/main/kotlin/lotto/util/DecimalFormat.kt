package lotto.util

import java.text.DecimalFormat

fun Double.roundTwo(): String {
    return DecimalFormat("#,##0.0").format(this)
}

fun Int.decimalFormat(): String {
    return DecimalFormat("#,##0").format(this)
}
