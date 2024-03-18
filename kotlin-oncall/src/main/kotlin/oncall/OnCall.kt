package oncall

class OnCall {
    private val ioHandler: IOHandler = IOHandler()
    private val calender: Calender = Calender()

    fun run() {
        val (month, day) = ioHandler.readMonthAndDay()

        calender.setMonth(month)
        calender.setStartDay(day)

        // 평일 비상근무 사원
        val weekdayEmergency = ioHandler.getWeekdayEmergency()
        // 주말 비상근무 사원
        val weekendEmergency = ioHandler.getWeekendEmergency()


        // 근무 배정
        val result = calender.assignEmergency(weekdayEmergency, weekendEmergency)
    }
}
