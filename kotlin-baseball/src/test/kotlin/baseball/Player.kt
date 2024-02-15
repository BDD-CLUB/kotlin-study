package baseball

import baseball.module.NumberGenerator

interface Player {
    fun selectNumbers(): List<Number>
}

class ComputerPlayer(private val numberGenerator: NumberGenerator) : Player {
    override fun selectNumbers(): List<Number> {
        return numberGenerator.generateThreeRandomNumbers()
    }

}

class HumanPlayer() : Player {
    override fun selectNumbers(): List<Number> {
        TODO("Not yet implemented")
    }

}
