package oncall.view

import camp.nextstep.edu.missionutils.Console
import oncall.entity.Order
import oncall.exception.printlnOncallException
import oncall.model.OncallDate
import java.time.DayOfWeek
import java.time.Month

object InputView {
    fun getOncallDate() = tryGetUserInput {
        print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ")
        Console.readLine().toOncallDate()
    }

    fun getOrder() = tryGetUserInput {
        print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
        val weekdayOrder = Console.readLine().toOrder()
        print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요>")
        val holidayOrder = Console.readLine().toOrder()
        Pair(weekdayOrder, holidayOrder)
    }
}

private fun String.toOncallDate(): OncallDate {
    val (monthString, startDayOfWeekString) = this.split(",")
    return OncallDate(Month.of(monthString.toInt()), startDayOfWeekString.toDayOfWeek())
}

private fun String.toDayOfWeek() = when (this) {
    "월" -> DayOfWeek.MONDAY
    "화" -> DayOfWeek.TUESDAY
    "수" -> DayOfWeek.WEDNESDAY
    "목" -> DayOfWeek.THURSDAY
    "금" -> DayOfWeek.FRIDAY
    "토" -> DayOfWeek.SATURDAY
    "일" -> DayOfWeek.SUNDAY
    else -> throw IllegalArgumentException()
}

private fun String.toOrder() = Order(this.split(","))

private fun <T> tryGetUserInput(inputFunction: () -> T): T {
    while (true) {
        try {
            return inputFunction()
        } catch (ignore: Exception) {
            printlnOncallException("유효하지 않은 입력 값입니다. 다시 입력해 주세요.")
        }
    }
}
