package step2.application

import step2.dto.BaseballResultDto
import step2.entity.RandomNumbers
import step2.global.createUniqueRandomNumbers

class BaseballService {

    fun createBaseball() {
        val createUniqueRandomNumbers = createUniqueRandomNumbers(3)
        RandomNumbers.changeRandomNumbers(createUniqueRandomNumbers)
    }

    fun compareRandomNumbers(userInputNumbers: String): BaseballResultDto =
            BaseballResultDto(RandomNumbers.countStrike(userInputNumbers), RandomNumbers.countBall(userInputNumbers))

}