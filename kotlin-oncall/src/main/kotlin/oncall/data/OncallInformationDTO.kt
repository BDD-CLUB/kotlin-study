package oncall.data

data class OncallInformationDTO(
    val month: Int,
    val startWeek: Week,
    val weekdayWorkerList: List<String>,
    val holidayWorkerList: List<String>
)
