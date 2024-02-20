package baseball.module

import baseball.common.ErrorMessage.ILLEGAL_INPUT

class BaseballInputModule {
    fun readNumbers(): List<Number> {
        return sequenceOf(readLine())
            .map { it ?: throw IllegalStateException(ILLEGAL_INPUT.message) }
            .filter { it.length == 3 && it.all { it.isDigit() } }
            .map { it.map { char -> char.toString().toInt() } }
            .firstOrNull()
            ?: throw IllegalArgumentException(ILLEGAL_INPUT.message)
    }
}