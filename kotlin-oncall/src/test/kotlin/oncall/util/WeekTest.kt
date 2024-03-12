package oncall.util

import oncall.data.Week
import oncall.data.Week.Companion.getWeek
import oncall.data.Week.Companion.isHoliday
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class WeekTest {

    @Test
    fun `isHoliday - 시작 요일 월`() {
        val startWeek = Week.MONDAY

        assertFalse(startWeek.isHoliday(1))
        assertFalse(startWeek.isHoliday(2))
        assertFalse(startWeek.isHoliday(3))
        assertFalse(startWeek.isHoliday(4))
        assertFalse(startWeek.isHoliday(5))
        assertTrue(startWeek.isHoliday(6))
        assertTrue(startWeek.isHoliday(7))
        assertFalse(startWeek.isHoliday(8))
    }

    @Test
    fun `isHoliday - 시작 요일 일`() {
        val startWeek = Week.SUNDAY

        assertTrue(startWeek.isHoliday(1))
        assertFalse(startWeek.isHoliday(2))
        assertFalse(startWeek.isHoliday(3))
        assertFalse(startWeek.isHoliday(4))
        assertFalse(startWeek.isHoliday(5))
        assertFalse(startWeek.isHoliday(6))
        assertTrue(startWeek.isHoliday(7))
        assertTrue(startWeek.isHoliday(8))
    }

    @Test
    fun `isHoliday - 시작 요일 금`() {
        val startWeek = Week.FRIDAY

        assertFalse(startWeek.isHoliday(1))
        assertTrue(startWeek.isHoliday(2))
        assertTrue(startWeek.isHoliday(3))
        assertFalse(startWeek.isHoliday(4))
        assertFalse(startWeek.isHoliday(5))
        assertFalse(startWeek.isHoliday(6))
        assertFalse(startWeek.isHoliday(7))
        assertFalse(startWeek.isHoliday(8))
    }

    @Test
    fun `getWeek - 요일`() {
        val startWeek = Week.MONDAY
        val month = 4
        val weekList = listOf("월", "화", "수", "목", "금", "토", "일", "월")
        for (date in 1..8) {
            val result = startWeek.getWeek(month, date)
            assertEquals(weekList[date-1], result)
        }
    }

    @Test
    fun `getWeek - 휴일`() {
        val startWeek = Week.SUNDAY
        val month = 5
        val date = 5
        val result = startWeek.getWeek(month, date)
        assertEquals("목(휴일)", result)
    }
}
