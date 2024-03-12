package oncall.util

import oncall.data.OncallInformationDTO
import oncall.data.Week.Companion.getWeek
import oncall.data.getEndDateOfMonth

class OutputManager {

    fun printExceptionMessage() = println("$ERROR_PREFIX 유효하지 않은 입력 값입니다. 다시 입력해 주세요.")

    fun printGetDateMessage() = print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ")

    fun printGetWeekdayWorkerMessage() = print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")

    fun printGetHolidayWorkerMessage() = print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")

    fun printWorkerOrder(oncallInformationDTO: OncallInformationDTO, workerList: List<String>) {
        val month = oncallInformationDTO.month
        val endDate = getEndDateOfMonth(month)
        for (date in 1..endDate) {
            val week = oncallInformationDTO.startWeek.getWeek(month, date)
            print("${month}월 ${date}일 $week ${workerList[date-1]}\n")
        }
    }

    companion object {
        private const val ERROR_PREFIX = "[ERROR]"
    }
}
