package christmas.event

import christmas.model.Price
import christmas.model.UserOrderInfo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import java.time.LocalDate

class EventPolicyTest {

    @Nested
    inner class GiveawayEventPolicyTest {

        val giveawayEventPolicy = EventPolicy.GiveawayEventPolicy

        @Test
        fun `증정 이벤트는 총 금액이 120,000원 이상이면 제공한다`() {
            val userOrderInfo1 = mock<UserOrderInfo> {
                on { totalPrice } doReturn Price(0)
            }
            assertThat(giveawayEventPolicy.isOwnSupported(userOrderInfo1)).isFalse

            val userOrderInfo2 = mock<UserOrderInfo> {
                on { totalPrice } doReturn Price(119_999)
            }
            assertThat(giveawayEventPolicy.isOwnSupported(userOrderInfo2)).isFalse

            val userOrderInfo3 = mock<UserOrderInfo> {
                on { totalPrice } doReturn Price(120_000)
            }
            assertThat(giveawayEventPolicy.isOwnSupported(userOrderInfo3)).isTrue

            val userOrderInfo4 = mock<UserOrderInfo> {
                on { totalPrice } doReturn Price(200_000)
            }
            assertThat(giveawayEventPolicy.isOwnSupported(userOrderInfo4)).isTrue
        }
    }

    @Nested
    inner class ChristmasDiscountPolicyTest {

        val christmasDiscountPolicy = EventPolicy.ChristmasDiscountPolicy

        @Test
        fun `크리스마스 디데이 할인은 12월 25일 이전이면 제공한다`() {
            val userOrderInfo1 = mock<UserOrderInfo> {
                on { estimatedVisitDate } doReturn LocalDate.of(2023, 12, 1)
            }
            assertThat(christmasDiscountPolicy.isOwnSupported(userOrderInfo1)).isTrue

            val userOrderInfo2 = mock<UserOrderInfo> {
                on { estimatedVisitDate } doReturn LocalDate.of(2023, 12, 25)
            }
            assertThat(christmasDiscountPolicy.isOwnSupported(userOrderInfo2)).isTrue

            val userOrderInfo3 = mock<UserOrderInfo> {
                on { estimatedVisitDate } doReturn LocalDate.of(2023, 12, 26)
            }
            assertThat(christmasDiscountPolicy.isOwnSupported(userOrderInfo3)).isFalse

            val userOrderInfo4 = mock<UserOrderInfo> {
                on { estimatedVisitDate } doReturn LocalDate.of(2023, 12, 31)
            }
            assertThat(christmasDiscountPolicy.isOwnSupported(userOrderInfo4)).isFalse
        }

        @Test
        fun `크리스마스 디데이 할인은 하루가 지날 때마다 1000원씩 증가한 할인을 제공한다`() {
            val userOrderInfo1 = mock<UserOrderInfo> {
                on { estimatedVisitDate } doReturn LocalDate.of(2023, 12, 1)
            }
            assertThat(christmasDiscountPolicy.getBenefit(userOrderInfo1)).isInstanceOf(EventType.Discount::class.java)
            assertThat((christmasDiscountPolicy.getBenefit(userOrderInfo1) as EventType.Discount).price)
                .isEqualTo(Price(1000))

            val userOrderInfo2 = mock<UserOrderInfo> {
                on { estimatedVisitDate } doReturn LocalDate.of(2023, 12, 25)
            }
            assertThat(christmasDiscountPolicy.getBenefit(userOrderInfo2)).isInstanceOf(EventType.Discount::class.java)
            assertThat((christmasDiscountPolicy.getBenefit(userOrderInfo2) as EventType.Discount).price)
                .isEqualTo(Price(3400))
        }
    }

    // 중략 ...
    @Nested
    inner class WeekdayDiscountPolicyTest {
    }

    @Nested
    inner class WeekendDiscountPolicyTest {
    }

    @Nested
    inner class SpecialDiscountPolicyTest {
    }

}
