package oncall.domain

class EmergencySchedule(
        private val emergencyWork: EmergencyWork,
) {

    // - 오늘이 평일인지 확인

    // - 평일이라면 weekday에서 가져오기
    // -- 다음 날이 휴일 && 2일 연속 근무일 때 다음 휴일 근무자와 바꿔치기

    // - 휴일이라면 holiday에서 가져오기
    // -- 다음 날이 평일 && 2일 연속 근무일 때 다음 평일 근무자와 바꿔치기

}