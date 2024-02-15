package baseball

import step1.*
import step2.api.BaseballController
import step2.application.BaseballService
import step2.global.BaseballApplication
import step2.view.BaseballView

fun main() {
    BaseballApplication(BaseballView(), BaseballController(BaseballService())).run()
}

