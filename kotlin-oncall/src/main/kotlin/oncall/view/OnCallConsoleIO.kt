package oncall.view

import camp.nextstep.edu.missionutils.Console
import oncall.controller.INPUT_SEPARATOR
import oncall.model.OnCallSchedule
import oncall.model.*
import oncall.exception.OnCallException

class OnCallConsoleIO {
    fun getOnCallMonthAndDayOfTheWeek(): Pair<Month, DayOfWeek> {
        return getValidInput {
            print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ")
            Console.readLine().toValidMonthAndDayOfTheWeek()
        }
    }

    fun getOnCallEmployee(): OnCallEmployee {
        return getValidInput {
            print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
            val rawWeekdayOnCallEmployeeNickNames = Console.readLine()
            print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
            val rawHolidayOnCallEmployeeNickNames = Console.readLine()
            OnCallEmployee(
                weekdayEmployees = rawWeekdayOnCallEmployeeNickNames.toEmployees(),
                holidayEmployees = rawHolidayOnCallEmployeeNickNames.toEmployees()
            )
        }
    }

    fun printOnCallSchedule(calendar: Calendar, onCallSchedule: OnCallSchedule) {
        calendar.dates.forEach {
            println("${onCallSchedule.month.num}월 " +
                    "${it.day}일 " +
                    "${it.dayOfWeek.name}${if (it.isPublicHoliday) "(휴일)" else ""} " +
                    onCallSchedule.scheduledEmployees[it.day - 1].nickName
            )
        }
    }
}

private fun <T> getValidInput(inputFunction: () -> T): T {
    while (true) {
        try {
            return inputFunction()
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }
}

private fun String.toValidMonthAndDayOfTheWeek(): Pair<Month, DayOfWeek> {
    return this.split(INPUT_SEPARATOR).let {
        if (it.size != 2) throw OnCallException("유효하지 않은 입력 값입니다. 다시 입력해 주세요.")
        Pair(it.first().toMonth(), it.last().toDayOfWeek())
    }
}

private fun String.toEmployees(): List<Employee> {
    return this.split(INPUT_SEPARATOR).map{ Employee(it) }
}

private fun String.toMonth(): Month {
    return Month.entries.find{ it.num == this.toIntOrNull() }
        ?: throw OnCallException("유효하지 않은 입력 값입니다. 다시 입력해 주세요.")
}

private fun String.toDayOfWeek(): DayOfWeek {
    return DayOfWeek.entries.find{ it.name == this }
        ?: throw OnCallException("유효하지 않은 입력 값입니다. 다시 입력해 주세요.")
}
