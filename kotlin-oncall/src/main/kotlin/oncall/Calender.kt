package oncall

import java.util.Stack

class Calender {
    var month: Int? = null
    var startDay: Int? = null

    fun setMonth(month: Int): Int {
        this.month = month
        return month
    }

    fun setStartDay(day: String) {
        this.startDay = dayToInt(day)
    }

    fun assignEmergency(weekdayEmergency: List<String>, weekendEmergency: List<String>): Any? {
        var day = 1

        var weekdayTrun = 0
        var weekendTrun = 0
        var recentlyWorker = ""

        val lastDayOfMonth = Month.entries[month!! - 1].days

        val weekdayStack = Stack<String>()
        val weekendStack = Stack<String>()

        while (day <= lastDayOfMonth) {
            val dayOfWeek = Weekday.entries[(startDay!! + day - 1) % 7]
            if (dayOfWeek in Weekday.isWeekDay()) {
                if (!weekdayStack.isEmpty() && weekendStack.peek()!=recentlyWorker) {
                    recentlyWorker = weekdayStack.pop()

                } else {
                    if (recentlyWorker == weekdayEmergency[weekdayTrun]) { // 이전과 같다
                        weekdayStack.push(weekdayEmergency[weekdayTrun])
                        weekdayTrun = addTurn(weekdayTrun, weekdayEmergency)
                        recentlyWorker = weekdayEmergency[weekdayTrun]
                    } else { // 이전과 다르다
                        recentlyWorker = weekdayEmergency[weekdayTrun]
                        weekdayTrun = addTurn(weekdayTrun, weekdayEmergency)
                    }
                }
            }


            println("${month}월 ${day}일 : ${dayOfWeek.description}요일 ${recentlyWorker}")

            day++
        }




        return null
    }

    private fun dayToInt(day: String): Int = Weekday.match(day).day

    private fun addTurn(turn: Int, members: List<String>): Int = (turn + 1) % members.size
}

enum class Weekday(val day: Int, val description: String) {
    SUNDAY(0, "일"),
    MONDAY(1, "월"),
    TUESDAY(2, "화"),
    WEDNESDAY(3, "수"),
    THURSDAY(4, "목"),
    FRIDAY(5, "금"),
    SATURDAY(6, "토");

    companion object {
        fun match(day: String): Weekday {
            return when (day) {
                "일" -> SUNDAY
                "월" -> MONDAY
                "화" -> TUESDAY
                "수" -> WEDNESDAY
                "목" -> THURSDAY
                "금" -> FRIDAY
                "토" -> SATURDAY
                else -> throw IllegalArgumentException("요일을 올바르게 입력해주세요.")
            }
        }


        fun isWeekDay(): List<Weekday> {
            return listOf(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY)
        }

        fun isWeekend(): List<Weekday> {
            return listOf(SUNDAY, SATURDAY)
        }

    }
}

enum class Holiday(val month: Int, val day: Int, val description: String) {
    NEW_YEAR(1, 1, "신정"),
    INDEPENDENCE_MOVEMENT_DAY(3, 1, "삼일절"),
    CHILDREN_DAY(5, 5, "어린이날"),
    MEMORIAL_DAY(6, 6, "현충일"),
    LIBERATION_DAY(8, 15, "광복절"),
    NATIONAL_FOUNDATION_DAY(10, 3, "개천절"),
    HANGUL_DAY(10, 9, "한글날"),
    CHRISTMAS(12, 25, "성탄절")
}

enum class Month(val month: Int, val days: Int) {
    JANUARY(1, 31),
    FEBRUARY(2, 28),
    MARCH(3, 31),
    APRIL(4, 30),
    MAY(5, 31),
    JUNE(6, 30),
    JULY(7, 31),
    AUGUST(8, 31),
    SEPTEMBER(9, 30),
    OCTOBER(10, 31),
    NOVEMBER(11, 30),
    DECEMBER(12, 31)
}
