package oncall.util

import camp.nextstep.edu.missionutils.Console
import oncall.data.OncallInformationDTO

class InputManager(
    private val dataConverter: DataConverter = DataConverter(),
    private val outputManager: OutputManager = OutputManager()
) {

    fun getInput(): OncallInformationDTO {
        val (month, startWeek) = getDate()
        val weekdayWorker = getWeekdayWorker()
        val holidayWorker = getHolidayWorker(weekdayWorker)

        return OncallInformationDTO(month, startWeek, weekdayWorker, holidayWorker)
    }

    private fun getDate(): Pair<Int, String> {
        return getUserInput {
            outputManager.printGetDateMessage()
            val input = Console.readLine() ?: ""
            dataConverter.convertDate(input)
        }
    }

    private fun getWeekdayWorker(): List<String> {
        return getUserInput {
            outputManager.printGetWeekdayWorkerMessage()
            val input = Console.readLine() ?: ""
            dataConverter.convertWeekdayWorker(input)
        }
    }

    private fun getHolidayWorker(weekdayWorker: List<String>): List<String> {
        return getUserInput {
            outputManager.printGetHolidayWorkerMessage()
            val input = Console.readLine() ?: ""
            dataConverter.convertHolidayWorker(input, weekdayWorker)
        }
    }

    private fun <T> getUserInput(inputFunction: () -> T): T {
        while (true) {
            try {
                return inputFunction()
            } catch (_: IllegalArgumentException) {
                outputManager.printExceptionMessage()
            }
        }
    }
}
