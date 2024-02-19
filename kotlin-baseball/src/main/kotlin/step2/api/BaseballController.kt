package step2.api

import step2.application.BaseballService
import step2.dto.BaseballResultDto
import step2.view.BaseballView

class BaseballController(
        val baseballService: BaseballService
) {

    fun saveRandomNumbers() {
        baseballService.createBaseball()
    }

    fun getBaseballGameResult(userInputNumbers: String): BaseballResultDto =
            requireNotNull(userInputNumbers.takeIf { it.length == 3 && it.toIntOrNull() != null }) {
        "유효하지 않은 숫자 또는 문자입니다."
    }.let { baseballService.compareRandomNumbers(it) }

}