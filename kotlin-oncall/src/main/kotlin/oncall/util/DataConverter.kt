package oncall.util

import oncall.data.Week


class DataConverter(
    private val validationChecker: ValidationChecker = ValidationChecker()
) {

    fun convertDate(input: String): Pair<Int, Week> {
        val split = input.split(',')
        val month = split[0].toIntWithoutNull()
        val week = Week.get(split[1])

        validationChecker.checkDate(split.size, month, week)
        return Pair(month, week!!)
    }

    fun convertWeekdayWorker(input: String): List<String> {
        val weekdayWorker = input.split(',')
        validationChecker.checkWeekdayWorker(weekdayWorker)
        return weekdayWorker
    }

    fun convertHolidayWorker(input: String, weekdayWorker: List<String>): List<String> {
        val holidayWorker = input.split(',')
        validationChecker.checkHolidayWorker(weekdayWorker, holidayWorker)
        return holidayWorker
    }

    private fun String.toIntWithoutNull(): Int {
        return this.toIntOrNull() ?: throw IllegalArgumentException()
    }
}
