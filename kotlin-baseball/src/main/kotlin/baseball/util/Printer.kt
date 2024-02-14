package baseball.util

import baseball.data.Result

class Printer {

    fun startGame(){
        println("숫자 야구 게임을 시작합니다.")
    }

    fun getGameNumber(){
        print("숫자를 입력해주세요 : ")
    }

    fun printResult(result: Result){
        val ball = result.ball
        val strike = result.strike
        val isBallEmpty = ball == 0
        val isStrikeEmpty = strike == 0

        if (isBallEmpty && isStrikeEmpty){
            println("낫싱")
        }
        if (isBallEmpty && !isStrikeEmpty){
            println("${strike}스트라이크")
        }
        if (!isBallEmpty && isStrikeEmpty){
            println("${ball}볼")
        }
        if (!isBallEmpty && !isStrikeEmpty){
            println("${ball}볼 ${strike}스트라이크")
        }
    }

    fun success(){
        println("3개의 숫자를 모두 맞히셨습니다! 게임 종료")
    }

    fun getExitNumber(){
        println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.")
    }
}