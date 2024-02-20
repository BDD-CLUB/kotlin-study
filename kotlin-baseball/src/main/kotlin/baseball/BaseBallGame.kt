package baseball

class BaseBallGame {
    lateinit var targetNumbers: List<Number>
    private var lastStrikes = 0

    fun initializeGame(targetNumbers: List<Number>) {
        this.targetNumbers = targetNumbers
    }

    fun calculateStrikesAndBalls(guess: List<Number>): Pair<Int, Int> {
        var strikes = 0
        var balls = 0
        guess.forEachIndexed { index, number ->
            if (number == targetNumbers[index]) {
                strikes++
            } else if (number in targetNumbers) {
                balls++
            }
        }
        lastStrikes = strikes
        return Pair(strikes, balls)
    }

    fun isGameOver(): Boolean {
        return lastStrikes == 3
    }
}