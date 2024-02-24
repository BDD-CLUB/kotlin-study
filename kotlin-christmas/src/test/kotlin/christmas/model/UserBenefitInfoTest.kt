package christmas.model

import christmas.event.EventType
import christmas.menu.Menu
import christmas.menu.MenuWithCount
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class UserBenefitInfoTest {

    @Test
    fun getGiveawayTest() {
        val userBenefitInfo1 = UserBenefitInfo(
            listOf(
                EventType.Giveaway(MenuWithCount(Menu.양송이스프, 1), anyString()),
                EventType.Discount(Price(1000), "anyString"), // stub for testing
            )
        )
        assertThat(userBenefitInfo1.giveaway).isNotNull
        assertThat(userBenefitInfo1.giveaway!!.menuWithCount).isEqualTo(MenuWithCount(Menu.양송이스프, 1))

        val userBenefitInfo2 = UserBenefitInfo(
            listOf(
                EventType.Discount(Price(1000), "anyString"), // stub for testing
            )
        )
        assertThat(userBenefitInfo2.giveaway).isNull()
    }

    @Test
    fun getTotalBenefitAmountTest() {
        val menu = mock<Menu> {
            on { price } doReturn Price(1000)
        }
        val userBenefitInfo = UserBenefitInfo(
            listOf(
                EventType.Giveaway(MenuWithCount(menu, 3), "anyString"), // 1000 * 3 == 3000원
                EventType.Discount(Price(500), "anyString"),
                EventType.Discount(Price(70), "anyString"),
            )
        )

        assertThat(userBenefitInfo.totalBenefitAmount).isEqualTo(Price(3570))
    }

    @Test
    fun getTotalDiscountAmountTest() {

        val menu = mock<Menu> {
            on { price } doReturn Price(1000)
        }
        val userBenefitInfo = UserBenefitInfo(
            listOf(
                EventType.Giveaway(MenuWithCount(menu, 3), "anyString"),
                EventType.Discount(Price(500), "anyString"),
                EventType.Discount(Price(70), "anyString"),
            )
        )

        assertThat(userBenefitInfo.totalDiscountAmount).isEqualTo(Price(570))
    }
}
