package lotto

import camp.nextstep.edu.missionutils.Console

class LottoGame {
    fun start() {
        val lottoList = mutableListOf<Lotto>()

        println("구입 금액을 입력해 주세요.")
        val amount: Int = getAmount()
    }

    private fun getAmount(): Int {
        while (true) {
            try {
                val input = Console.readLine()
                val validInput = getValidInput(input)
                val amount = validInput / 1000
                return amount
            } catch (e: IllegalArgumentException) {
                println(e.message)
                println("구입 금액을 다시 입력해 주세요.")
            }
        }
    }

    private fun getValidInput(input: String): Int {
        val validInput = input.toIntOrNull()
            ?: throw IllegalArgumentException("[ERROR]: ${input}은(는) 유효하지 않은 구입 금액입니다. 1000원 단위 숫자만 입력 가능합니다.")

        if (validInput % 1000 != 0) {
            throw IllegalArgumentException("[ERROR]: ${input}은(는) 유효하지 않은 구입 금액입니다. 1000원 단위 숫자만 입력 가능합니다.")
        }

        return validInput
    }
}