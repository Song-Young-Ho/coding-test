import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

class 유연근무제 {
    static final List<DayOfWeek> WEEKEND = List.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
    static final int WEEK = 7;
    static final int WORK_DAY = 5;

    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;

        for (int schedule = 0; schedule < schedules.length; schedule++) {
            int day = startday;
            int correct = 0;

            for (int j = 0; j < timelogs[schedule].length; j++) {
                // 한 주 지남 -> 월요일로 초기화한다.
                if (day > WEEK) day = 1;

                // 주말은 기준에서 제외한다.
                if (WEEKEND.contains(DayOfWeek.of(day))) {
                    day ++;
                    continue;
                }

                // 기준 시간
                LocalTime standardTime = parseTime(schedules[schedule]).plusMinutes(10);
                // 출근 시간
                LocalTime attendance = parseTime(timelogs[schedule][j]);

                // 출근시간이 기준시간보다 늦음 -> 이벤트 대상 제외
                if (attendance.isAfter(standardTime)) break;

                correct ++;
                day ++;
            }

            // 평일 기준 만족 -> 이벤트 대상
            if (correct == WORK_DAY) answer++;
        }

        return answer;
    }

    private LocalTime parseTime(int timeInt) {
        String timeString = "";

        if (timeInt <= 999) timeString = "0" + timeInt;
        else timeString = String.valueOf(timeInt);

        int time = Integer.parseInt(timeString.substring(0, 2));
        int minute = Integer.parseInt(timeString.substring(2, 4));

        return LocalTime.of(time, minute);
    }
}