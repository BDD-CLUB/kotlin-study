package baseball

import baseball.module.ComputerGenerator
import baseball.module.BaseballInputModule

interface Player {
    fun selectNumbers(): List<Number>
}

class ComputerPlayer : Player {
    override fun selectNumbers(): List<Number> {
        return ComputerGenerator().generateThreeRandomNumbers()
    }
}

class HumanPlayer : Player {
    override fun selectNumbers(): List<Number> {
        return BaseballInputModule().readNumbers()
    }

}
