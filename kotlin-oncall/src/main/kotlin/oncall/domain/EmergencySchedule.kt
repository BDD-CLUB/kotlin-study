package oncall.domain

class EmergencySchedule(
        private val day: Day,
        private val isHoliday: Boolean,
        private val name: String,
) {

    override fun toString(): String {
        return "EmergencySchedule(day=$day, isHoliday=$isHoliday, name='$name')"
    }
}