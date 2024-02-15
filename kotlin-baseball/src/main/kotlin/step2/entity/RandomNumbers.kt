package step2.entity

object RandomNumbers {
    private var randomNumber: String? = null

    fun changeRandomNumbers(number: String): String {
        this.randomNumber = number
        return this.randomNumber!!
    }

    fun countStrike(number: String): Int = number.indices.count { i -> number[i] == randomNumber!![i] }

    fun countBall(number: String): Int = number.indices.count { i -> number[i] != randomNumber!![i] && number[i] in randomNumber!! }
}
