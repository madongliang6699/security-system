package com.security.study.特定知识点测试和总结.临时测试;

import com.security.study.myBatis.E_多种查询详解.demo1.UserModel;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BB {

    public static void main(String[] args) {

        List<UserModel> lsit = new ArrayList<>();
        Map<Integer, UserModel> equipmentBaseInfoMap = lsit.stream().collect(Collectors.toMap(UserModel::getId, v -> v));
        System.out.println(equipmentBaseInfoMap);
        System.out.println(equipmentBaseInfoMap.get(1));
        System.out.println(equipmentBaseInfoMap.get(1).getName());



    }

}
