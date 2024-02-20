package lotto

import camp.nextstep.edu.missionutils.Console

class LottoGame {
    fun start() {
        val lottoList = mutableListOf<Lotto>()

        println("구입 금액을 입력해 주세요.")
        val amount: Int = getAmount()

        println()
        println("${amount}개를 구매했습니다.")

        repeat(amount) { lottoList.add(Lotto())}

        printLottoList(lottoList)

        val lottoPrizeNumbers = getLottoPrizeNumbers()
        print(lottoPrizeNumbers)

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

    private fun getLottoPrizeNumbers(): LottoPrizeNumbers {
        val lottoPrizeBaseNumber = getLottoPrizeBaseNumbers()
        val lottoPrizeBonusNumber = getLottoPrizeBonusNumber()
        return LottoPrizeNumbers(
            baseNumbers = lottoPrizeBaseNumber,
            bonusNumber = lottoPrizeBonusNumber
        )
    }

    private fun getLottoPrizeBonusNumber(): Int {
        println("보너스 번호를 입력해 주세요.")
        while (true) {
            try {
                val lottoPrizeBonusNumberInput = Console.readLine()
                return getValidLottoPrizeBonusNumbers(lottoPrizeBonusNumberInput)
            } catch (e: IllegalArgumentException) {
                println(e.message)
                println("보너스 번호를 다시 입력해 주세요.")
            }
        }
    }

    private fun getLottoPrizeBaseNumbers(): List<Int> {
        println("당첨 번호를 입력해 주세요.")
        while (true) {
            try {
                val lottoPrizeBaseNumberInput = Console.readLine()
                return getValidLottoPrizeBaseNumbers(lottoPrizeBaseNumberInput)
            } catch (e: IllegalArgumentException) {
                println(e.message)
                println("당첨 번호를 다시 입력해 주세요.")
            }
        }
    }

    private fun getValidLottoPrizeBaseNumbers(input: String): List<Int> {
        return input.split(",").map { part ->
            part.toIntOrNull()
                ?.takeIf { it in 1..45 }
                ?: throw IllegalArgumentException("[ERROR] ${part}은(는) 1부터 45 사이의 숫자여야 합니다.")
        }.also { numbers ->
            if (numbers.size != 6) {
                throw IllegalArgumentException("[ERROR] 입력된 숫자의 개수가 6개가 아닙니다. 정확히 6개의 숫자를 입력해야 합니다.")
            }
        }
    }

    private fun getValidLottoPrizeBonusNumbers(input: String): Int {
        return input.toIntOrNull()
                ?.takeIf { it in 1..45 }
                ?: throw IllegalArgumentException("[ERROR] {$input}은(는) 1부터 45 사이의 숫자여야 합니다.")
    }

    private fun getValidInput(input: String): Int {
        val validInput = input.toIntOrNull()
            ?: throw IllegalArgumentException("[ERROR]: ${input}은(는) 유효하지 않은 구입 금액입니다. 1000원 단위 숫자만 입력 가능합니다.")

        if (validInput % 1000 != 0 || validInput <= 0) {
            throw IllegalArgumentException("[ERROR]: ${input}은(는) 유효하지 않은 구입 금액입니다. 1000원 단위 숫자만 입력 가능합니다.")
        }

        return validInput
    }

    private fun printLottoList(lottoList: List<Lotto>) {
        for (lotto in lottoList) {
            println(lotto.getSortedLotto)
        }
    }

}