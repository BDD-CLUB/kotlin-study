package baseball

import baseball.controller.GameController
import baseball.model.RandomNumbers

fun main() {
    val gameController = GameController(RandomNumbers())
    gameController.run()
}
