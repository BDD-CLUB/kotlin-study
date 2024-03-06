package oncall.view

class OutputView {

    fun printEmergencyAssignment() = print("비상 근무를 배정할 월과 시작 요일을 입력하세요>")

    fun printWeekdayEmergencyAssignment() = print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요>")

    fun printWeekendEmergencyAssignment() = print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요>")

}