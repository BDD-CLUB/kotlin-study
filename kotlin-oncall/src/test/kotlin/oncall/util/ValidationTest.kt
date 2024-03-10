package oncall.util

import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ValidationTest {

    private val validationChecker = ValidationChecker()

    // ----------------배정 월, 시작요일 유효성 체크----------------

    @Test
    fun `배정 월, 시작요일 유효성 체크 성공`() {
        val size = 2
        val month = 1
        val week = "월"
        validationChecker.checkDate(size, month, week)
        assertThatNoException()
    }

    @Test
    fun `배정 월, 시작요일 유효성 체크 실패 - 사이즈 문제`() {
        assertThrows<IllegalArgumentException> {
            val size = 3
            val month = 1
            val week = "월"
            validationChecker.checkDate(size, month, week)
        }
    }

    @Test
    fun `배정 월, 시작요일 유효성 체크 실패 - 배정 달 문제`() {
        assertThrows<IllegalArgumentException> {
            val size = 2
            val month = 0
            val week = "월"
            validationChecker.checkDate(size, month, week)
        }
    }

    @Test
    fun `배정 월, 시작요일 유효성 체크 실패 - 시작 요일 문제`() {
        assertThrows<IllegalArgumentException> {
            val size = 2
            val month = 2
            val week = "안녕"
            validationChecker.checkDate(size, month, week)
        }
    }

    // ----------------평일 근무자 유효성 체크----------------

    @Test
    fun `평일 근무자 유효성 체크 성공`() {
        val input = listOf("가","나","다","라","마","바")
        validationChecker.checkWeekdayWorker(input)
        assertThatNoException()
    }

    @Test
    fun `평일 근무자 유효성 체크 실패 - 중복된 닉네임`() {
        assertThrows<IllegalArgumentException> {
            val input = listOf("가", "나", "다", "라", "마", "바", "바")
            validationChecker.checkWeekdayWorker(input)
        }
    }

    @Test
    fun `평일 근무자 유효성 체크 실패 - 닉네임 빈 문자열 존재`() {
        assertThrows<IllegalArgumentException> {
            val input = listOf("가", "", "다", "라", "마", "바", "바")
            validationChecker.checkWeekdayWorker(input)
        }
    }

    @Test
    fun `평일 근무자 유효성 체크 실패 - 닉네임 길이 너무 긺`() {
        assertThrows<IllegalArgumentException> {
            val input = listOf("가", "나", "다", "라면먹고싶다", "마", "바", "바")
            validationChecker.checkWeekdayWorker(input)
        }
    }

    @Test
    fun `평일 근무자 유효성 체크 실패 - 근무자수 너무 작음`() {
        assertThrows<IllegalArgumentException> {
            val input = List(2) { i -> "${i}인간" }
            validationChecker.checkWeekdayWorker(input)
        }
    }

    @Test
    fun `평일 근무자 유효성 체크 실패 - 근무자수 너무 많음`() {
        assertThrows<IllegalArgumentException> {
            val input = List(100) { i -> "${i}인간" }
            validationChecker.checkWeekdayWorker(input)
        }
    }

    // ----------------휴일 근무자 유효성 체크----------------

    @Test
    fun `휴일 근무자 유효성 체크 성공`() {
        val weekdayWorker = listOf("가","나","다","라","마","바")
        val holidayWorker = listOf("바","마","라","다","나","가")
        validationChecker.checkHolidayWorker(weekdayWorker, holidayWorker)
        assertThatNoException()
    }

    @Test
    fun `휴일 근무자 유효성 체크 실패 - 중복된 닉네임`() {
        assertThrows<IllegalArgumentException> {
            val weekdayWorker = listOf("가", "나", "다", "라", "마", "바")
            val holidayWorker = listOf("바", "마", "라", "나", "가", "가")
            validationChecker.checkHolidayWorker(weekdayWorker, holidayWorker)
        }
    }

    @Test
    fun `휴일 근무자 유효성 체크 실패 - 닉네임 빈 문자열 존재`() {
        assertThrows<IllegalArgumentException> {
            val weekdayWorker = listOf("가", "나", "다", "라", "마", "바")
            val holidayWorker = listOf("바", "마", "라", "다", "나", "")
            validationChecker.checkHolidayWorker(weekdayWorker, holidayWorker)
        }
    }

    @Test
    fun `휴일 근무자 유효성 체크 실패 - 닉네임 길이 너무 긺`() {
        assertThrows<IllegalArgumentException> {
            val weekdayWorker = listOf("가", "나", "다", "라", "마", "바")
            val holidayWorker = listOf("바지지퍼열렸어요", "마", "라", "다", "나", "가", "가")
            validationChecker.checkHolidayWorker(weekdayWorker, holidayWorker)
        }
    }

    @Test
    fun `휴일 근무자 유효성 체크 실패 - 근무자수 너무 작음`() {
        assertThrows<IllegalArgumentException> {
            val weekdayWorker = List(2) { i -> "${i}인간" }
            val holidayWorker = List(2) { i -> "${i}인간" }
            validationChecker.checkHolidayWorker(weekdayWorker, holidayWorker)
        }
    }

    @Test
    fun `휴일 근무자 유효성 체크 실패 - 근무자수 너무 많음`() {
        assertThrows<IllegalArgumentException> {
            val weekdayWorker = List(50) { i -> "${i}인간" }
            val holidayWorker = List(50) { i -> "${i}인간" }
            validationChecker.checkHolidayWorker(weekdayWorker, holidayWorker)
        }
    }

    @Test
    fun `휴일 근무자 유효성 체크 실패 - 평일 근무자랑 휴일 근무자 다름`() {
        assertThrows<IllegalArgumentException> {
            val weekdayWorker = listOf("가", "나", "다", "라", "마", "바")
            val holidayWorker = listOf("가구", "나락", "다람쥐", "라면", "마늘", "바질")
            validationChecker.checkHolidayWorker(weekdayWorker, holidayWorker)
        }
    }

    @Test
    fun `휴일 근무자 유효성 체크 실패 - 평일 근무자랑 휴일 근무자 인원수 다름`() {
        assertThrows<IllegalArgumentException> {
            val weekdayWorker = listOf("가", "나", "다", "라", "마", "바")
            val holidayWorker = listOf("가", "나", "다", "라", "마", "바", "사")
            validationChecker.checkHolidayWorker(weekdayWorker, holidayWorker)
        }
    }

}
