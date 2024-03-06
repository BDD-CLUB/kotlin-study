package oncall.domain

class EmergencyWork(
        val emergencyWorkDate: Pair<Int, Day>,
        val holidayEmployee: List<String>,
        val weekdayEmployee: List<String>,
) {



}