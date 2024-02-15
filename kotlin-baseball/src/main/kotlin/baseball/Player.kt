package baseball

import baseball.module.ComputerGenerator
import baseball.module.BaseballInputModule

interface Player {
    fun selectNumbers(): List<Number>
}

class ComputerPlayer : Player {
    override fun selectNumbers(): List<Int> {
        return ComputerGenerator().generateThreeRandomNumbers()
    }
}

class HumanPlayer : Player {
    override fun selectNumbers(): List<Int> {
        return BaseballInputModule().readNumbers()
    }

}
