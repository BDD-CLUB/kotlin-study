package oncall.util

import oncall.data.Week

class ValidationChecker {

    fun checkDate(size: Int, month: Int, week: String) {
        require(size == 2)
        require(month in MONTH_RANGE)
        require(Week.contains(week))
    }

    fun checkWeekdayWorker(worker: List<String>) {
        checkWorker(worker)
    }

    fun checkHolidayWorker(weekdayWorker: List<String>, holidayWorker: List<String>) {
        checkWorker(holidayWorker)
        require(weekdayWorker.sorted() == holidayWorker.sorted())
    }

    private fun checkWorker(worker: List<String>) {
        require(worker.distinct().size == worker.size)
        require(worker.all { it.length in NICKNAME_RANGE })
        require(worker.size in WORKER_RANGE)
    }

    companion object {
        private val MONTH_RANGE = 1..12
        private val NICKNAME_RANGE = 1..5
        private val WORKER_RANGE = 5..35
    }
}
