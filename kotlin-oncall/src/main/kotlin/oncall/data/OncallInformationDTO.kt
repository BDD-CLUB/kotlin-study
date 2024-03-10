package oncall.data

data class OncallInformationDTO(
    val month: Int,
    val startWeek: String,
    val weekdayWorkerList: List<String>,
    val holidayWorkerList: List<String>
)
