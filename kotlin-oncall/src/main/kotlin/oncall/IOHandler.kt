package oncall

import camp.nextstep.edu.missionutils.Console

class IOHandler {
    fun readInput(): String {
        return Console.readLine() ?: ""
    }

    fun readMonthAndDay(): Pair<Int, String> {
        print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ")
        val input = readInput().split(",")
        return Pair(input[0].toInt(), input[1])
    }

    fun getWeekdayEmergency(): List<String> {
        print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
        val input = readInput().split(",")
        return input
    }

    fun getWeekendEmergency(): List<String> {
        print("주말 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
        val input = readInput().split(",")
        return input

    }

}
