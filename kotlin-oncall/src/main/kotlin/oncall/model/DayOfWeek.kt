package oncall.model

enum class DayOfWeek(
    val isHoliday: Boolean
) {
    월(false),
    화(false),
    수(false),
    목(false),
    금(false),
    토(true),
    일(true)
    ;

    fun nextDayOfWeek(): DayOfWeek {
        return when (this) {
            월 -> 화
            화 -> 수
            수 -> 목
            목 -> 금
            금 -> 토
            토 -> 일
            일 -> 월
        }
    }
}

