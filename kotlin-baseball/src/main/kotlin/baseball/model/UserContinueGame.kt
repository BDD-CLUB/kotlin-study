package baseball.model

enum class UserContinueGame(val value: Int) {
    RESTART(1),
    END(2),
    ;

    companion object {
        fun of(input: String): UserContinueGame {
            return entries.find { it.value == input.toInt() }
                ?: throw IllegalArgumentException("Input must in $entries. input: $input")
        }
    }
}
