package com.security.study.特定知识点测试和总结.临时测试;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class B {

    public static void main(String[] args) {

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
