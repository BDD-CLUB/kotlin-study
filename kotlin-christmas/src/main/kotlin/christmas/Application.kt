package christmas

import christmas.global.Container

class Application

fun main() {
    Container.componentScan(Application::class)
    val christmasGameManager = Container.getInstance(ChristmasGameManager::class)
    christmasGameManager.run()
}
