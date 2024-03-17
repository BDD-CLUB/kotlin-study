package oncall.model

import oncall.exception.OnCallException
import oncall.exception.customRequire

const val MAXIMUM_NICKNAME_LENGTH = 5

class Employee(
    val nickName: String
) {
    init {
        customRequire( nickName.length <= MAXIMUM_NICKNAME_LENGTH ) { OnCallException("유효하지 않은 입력 값입니다. 다시 입력해 주세요.") }
    }
}
