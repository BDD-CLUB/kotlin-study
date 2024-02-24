package christmas.model

import christmas.validation.requireChristmas
import java.text.DecimalFormat

@JvmInline
value class Price(val value: Int) {
    init {
        requireChristmas(value >= 0)
    }

    operator fun times(other: Int) = Price(this.value * other)

    operator fun plus(other: Price) = Price(this.value + other.value)

    operator fun minus(other: Price) = Price(this.value - other.value)

    operator fun compareTo(other: Price) = this.value.compareTo(other.value)

    operator fun compareTo(other: Int) = this.value.compareTo(other)

    fun toMinusString() = "${DecimalFormat("#,###").format(-value)}원"

    override fun toString() = "${DecimalFormat("#,###").format(value)}원"
}

fun Int.toPrice() = Price(this)
