package christmas

import christmas.data.*
import christmas.enums.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EventServiceTest {

    @Test
    fun `성공 테스트`() {
        val date = 25
        val menuList = listOf(
            UserMenu(Menu.MUSHROOM_SOUP, 1),
            UserMenu(Menu.T_BONE_STEAK, 1),
            UserMenu(Menu.SEAFOOD_PASTA, 1),
            UserMenu(Menu.CHOCOLATE_CAKE, 2),
            UserMenu(Menu.RED_WINE, 2),
        )
        val eventBenefit = EventService(date, menuList).eventBenefit

        assertEquals(3400, eventBenefit.discountBenefit.christmasDDay)
        assertEquals(4046, eventBenefit.discountBenefit.weekday)
        assertEquals(0, eventBenefit.discountBenefit.weekend)
        assertEquals(1000, eventBenefit.discountBenefit.special)
        assertEquals(8446, eventBenefit.discountBenefit.getTotalPrice())

        assertEquals(246000, eventBenefit.totalAmountBeforeBenefit)
        assertEquals(UserMenu(Menu.CHAMPAGNE, 1), eventBenefit.presentationMenu)
        assertEquals(Badge.SANTA, eventBenefit.badge)

        assertEquals(237554, eventBenefit.getTotalAmountAfterBenefit())
        assertEquals(33446, eventBenefit.getBenefit())

    }

    @Test
    fun `혜택 없음 테스트`() {
        val date = 26
        val menuList = listOf(
            UserMenu(Menu.TAPAS, 1),
            UserMenu(Menu.ZERO_COKE, 1),
        )
        val eventBenefit = EventService(date, menuList).eventBenefit

        assertEquals(0, eventBenefit.discountBenefit.christmasDDay)
        assertEquals(0, eventBenefit.discountBenefit.weekday)
        assertEquals(0, eventBenefit.discountBenefit.weekend)
        assertEquals(0, eventBenefit.discountBenefit.special)
        assertEquals(0, eventBenefit.discountBenefit.getTotalPrice())

        assertEquals(8500, eventBenefit.totalAmountBeforeBenefit)
        assertEquals(null, eventBenefit.presentationMenu)
        assertEquals(null, eventBenefit.badge)

        assertEquals(8500, eventBenefit.getTotalAmountAfterBenefit())
        assertEquals(0, eventBenefit.getBenefit())

    }
}
