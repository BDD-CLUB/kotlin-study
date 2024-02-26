package christmas.util

enum class Date(val dayOfWeek: Int) {
    SUNDAY(3),
    MONDAY(4),
    TUESDAY(5),
    WEDNESDAY(6),
    THURSDAY(7),
    FRIDAY(1),
    SATURDAY(2);

    companion object {
        fun getDayOfWeek(dayNumber: Int): Date? {
            return Date.values().find { it.dayOfWeek == dayNumber }
        }
    }

    fun getDateType(): DateType {
        return when (this) {
            FRIDAY, SATURDAY -> DateType.WEEKEND
            else -> DateType.WEEKDAY
        }
    }
}


enum class DateType(val description: String) {
    WEEKDAY("평일"),
    WEEKEND("주말")
}
