package oncall.view

class InputView {


}

fun getIntOrThrow(consoleRead: () -> String): Int {
    while (true) {
        try {
            return consoleRead().toIntOrNull()
                    ?: throw IllegalArgumentException("[ERROR] 제대로 입력해 주세용.")
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }
}
