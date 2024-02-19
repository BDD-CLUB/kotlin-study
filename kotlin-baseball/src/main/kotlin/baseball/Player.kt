package baseball

import baseball.data.PlayerState
import baseball.util.*
import camp.nextstep.edu.missionutils.Console


class Player {

    private var _gameNumber = mutableListOf<Int>()
    val gameNumber: List<Int> get() = _gameNumber

    private var _state = PlayerState.IN_PROGRESS
    val state get() = _state

    private val validationCheck = ValidationChecker()

    // 게임 진행 중 플레이어에게 서로 다른 3자리 수를 입력 받음
    fun getGameNumber() {
        val input = Console.readLine() ?: ""
        validationCheck.checkGameNumber(input)
        _gameNumber = input
                .map { it.digitToInt() }
                .toMutableList()
    }

    // 게임 종료시 재시작할지, 종료할지에 대한 입력을 받음
    fun getExitNumber() {
        val input = Console.readLine() ?: ""
        validationCheck.checkExitNumber(input)
        _state = PlayerState from input.toInt()
    }

}
