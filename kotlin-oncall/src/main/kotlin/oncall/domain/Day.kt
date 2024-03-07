package oncall.domain

enum class Day(
        val day: String,
        val isWeekend: Boolean,
) {
    MONDAY("월", false),
    TUESDAY("화", false),
    WEDNESDAY("수", false),
    THURSDAY("목", false),
    FRIDAY("금", false),
    SATURDAY("토", true),
    SUNDAY("일", true),
    ;

    fun next(): Day = entries[(this.ordinal + 1) % entries.size]
}