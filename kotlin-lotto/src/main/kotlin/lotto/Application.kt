package lotto

import lotto.global.BService
import lotto.global.ContainerV2
import lotto.global.start

fun main() {

    start(Lotto::class)
    val instance = ContainerV2.getInstance(Lotto::class)
    instance.printHello()

}
