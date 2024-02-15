package baseball.model

import camp.nextstep.edu.missionutils.Randoms

class RandomNumbers {

    private val _numbers = mutableListOf<Int>()

    init {
        while (_numbers.size < NUMBERS_SIZE) {
            val randomNumber = Randoms.pickNumberInRange(MIN_NUMBER, MAX_NUMBER)
            if (!_numbers.contains(randomNumber)) {
                _numbers.add(randomNumber)
            }
        }
    }

    val numbers: List<Int>
        get() = _numbers

    fun contains(number: Int): Boolean {
        return _numbers.contains(number)
    }
}
