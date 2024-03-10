package oncall.service

import oncall.entity.Order
import oncall.exception.OncallException
import oncall.model.DayType
import oncall.model.OncallDate
import java.time.DayOfWeek

object OncallService {
    fun getOncallTable(oncallDate: OncallDate, weekdayOrder: Order, holidayOrder: Order): String {
        var previousMember: String? = null

        return buildString {
            for (day in 1..oncallDate.monthDay) {
                val (dayOfWeek, dayType) = oncallDate[day] ?: throw OncallException("존재하지 않는 날짜입니다: ${day}일")
                val nextMember = when (dayType) {
                    DayType.WEEKDAY -> weekdayOrder.getNextTurnMember(previousMember)
                    DayType.WEEKDAY_HOLIDAY, DayType.HOLIDAY -> holidayOrder.getNextTurnMember(previousMember)
                }
                previousMember = nextMember
                append("${oncallDate.month.value}월 ${day}일 ${dayOfWeek.toKorean()}${if (dayType == DayType.WEEKDAY_HOLIDAY) "(휴일)" else ""} ${nextMember}\n")
            }
        }
    }
}

private fun DayOfWeek.toKorean() = when (this) {
    DayOfWeek.MONDAY -> "월"
    DayOfWeek.TUESDAY -> "화"
    DayOfWeek.WEDNESDAY -> "수"
    DayOfWeek.THURSDAY -> "목"
    DayOfWeek.FRIDAY -> "금"
    DayOfWeek.SATURDAY -> "토"
    DayOfWeek.SUNDAY -> "일"
    else -> throw IllegalArgumentException()
}
