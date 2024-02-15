package baseball

import camp.nextstep.edu.missionutils.test.NsTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
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

    override fun runMain() {
        main()
    }
}