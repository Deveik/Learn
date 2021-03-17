package org.xxx;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;

public class DateTest {
    @Test
    public void temporalTest() {
        LocalDate now = LocalDate.now();
        System.out.println(now.get(WeekFields.ISO.weekOfMonth()));
    }

    @Test
    public void printTest() {
        System.out.printf("%-2d", 2);
    }

    @Test
    public void scanTest() {

        Period period = Period.between(LocalDate.now(), LocalDate.now().minusDays(4));
        System.out.println(period.getDays());
    }
}
