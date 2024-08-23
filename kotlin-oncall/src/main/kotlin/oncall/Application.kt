package oncall

import oncall.controller.OnCallRotationAssigner

fun main() {
    val onCallRotationAssigner = OnCallRotationAssigner()
    onCallRotationAssigner.run()
}
