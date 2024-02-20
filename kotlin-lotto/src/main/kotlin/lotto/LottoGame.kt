package lotto

import camp.nextstep.edu.missionutils.Console

const val LOTTO_MIN_NUMBER = 1
const val LOTTO_MAX_NUMBER = 45
const val LOTTO_NUMBERS_COUNT = 6
const val LOTTO_PRICE = 1000

class LottoGame {
    fun start() {
        val lottoList = mutableListOf<Lotto>()

        val amount: Int = getAmount()
        printAmount(amount)

        repeat(amount) { lottoList.add(Lotto())}
        printLottoList(lottoList)

        val lottoPrizeNumbers = getLottoPrizeNumbers()

        val lottoPrizeStatistics = LottoPrizeStatistics.of(lottoList, lottoPrizeNumbers)
        lottoPrizeStatistics.printStatistics()

        lottoPrizeStatistics.printROI()
    }

    private fun getAmount(): Int {
        println("구입 금액을 입력해 주세요.")
        while (true) {
            try {
                val input = Console.readLine()
                return getValidAmountByPrice(input)
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
        println()
        println("보너스 번호를 입력해 주세요.")
        while (true) {
            try {
                val lottoPrizeBonusNumberInput = Console.readLine()
                return getValidLottoPrizeBonusNumbers(lottoPrizeBonusNumberInput)
            } catch (e: IllegalArgumentException) {
                println(e.message)
                println()
                println("보너스 번호를 다시 입력해 주세요.")
            }
        }
    }

    private fun getLottoPrizeBaseNumbers(): List<Int> {
        println()
        println("당첨 번호를 입력해 주세요.")
        while (true) {
            try {
                val lottoPrizeBaseNumberInput = Console.readLine()
                return getValidLottoPrizeBaseNumbers(lottoPrizeBaseNumberInput)
            } catch (e: IllegalArgumentException) {
                println(e.message)
                println()
                println("당첨 번호를 다시 입력해 주세요.")
            }
        }
    }

    private fun getValidLottoPrizeBaseNumbers(input: String): List<Int> {
        return input.split(",").map { part ->
            part.toIntOrNull()
                ?.takeIf { it in LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER }
                ?: throw IllegalArgumentException("[ERROR] 로또 번호는 ${LOTTO_MIN_NUMBER}부터 ${LOTTO_MAX_NUMBER} 사이의 숫자여야 합니다.")
        }.also { numbers ->
            if (numbers.size != LOTTO_NUMBERS_COUNT) {
                throw IllegalArgumentException("[ERROR] 입력된 숫자의 개수가 ${LOTTO_NUMBERS_COUNT}개가 아닙니다. 정확히 ${LOTTO_NUMBERS_COUNT}개의 숫자를 입력해야 합니다.")
            }
            if (numbers.toSet().size != numbers.size) {
            throw IllegalArgumentException("[ERROR] 로또 번호에 중복된 숫자가 있습니다. 모든 숫자는 고유해야 합니다.")
            }
        }
    }

    private fun getValidLottoPrizeBonusNumbers(input: String): Int {
        return input.toIntOrNull()
                ?.takeIf { it in LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER }
                ?: throw IllegalArgumentException("[ERROR] {$input}은(는) ${LOTTO_MIN_NUMBER}부터 ${LOTTO_MAX_NUMBER} 사이의 숫자여야 합니다.")
    }

    private fun getValidAmountByPrice(input: String): Int {
        val validInput = input.toIntOrNull()
            ?: throw IllegalArgumentException("[ERROR]: ${input}은(는) 유효하지 않은 구입 금액입니다. ${LOTTO_PRICE}원 단위 숫자만 입력 가능합니다.")

        if (validInput % LOTTO_PRICE != 0 || validInput <= 0) {
            throw IllegalArgumentException("[ERROR]: ${input}은(는) 유효하지 않은 구입 금액입니다. ${LOTTO_PRICE}원 단위 숫자만 입력 가능합니다.")
        }

        return validInput / LOTTO_PRICE
    }

    private fun printAmount(amount: Int) {
        println()
        println("${amount}개를 구매했습니다.")
    }

    private fun printLottoList(lottoList: List<Lotto>) {
        for (lotto in lottoList) {
            println(lotto.sortedLotto)
        }
    }

}