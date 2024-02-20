package lotto

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LottoGenerateTest {

    private val lottoGenerator = LottoGenerator()

    @Test
    fun `로또 생성 성공`() {
        val amount = 10
        lottoGenerator.generate(amount)
        val result = lottoGenerator.lottoList.size
        assertEquals(result, amount)
        assertThatNoException()
    }
}
