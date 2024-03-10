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
    SUNDAY("일");

    companion object {
        fun contains(value: String): Boolean {
            return entries.any { it.korean == value }
        }
    }
}
