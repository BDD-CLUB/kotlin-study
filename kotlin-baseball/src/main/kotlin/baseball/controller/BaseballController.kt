package baseball.controller

import baseball.model.NUMBER_LENGTH
import baseball.model.Numbers
import baseball.model.StrikeBall
import baseball.model.UserContinueGame
import baseball.view.View

class BaseballController(
    private val view: View,
) {
    fun start() {
        view.printStartMessage()
        do {
            val computerNumbers = Numbers.generateRandom()
            do {
                val userNumbers = view.getUserNumbers()
                val strikeBall = userNumbers.compare(computerNumbers)
                view.printStrikeBall(strikeBall)
            } while (strikeBall.isGameContinue())
            view.printEndMessage()
        } while (view.getUserContinueGame() == UserContinueGame.RESTART)
    }

    private fun StrikeBall.isGameContinue() = this.strike != NUMBER_LENGTH
}
