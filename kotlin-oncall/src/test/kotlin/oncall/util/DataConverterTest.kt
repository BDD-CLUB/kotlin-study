package oncall.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DataConverterTest {

    private val dataConverter = DataConverter()

    @Test
    fun `배정 월, 시작요일 입력 성공`() {
        val input = "1,월"
        val (month, week) = dataConverter.convertDate(input)

        assertEquals(1, month)
        assertEquals("월", week)
    }

    @Test
    fun `배정 월, 시작요일 입력 실패`() {
        assertThrows<IllegalArgumentException> {
            val input = "asdf,월"
            val (month, week) = dataConverter.convertDate(input)
        }
    }

    @Test
    fun `평일 근무자 입력 성공`() {
        val input = "a,b,c,d,e"
        val worker = dataConverter.convertWeekdayWorker(input)

        assertEquals(worker, listOf("a", "b", "c", "d", "e"))
    }

    @Test
    fun `휴일 근무자 입력 성공`() {
        val weekdayWorker = listOf("가","나","다","라","마","바")
        val holidayWorker = "바,마,라,다,나,가"
        val worker = dataConverter.convertHolidayWorker(holidayWorker, weekdayWorker)

        assertEquals(worker, listOf("바", "마", "라", "다", "나", "가"))
    }

}
