package christmas.enums

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MenuTest {

    @Test
    fun `메뉴 이름으로 Menu 찾기 - 잘못된 값 입력`() {
        assertThrows<IllegalArgumentException> {
            val value = "잘못된 값"
            val result = Menu.getByKoreanName(value)
        }
    }

    @Test
    fun `메뉴 이름으로 Menu 찾기 - 양송이수프`() {
        val value = "양송이수프"
        val result = Menu.getByKoreanName(value)
        val expected = Menu.MUSHROOM_SOUP
        assertEquals(expected, result)
    }

}
