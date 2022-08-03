package com.knubisoft.base.date;

import org.checkerframework.checker.units.qual.C;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateTasksImpl implements DateTasks {

    @Override
    public String add1Day(String date) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = simpleDateFormat.parse(date);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_YEAR,1);


        return simpleDateFormat.format(calendar.getTime());
    }
    @Override
    public int getMonthFromDate(String date) throws ParseException {

        SimpleDateFormat originalFormat = new SimpleDateFormat("E, d MMM yyyy");
        SimpleDateFormat updatedFormat = new SimpleDateFormat("MM");

        Date formattedDate = originalFormat.parse(date);

        return Integer.parseInt(updatedFormat.format(formattedDate));
    }

    @Override
    public String findBiggestDate(String date1, String date2, String date3) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date formattedDate1 = simpleDateFormat.parse(date1);
        Date formattedDate2 = simpleDateFormat.parse(date2);
        Date formattedDate3 = simpleDateFormat.parse(date3);

        Date biggestDate = formattedDate1.getTime() > formattedDate2.getTime() ? formattedDate1: formattedDate2;
        biggestDate = biggestDate.getTime() > formattedDate3.getTime() ? biggestDate: formattedDate3;

        return simpleDateFormat.format(biggestDate);
    }

    @Override
    public String getLastDayOfTheMonth(String date) throws ParseException {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date formattedDate = simpleDateFormat.parse(date);

        calendar.setTime(formattedDate);
        calendar.add(Calendar.MONTH,1);
        int days = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DAY_OF_MONTH, -days);

        return simpleDateFormat.format(calendar.getTime());
    }

    @Override
    public String sumTimes(String time1, String time2) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        Date dateTime1 = simpleDateFormat.parse(time1);
        Date dateTime2 = simpleDateFormat.parse(time2);

        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(dateTime1);
        calendar2.setTime(dateTime2);

        calendar1.add(Calendar.MILLISECOND,calendar2.get(Calendar.MILLISECOND));
        calendar1.add(Calendar.SECOND,calendar2.get(Calendar.SECOND));
        calendar1.add(Calendar.MINUTE,calendar2.get(Calendar.MINUTE));
        calendar1.add(Calendar.HOUR_OF_DAY,calendar2.get(Calendar.HOUR_OF_DAY));

        return simpleDateFormat.format(calendar1.getTime());
    }

    @Override
    public String getDateAfter2Weeks(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date formattedDate = simpleDateFormat.parse(date);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(formattedDate);
        calendar.add(Calendar.WEEK_OF_YEAR, 2);

        return simpleDateFormat.format(calendar.getTime());
    }

    @Override
    public long getNumberOfDaysBetweenTwoDates(String date1, String date2) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date formattedDate1 = sdf.parse(date1);
        Date formattedDate2 = sdf.parse(date2);

        long timeDiff = Math.abs(formattedDate1.getTime()-formattedDate2.getTime());
        return TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
    }

    @Override
    public String[] getTheNextAndPreviousFriday(String date) throws ParseException {
        String[] fridays = new String[2];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date formattedDate = sdf.parse(date);
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(formattedDate);
        boolean isFriday = calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY;

        if (isFriday) {
            calendar.add(Calendar.DAY_OF_YEAR, -7);
            fridays[0] = sdf.format(calendar.getTime());
            calendar.add(Calendar.DAY_OF_YEAR, 14);
        } else {
            while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
                calendar.add(Calendar.DAY_OF_WEEK, -1);
            }
            fridays[0] = sdf.format(calendar.getTime());
            calendar.add(Calendar.DAY_OF_YEAR, 7);
        }
        fridays[1] = sdf.format(calendar.getTime());

        return fridays;
    }

    @Override
    public int getNumberOfMonthsRemainingInTheYear(String date) throws ParseException {
        SimpleDateFormat sdf_input = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf_output = new SimpleDateFormat("M");
        Date dateInput = sdf_input.parse(date);

        return 12 - Integer.parseInt(sdf_output.format(dateInput));
    }
}
