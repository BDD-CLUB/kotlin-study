package baseball.util

import baseball.data.Result

class Calculator {

    // 결과를 Result에 담아서 반환
    fun getResult(player: List<Int>, opponent: List<Int>): Result {
        val ball = getBall(player, opponent)
        val strike = getStrike(player, opponent)
        return Result(ball, strike)
    }

    // 같은 수가 같은 자리에 있으면 스트라이크
    private fun getStrike(player: List<Int>, opponent: List<Int>) =
            player.filterIndexed { index, num ->
                num == opponent[index]
            }.count()

    // 같은 수가 다른 자리에 있으면 볼
    private fun getBall(player: List<Int>, opponent: List<Int>) =
            player.filterIndexed { index, num ->
                num != opponent[index] && opponent.contains(num)
            }.count()
}
