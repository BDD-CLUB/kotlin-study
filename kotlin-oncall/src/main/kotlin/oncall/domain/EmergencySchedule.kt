package oncall.domain

class EmergencySchedule(
        val month: Month,
        val dayOfMonth: Int,
        val day: Day,
        val isHoliday: Boolean,
        val name: String,
) {

    override fun toString(): String {
        return "EmergencySchedule(month=$month, dayOfMonth=$dayOfMonth, day=$day, isHoliday=$isHoliday, name='$name')"
    }
}