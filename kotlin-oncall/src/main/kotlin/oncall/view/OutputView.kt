package oncall.view

import oncall.domain.EmergencySchedule
import oncall.global.Component

@Component
class OutputView {

    fun printEmergencyAssignment() = print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ")

    fun printWeekdayEmergencyAssignment() = print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")

    fun printWeekendEmergencyAssignment() = print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")

    fun printScheduleResult(schedule: List<EmergencySchedule>) {
        schedule.forEach {schedule ->
            val isHoliday = if (schedule.isHoliday) "(휴일)" else ""
            println("${schedule.month.month}월 ${schedule.dayOfMonth}일 ${schedule.day.day}$isHoliday ${schedule.name}")
        }
    }

}