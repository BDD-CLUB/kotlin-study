package baseball

import camp.nextstep.edu.missionutils.Randoms

class Opponent {

    private var _answerNumber = mutableListOf<Int>()
    val answerNumber get() = _answerNumber

    fun getAnswerNumber(){
        while (_answerNumber.size < 3) {
            val randomNumber = Randoms.pickNumberInRange(1, 9)
            if (!_answerNumber.contains(randomNumber)) {
                _answerNumber.add(randomNumber)
            }
        }
    }
}