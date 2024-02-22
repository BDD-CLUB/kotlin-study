package lotto

import camp.nextstep.edu.missionutils.Console
import camp.nextstep.edu.missionutils.Randoms
import java.lang.IllegalStateException
import kotlin.jvm.Throws

//import lotto.common.require

class LottoStore {
    companion object {
        const val LOTTO_NUMBER_MIN = 1
        const val LOTTO_NUMBER_MAX = 45
        const val LOTTO_NUMBERS_PER_TICKET = 6
        const val LOTTO_PRICE = 1000
    }

    fun receivePaymentForLotto(): Pair<Int, Int> {
        var purchasePrice: Int?
        println("구입급액을 입력해주세요.")
        do {
            val input = readLine()
            purchasePrice = input?.toIntOrNull()
            if (purchasePrice == null) {
                println("[ERROR] 숫자로 입력해야합니다.")
                throw NumberFormatException("[ERROR] 숫자로 입력해야합니다.")
            } else if (purchasePrice % LOTTO_PRICE != 0) {
                println("[ERROR] 숫자는 $LOTTO_PRICE(으)로 나누어 떨어져야 합니다.")
                purchasePrice = null // 입력이 유효하지 않으므로 null로 설정하여 다시 입력 받음
            }
            // 유효한 입력을 받으면 자동으로 반복문에서 탈출
        } while (purchasePrice == null)

        println()
        return Pair(purchasePrice!!, purchasePrice / LOTTO_PRICE)
    }

    fun askWinningNumbers(): List<Int> {
        println("당첨 번호를 입력해주세요. 번호는 쉼표(,)로 구분하여 입력하세요:")
        var numbers: List<Int>
        do {
            numbers = readLine()?.split(",")?.mapNotNull { it.trim().toIntOrNull() } ?: emptyList()

            require(numbers.size == LOTTO_NUMBERS_PER_TICKET) {
                IllegalArgumentException("[ERROR] 정확히 $LOTTO_NUMBERS_PER_TICKET}개의 숫자를 입력해야 합니다.")
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
        var bonusNumber: Int?
        do {
            try {
                bonusNumber = Console.readLine().toInt()
                check(bonusNumber in LOTTO_NUMBER_MIN..LOTTO_NUMBER_MAX) { NumberFormatException() }
            } catch (e: IllegalArgumentException) {
                bonusNumber = null
                println("[ERROR] 정확한 숫자 형식으로 입력해주세요.")
            } catch (e: IllegalStateException) {
                bonusNumber = null
                println("[ERROR] 숫자는 ${LOTTO_NUMBER_MIN}과 ${LOTTO_NUMBER_MAX} 사이여야 합니다.")
            }
        } while (bonusNumber !is Int)
        println()

        return bonusNumber!!
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

