package oncall.domain

class EmergencyWork(
        val month: Int,
        val startDay: String,
        val holidayEmployee: List<String>,
        val weekdayEmployee: List<String>,
) {



}