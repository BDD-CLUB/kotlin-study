package oncall

import oncall.data.OncallInformationDTO
import oncall.data.Week
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class WorkOrderServiceTest {

    @Test
    fun `근무자 순서 정하기` () {
        val weekdayWorker = listOf("준팍","도밥","고니","수아","루루","글로","솔로스타","우코","슬링키","참새","도리")
        val holidayWorker = listOf("수아","루루","글로","솔로스타","우코","슬링키","참새","도리","준팍","도밥","고니")

        val oncallInformationDTO = OncallInformationDTO(5, Week.MONDAY, weekdayWorker, holidayWorker)
        val result = WorkOrderService(oncallInformationDTO).getWorkerList()
        val expected = listOf("준팍", "도밥", "고니", "수아", "루루", "수아", "글로", "루루", "글로")

        for (index in expected.indices) {
            assertEquals(expected[index], result[index])
        }
    }

}
