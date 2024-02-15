package baseball.model

interface GameNumbers {

    fun get(): List<Int>
    fun generate(): List<Int>
    fun setNumbers()
}
