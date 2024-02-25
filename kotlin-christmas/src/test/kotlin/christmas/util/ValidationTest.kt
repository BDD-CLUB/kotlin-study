package christmas.util

import christmas.data.UserMenu
import christmas.enums.Menu
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ValidationTest {

    private val validation = Validation()

    @Test
    fun `이벤트 날짜 성공`() {
        val input = "3"
        val result = validation.checkDate(input)
        val expected = 3
        assertEquals(expected, result)
    }

    @Test
    fun `이벤트 날짜 실패 - 숫자 아님`() {
        assertThrows<IllegalArgumentException> {
            val input = "숫자"
            val result = validation.checkDate(input)
        }
    }

    @Test
    fun `이벤트 날짜 실패 - 범위 밖의 숫자`() {
        assertThrows<IllegalArgumentException> {
            val input = "33"
            val result = validation.checkDate(input)
        }
    }

    @Test
    fun `메뉴 성공`() {
        val input = "해산물파스타-2,레드와인-1,초코케이크-1"
        val result = validation.checkMenu(input)
        val expected = listOf(
            UserMenu(Menu.SEAFOOD_PASTA, 2),
            UserMenu(Menu.RED_WINE, 1),
            UserMenu(Menu.CHOCOLATE_CAKE, 1)
        )
        assertEquals(expected, result)
    }

    @Test
    fun `메뉴 실패 - 포맷 오류`() {
        assertThrows<IllegalArgumentException> {
            val input = "해산물파스타-2&&레드와인-1"
            val result = validation.checkMenu(input)
        }
    }

    @Test
    fun `메뉴 실패 - 존재하지 않는 메뉴`() {
        assertThrows<IllegalArgumentException> {
            val input = "토마토파스타-2"
            val result = validation.checkMenu(input)
        }
    }

    @Test
    fun `메뉴 실패 - 잘못된 가격 입력`() {
        assertThrows<IllegalArgumentException> {
            val input = "해산물파스타-2^10"
            val result = validation.checkMenu(input)
        }
    }

    @Test
    fun `메뉴 실패 - 음수 가격 입력`() {
        assertThrows<IllegalArgumentException> {
            val input = "해산물파스타--1000"
            val result = validation.checkMenu(input)
        }
    }

    @Test
    fun `메뉴 실패 - 중복 메뉴`() {
        assertThrows<IllegalArgumentException> {
            val input = "해산물파스타-2,해산물파스타-2"
            val result = validation.checkMenu(input)
        }
    }

    @Test
    fun `메뉴 실패 - 음료만 주문`() {
        assertThrows<IllegalArgumentException> {
            val input = "제로콜라-2"
            val result = validation.checkMenu(input)
        }
    }
    @Test
    fun `메뉴 실패 - 메뉴 개수 초과`() {
        assertThrows<IllegalArgumentException> {
            val input = "해산물파스타-20,레드와인-1,초코케이크-1"
            val result = validation.checkMenu(input)
        }
    }
}
