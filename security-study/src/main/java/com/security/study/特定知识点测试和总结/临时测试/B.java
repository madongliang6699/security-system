package com.security.study.特定知识点测试和总结.临时测试;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class B {

    public static void main(String[] args) {


        LocalDate startDate = LocalDate.of(2023, 1, 4);// 起始日期
        Integer intervalDays = 4; // 固定的天数间隔
        LocalDate currentDate = LocalDate.now(); // 当前日期
        if(currentDate.isBefore(startDate)){
            LocalDate localDate = startDate.plusDays(intervalDays);
            System.out.println(localDate);
        }

        long daysBetween = ChronoUnit.DAYS.between(startDate, currentDate);
        long remainder = daysBetween % intervalDays;
        if (remainder == 0 && !currentDate.equals(startDate)) {
            LocalDate localDate = currentDate.plusDays(intervalDays);
            System.out.println(localDate);
        } else {
            LocalDate localDate = currentDate.plusDays(intervalDays - remainder);
            System.out.println(localDate);
        }


        LocalDateTime startDateTime = LocalDateTime.of(2024, 7, 16, 12, 23, 34);// 起始日期


        LocalDate localDate = startDateTime.toLocalDate();

        System.out.println(localDate.equals(LocalDate.now()));


        try {
            int i = 2 / 0;
        }catch (Exception exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
            System.out.println(exception.getLocalizedMessage());
            System.out.println(exception.getCause());
            System.out.println(exception.getSuppressed());
            System.out.println(exception.getStackTrace());
            System.out.println(exception.fillInStackTrace());
            //System.out.println(exception.);
            //String exceptionDetail = getExceptionDetail(exception);
            //System.out.println(exceptionDetail);
        }




    }


    public static String getExceptionDetail(Exception ex) {
        String ret = null;
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PrintStream pout = new PrintStream(out);
            ex.printStackTrace(pout);
            ret = new String(out.toByteArray());
            pout.close();
            out.close();
        } catch (Exception e) {

        }
        return ret;
    }

}
