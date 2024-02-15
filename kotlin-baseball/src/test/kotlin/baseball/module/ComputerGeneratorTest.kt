package baseball.module

import baseball.main
import camp.nextstep.edu.missionutils.test.NsTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ComputerGeneratorTest : NsTest() {
    private lateinit var computerGenerator: ComputerGenerator

    @BeforeEach
    fun setUp() {
        computerGenerator = ComputerGenerator()
    }

    @Test
    fun `컴퓨터는 랜덤하게 1~9사이의 숫자를 선택한다`() {
        val generatedNumber = computerGenerator.generateRandomNumber()
        assertTrue(generatedNumber in NumberGenerator.MIN_VALUE..NumberGenerator.MAX_VALUE)

    }

    @Test
    fun `숫자는 랜덤하게 3번 생성된다`() {
        val generatedNumbers = computerGenerator.generateThreeRandomNumbers()
        assertEquals(3, generatedNumbers.size, "숫자가 3번 생성되지 않습니다.")
    }

    @Test
    fun `숫자 3개는 중복이 없다`() {
        val generatedNumbers = computerGenerator.generateThreeRandomNumbers()
        val distinctNumbers = generatedNumbers.distinct()

        assertEquals(3, distinctNumbers.size, "생성된 숫자들 중 중복이 있습니다.")
    }

    override fun runMain() {
        main()
    }
}