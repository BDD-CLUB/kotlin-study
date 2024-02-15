package baseball

import baseball.common.ErrorMessage
import baseball.common.ErrorMessage.ILLEGAL_INPUT
import baseball.common.GameMessage.*

class BaseballGameManager {
    companion object {
        const val PLAY_AGAIN = 1
        const val END_GAME = 2
    }

    private val baseBallGame = BaseBallGame()

    private val player = HumanPlayer()
    private val computer = ComputerPlayer()

    fun startGame() {
        START_GAME.print()
        baseBallGame.initializeGame(computer.selectNumbers())

        var gameOver = false
        while (!gameOver) {
            playTurn()
            gameOver = checkGameOver()
        }
        endGame()
    }

    fun playTurn() {
        INPUT_PROMPT.print()
        val guess = player.selectNumbers()
        val (strikes, balls) = baseBallGame.calculateStrikesAndBalls(guess)
        RESULT_FORMAT.print(strikes, balls)
    }

    fun checkGameOver(): Boolean {
        return baseBallGame.isGameOver()
    }

    fun endGame() {
        GAME_OVER.print()

        when (askUserToPlayAgain()) {
            PLAY_AGAIN -> startGame()
            else -> {}
        }
    }

    private fun askUserToPlayAgain(): Int {
        PLAY_AGAIN_OR_END.print()
        var choice: Int? = null
        while (choice !in 1..2) {
            choice = readLine()?.toIntOrNull()
            if (choice !in 1..2) {
                throw IllegalArgumentException(ILLEGAL_INPUT.message)
            }
        }
        return choice!!
    }
}