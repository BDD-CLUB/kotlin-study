package baseball

import baseball.util.*
import camp.nextstep.edu.missionutils.Randoms

class Opponent {

    private var _answerNumber = mutableListOf<Int>()
    val answerNumber: List<Int> get() = _answerNumber

    fun getAnswerNumber() {
        _answerNumber.clear()
        while (_answerNumber.size < NUM_COUNT) {
            val randomNumber = Randoms.pickNumberInRange(MIN_VALUE, MAX_VALUE)
            if (!_answerNumber.contains(randomNumber)) {
                _answerNumber.add(randomNumber)
            }
        }
    }
}
