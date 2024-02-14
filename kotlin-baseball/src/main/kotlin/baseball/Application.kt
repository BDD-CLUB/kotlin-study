package baseball

import baseball.controller.BaseballController
import baseball.view.View

fun main() {
    val baseballController = BaseballController(View())
    baseballController.start()
}
