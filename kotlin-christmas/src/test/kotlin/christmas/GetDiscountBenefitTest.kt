package christmas

import christmas.data.UserMenu
import christmas.enums.Menu
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GetDiscountBenefitTest {

    @Test
    fun `크리스마스 디데이 이벤트`() {
        val date = 25
        val menuList = listOf(UserMenu(Menu.T_BONE_STEAK, 1))
        val getDiscountBenefit = GetDiscountBenefit(date, menuList)
        val expected = 3400
        assertEquals(expected, getDiscountBenefit.discountBenefit.christmasDDay)
    }

    @Test
    fun `평일 이벤트`() {
        val date = 4
        val menuList = listOf(
            UserMenu(Menu.CHOCOLATE_CAKE, 1),
            UserMenu(Menu.ICE_CREAM, 4),
        )
        val getDiscountBenefit = GetDiscountBenefit(date, menuList)
        val expected = 2023 * 5
        assertEquals(expected, getDiscountBenefit.discountBenefit.weekday)
        assertEquals(0, getDiscountBenefit.discountBenefit.weekend)
    }

    @Test
    fun `주말 이벤트`() {
        val date = 1
        val menuList = listOf(UserMenu(Menu.T_BONE_STEAK, 1))
        val getDiscountBenefit = GetDiscountBenefit(date, menuList)
        val expected = 2023
        assertEquals(expected, getDiscountBenefit.discountBenefit.weekend)
        assertEquals(0, getDiscountBenefit.discountBenefit.weekday)
    }

    @Test
    fun `스페셜 이벤트`() {
        val date = 25
        val menuList = listOf(UserMenu(Menu.T_BONE_STEAK, 1))
        val getDiscountBenefit = GetDiscountBenefit(date, menuList)
        val expected = 1000
        assertEquals(expected, getDiscountBenefit.discountBenefit.special)
    }

}
