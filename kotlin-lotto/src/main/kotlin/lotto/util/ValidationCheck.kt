package lotto.util

import lotto._const.LOTTO_AMOUNT
import lotto._const.LOTTO_MAX_VALUE
import lotto._const.LOTTO_MIN_VALUE
import lotto._const.LOTTO_SIZE

fun String.toIntValue() = this.toIntOrNull() ?: throw IllegalArgumentException()

fun Int.checkDividable() = require(this % LOTTO_AMOUNT == 0)

fun Int.checkPositive() = require(this > 0)

fun Int.checkInRange() = require(this in LOTTO_MIN_VALUE..LOTTO_MAX_VALUE)

fun Int.checkDuplicationWithWinningNumber(winningNumber: List<Int>) = require(this !in winningNumber)

fun List<Any>.checkLottoSize() = require(this.size == LOTTO_SIZE)

fun List<Any>.checkDuplication() = require(this.size == this.distinct().size)

fun List<Int>.checkInRange() = this.forEach { it.checkInRange() }
