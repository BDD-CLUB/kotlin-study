package oncall.model

import oncall.exception.OnCallException
import oncall.exception.customRequire

const val MINIMUM_ONCALL_EMPLOYEES = 5
const val MAXIMUM_ONCALL_EMPLOYEES = 35

class OnCallEmployee(
    val weekdayEmployees: List<Employee>,
    val holidayEmployees: List<Employee>
) {
    init {
        customRequire( weekdayEmployees.size == weekdayEmployees.toSet().size ) { OnCallException("유효하지 않은 입력 값입니다. 다시 입력해 주세요.")}
        customRequire( holidayEmployees.size == holidayEmployees.toSet().size ) { OnCallException("유효하지 않은 입력 값입니다. 다시 입력해 주세요.")}
        customRequire(weekdayEmployees.toSet().size + holidayEmployees.toSet().size in MINIMUM_ONCALL_EMPLOYEES..< MAXIMUM_ONCALL_EMPLOYEES) { OnCallException("유효하지 않은 입력 값입니다. 다시 입력해 주세요.")}
    }
}
