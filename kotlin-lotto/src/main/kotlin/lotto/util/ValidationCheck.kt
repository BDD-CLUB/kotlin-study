package lotto.util

fun String.toIntValue() = this.toIntOrNull() ?: throw IllegalArgumentException()

fun Int.checkDividable() = require(this % LOTTO_AMOUNT == 0)

fun Int.checkInRange() = require(this in LOTTO_MIN_VALUE..LOTTO_MAX_VALUE)

fun Int.checkDuplicationWithWinningNumber(winningNumber: List<Int>) = require(this !in winningNumber)

fun List<String>.checkLottoSize() = require(this.size == LOTTO_SIZE)

fun List<String>.checkDuplication() = require(this.size == this.distinct().size)
