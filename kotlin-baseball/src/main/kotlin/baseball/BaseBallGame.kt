package baseball

class BaseBallGame {
    lateinit var targetNumbers: List<Int>
    private var lastStrikes = 0

    fun initializeGame(targetNumbers: List<Int>) {
        this.targetNumbers = targetNumbers
    }

    fun calculateStrikesAndBalls(guess: List<Int>): Pair<Int, Int> {
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