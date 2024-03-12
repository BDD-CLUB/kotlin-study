package oncall.data

enum class Week(
    val korean: String
) {
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토"),
    SUNDAY("일"),
    ;

    companion object {
        fun get(value: String): Week? {
            return entries.firstOrNull { it.korean == value }
        }
        fun Week.isHoliday(date: Int): Boolean {
            val holidayOrdinal = (this.ordinal + date - 1)%7
            return holidayOrdinal >= SATURDAY.ordinal
        }
        fun Week.getWeek(month: Int, date: Int): String {
            val result = entries.first { it.ordinal == (this.ordinal + date - 1)%7 }.korean
            val isHoliday = if (Holiday.isHoliday(month, date)) "(휴일)" else ""
            return result + isHoliday
        }
    }
}

enum class Holiday(
    val month: Int,
    val date: Int,
    val korean: String
) {
    NEW_YEARS_DAY(1, 1, "신정"),
    INDEPENDENCE_MOVEMENT_DAY(3, 1, "삼일절"),
    CHILDREN_S_DAY(5, 5, "어린이날"),
    MEMORIAL_DAY(6, 6, "현충일"),
    LIBERATION_DAY(8, 15, "광복절"),
    NATIONAL_FOUNDATION_DAY(10, 3, "개천절"),
    HANGUL_DAY(10, 9, "한글날"),
    CHRISTMAS(12, 25, "성탄절"),
    ;

    companion object {
        fun isHoliday(month: Int, date: Int): Boolean {
            return entries.any { holiday ->
                holiday.month == month && holiday.date == date
            }
        }
    }
}

fun getEndDateOfMonth(month: Int): Int {
    if (month !in 1..12) {
        throw IllegalArgumentException()
    }
    val daysOfMonth = listOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    return daysOfMonth[month-1]
}
