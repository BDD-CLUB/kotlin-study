package lotto

import camp.nextstep.edu.missionutils.Console
import camp.nextstep.edu.missionutils.Randoms
import lotto.common.require

class LottoStore {
    companion object {
        const val LOTTO_NUMBER_MIN = 1
        const val LOTTO_NUMBER_MAX = 45
        const val LOTTO_NUMBERS_PER_TICKET = 6
        const val LOTTO_PRICE = 1000
    }

    fun askWinningNumbers(): List<Int> {
        println("당첨 번호를 입력해주세요. 번호는 쉼표(,)로 구분하여 입력하세요:")
        var numbers: List<Int>
        do {
            numbers = readLine()?.split(",")?.mapNotNull { it.trim().toIntOrNull() } ?: emptyList()

            require(numbers.size == LOTTO_NUMBERS_PER_TICKET) {
                IllegalArgumentException("[ERROR] 정확히 ${LOTTO_NUMBERS_PER_TICKET}개의 숫자를 입력해야 합니다.")
            }
            numbers.forEach { number ->
                require(number in LOTTO_NUMBER_MIN..LOTTO_NUMBER_MAX) {
                    IllegalArgumentException("[ERROR] 숫자는 ${LOTTO_NUMBER_MIN}과 ${LOTTO_NUMBER_MAX} 사이여야 합니다.")
                }
            }

        } while (numbers.size != LOTTO_NUMBERS_PER_TICKET
            || !numbers.all { it in LOTTO_NUMBER_MIN..LOTTO_NUMBER_MAX }
        )

        println()

        return numbers
    }

    fun askBonusNumber(): Int {
        println("보너스 번호를 입력해 주세요.")
        var bonusNumber: Int
        do {
            bonusNumber = Console.readLine().toInt()
            require(bonusNumber in LOTTO_NUMBER_MIN..LOTTO_NUMBER_MAX) { IllegalArgumentException("[ERROR] 숫자는 ${LOTTO_NUMBER_MIN}과 ${LOTTO_NUMBER_MAX} 사이여야 합니다.") }
        } while (bonusNumber !in LOTTO_NUMBER_MIN..LOTTO_NUMBER_MAX)
        println()

        return bonusNumber
    }

    fun receivePaymentForLotto(): Pair<Int, Int> {
        var purchasePrice: Int?
        println("구입급액을 입력해주세요.")
        do {
            try {
                val input = Console.readLine()
                purchasePrice = input?.toInt()
            } catch (e: NumberFormatException) {
                println("[ERROR] 올바른 숫자를 입력해주세요.")
                purchasePrice = null
            }

        } while (purchasePrice == null)

        println()
        return Pair(purchasePrice, purchasePrice / LOTTO_PRICE)
    }


    fun generateLottoTickets(count: Int): List<Lotto> {
        return List(count) {
            generateLotto()
        }
    }

    fun generateLotto() = Lotto(generateRandomNumbers(LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX, LOTTO_NUMBERS_PER_TICKET))

}


fun generateRandomNumbers(startInclusive: Int, endInclusive: Int, count: Int): List<Number> =
    Randoms.pickUniqueNumbersInRange(startInclusive, endInclusive, count)

