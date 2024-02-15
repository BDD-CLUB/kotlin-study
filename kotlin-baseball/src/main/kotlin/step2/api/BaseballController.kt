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

    fun getBaseballGameResult(userInputNumbers: String): BaseballResultDto {

        // 어노테이션으로 만들어보면 어떨까용?
        if (!(userInputNumbers.length == 3 && userInputNumbers.toIntOrNull() != null)) {
            throw IllegalArgumentException()
        }

        return baseballService.compareRandomNumbers(userInputNumbers)

    }

}