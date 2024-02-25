package christmas.badge

import christmas.model.Price
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BadgeTest {
    @Test
    fun `가격에 맞는 뱃지가 나와야 한다`() {
        assertThat(Badge.of(Price(0))).isEqualTo(null)
        assertThat(Badge.of(Price(3000))).isEqualTo(null)
        assertThat(Badge.of(Price(5000))).isEqualTo(Badge.별)
        assertThat(Badge.of(Price(8000))).isEqualTo(Badge.별)
        assertThat(Badge.of(Price(10000))).isEqualTo(Badge.트리)
        assertThat(Badge.of(Price(15000))).isEqualTo(Badge.트리)
        assertThat(Badge.of(Price(20000))).isEqualTo(Badge.산타)
        assertThat(Badge.of(Price(20001))).isEqualTo(Badge.산타)
        assertThat(Badge.of(Price(1_000_000))).isEqualTo(Badge.산타)
    }
}
