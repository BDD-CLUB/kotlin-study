package oncall

import oncall.view.InputView
import oncall.view.OutputView

fun main() {

    EmergencyManager(InputView(), OutputView()).run()

}
