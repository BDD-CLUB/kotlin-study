package oncall

import oncall.global.Container

class Application

fun main() {

    Container.componentScan(Application::class)
    val emergencyManager = Container.getInstance(EmergencyManager::class)
    emergencyManager.run()

}
