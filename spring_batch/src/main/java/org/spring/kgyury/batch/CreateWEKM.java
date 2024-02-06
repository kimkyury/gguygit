package org.spring.kgyury.batch;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.EnumSet;

public class CreateWEKM extends QuartzJobBean {

    //  TODO: Inject ＋


    // TODO: Transactional
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {


        LocalDate batchDay = LocalDate.of(2024, 1, 27);
        LocalDate nextMonday = batchDay.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        System.out.println("확인할 주차의 월요일: " + nextMonday);

        int order = calculateWeekOrder(nextMonday);
        Month month = nextMonday.getMonth();
        if (isLastWeekOfMonth((nextMonday))) {
            LocalDate afterNextMonday = nextMonday.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
            if (calculateWeekOrder(afterNextMonday) == 2) {
                order = 1;
                month = afterNextMonday.getMonth();
            }
        }

        System.out.println("----------------------------------------");
        System.out.println(nextMonday + ": "
                + nextMonday.format(DateTimeFormatter.ofPattern("yyyy")) + "년 "
                + String.format("%02d", month.getValue()) + "월 "
                + String.format("%02d", order) + "주차");
    }

    public static boolean isLastWeekOfMonth(LocalDate nextMonday) {
        LocalDate lastDayOfMonth = nextMonday.with(TemporalAdjusters.lastDayOfMonth());
        LocalDate mondayOfLastWeek = lastDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        return nextMonday.equals(mondayOfLastWeek);
    }

    private static int calculateWeekOrder(LocalDate nextMonday) {
        LocalDate firstDay = nextMonday.with(TemporalAdjusters.firstDayOfMonth());

        // Find ```FirstWeek's Monday``` in Month
        LocalDate firstMonday = firstDay.getDayOfWeek().getValue() <= DayOfWeek.THURSDAY.getValue() ?
                firstDay.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)) :
                firstDay.with(TemporalAdjusters.next(DayOfWeek.MONDAY));

        // Return WeekOrder about ```nextMonday```
        return (int) ChronoUnit.WEEKS.between(firstMonday, nextMonday) + 1;
    }
}
