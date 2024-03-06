package oncall.domain

class EmergencySchedule(
        private val emergencyWork: EmergencyWork,
) {

    val schedule: Map<String, String> by lazy {
        emergencyWork.holidayEmployee.associate { it }
    }

}