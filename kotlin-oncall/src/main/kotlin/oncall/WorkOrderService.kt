package oncall

import oncall.data.Holiday
import oncall.data.OncallInformationDTO
import oncall.data.Week.Companion.isHoliday
import oncall.data.getEndDateOfMonth

class WorkOrderService(
    private val oncallInformationDTO: OncallInformationDTO
) {
    private val month = oncallInformationDTO.month
    private val endDate = getEndDateOfMonth(month)

    private val size = oncallInformationDTO.holidayWorkerList.size
    private val holidayWorker = MutableList(endDate) { i -> oncallInformationDTO.holidayWorkerList[i%size] }
    private val weekdayWorker = MutableList(endDate) { i -> oncallInformationDTO.weekdayWorkerList[i%size] }

    fun getWorkerList(): List<String> {
        val workerList = mutableListOf<String>()
        for (date in 1..endDate) {
            val workers = getWorkerList(date)
            val workerIndex = workers.indexOfFirst { workerList.isEmpty() || it != workerList.last() }
            workerList.add(workers[workerIndex])
            workers.removeAt(workerIndex)
        }
        return workerList.toList()
    }

    private fun getWorkerList(date: Int) = if (isHoliday(date)) holidayWorker else weekdayWorker

    private fun isHoliday(date: Int) = Holiday.isHoliday(month, date) || oncallInformationDTO.startWeek.isHoliday(date)
}
