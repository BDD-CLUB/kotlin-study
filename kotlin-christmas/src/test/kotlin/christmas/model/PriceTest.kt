package christmas.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PriceTest {

    @Test
    fun times() {
        assertThat(Price(100) * 10).isEqualTo(Price(1000))
        assertThat(Price(250) * 10).isEqualTo(Price(2500))
    }

    @Test
    fun plus() {
        assertThat(Price(100) + Price(10)).isEqualTo(Price(110))
        assertThat(Price(250) + Price(10)).isEqualTo(Price(260))
    }

    @Test
    fun minus() {
        assertThat(Price(100) - Price(10)).isEqualTo(Price(90))
        assertThat(Price(250) - Price(10)).isEqualTo(Price(240))
    }

    @Test
    fun compareTo() {
        assertThat(Price(100) > Price(10)).isTrue
        assertThat(Price(250) < Price(10)).isFalse
        assertThat(Price(100) == Price(100)).isTrue
        assertThat(Price(100) == Price(50)).isFalse

        assertThat(Price(100) > 10).isTrue
        assertThat(Price(250) < 10).isFalse
        assertThat(Price(100) >= 100).isTrue
        assertThat(Price(100) <= 50).isFalse
    }

    @Test
    fun `toMinusString을 사용하면 0원을 제외한 나머지 금액은 마이너스가 붙어야 한다`() {
        assertThat(Price(0).toMinusString()).isEqualTo("0원")
        assertThat(Price(100).toMinusString()).isEqualTo("-100원")
        assertThat(Price(12345).toMinusString()).isEqualTo("-12,345원")
    }
}
