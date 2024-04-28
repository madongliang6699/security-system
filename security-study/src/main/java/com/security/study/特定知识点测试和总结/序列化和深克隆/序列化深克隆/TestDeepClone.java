package com.security.study.特定知识点测试和总结.序列化和深克隆.序列化深克隆;

import cn.hutool.core.bean.BeanUtil;
import com.security.study.特定知识点测试和总结.序列化和深克隆.pojo.HseUser240428DTO;
import com.security.study.特定知识点测试和总结.序列化和深克隆.pojo.HseUser240428RpcDTO;
import org.springframework.beans.BeanUtils;

import java.io.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

public class TestDeepClone {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        HseUser240428DTO huangshang = getHseUser240428DTO();


        //序列化
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(huangshang);
        //反序列化
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
//        HseUser240428RpcDTO cloneUser = (HseUser240428RpcDTO)objectInputStream.readObject();

//        System.out.println(cloneUser);




    }

    public static HseUser240428DTO getHseUser240428DTO() {
        HseUser240428DTO huangshang = new HseUser240428DTO();
        huangshang.setSid(111L);
        huangshang.setAppId(10013);
        huangshang.setKeyword("keyword皇上");
        huangshang.setStartDate(new Date());
        huangshang.setEndDate(new Date());
        huangshang.setName("皇上");
        huangshang.setAge(60);
        huangshang.setJinqian(new BigDecimal("60.33"));

        HseUser240428DTO taizi = new HseUser240428DTO();
        taizi.setSid(222L);
        taizi.setAppId(10013);
        taizi.setKeyword("keyword太子");
        taizi.setStartDate(new Date());
        taizi.setEndDate(new Date());
        taizi.setName("太子");
        taizi.setAge(40);
        taizi.setJinqian(new BigDecimal("40.33"));

        HseUser240428DTO sunzi = new HseUser240428DTO();
        sunzi.setSid(333L);
        sunzi.setAppId(10013);
        sunzi.setKeyword("keyword孙子");
        sunzi.setStartDate(new Date());
        sunzi.setEndDate(new Date());
        sunzi.setName("孙子");
        sunzi.setAge(20);
        sunzi.setJinqian(new BigDecimal("20.33"));

        HseUser240428DTO sanAGe = new HseUser240428DTO();
        sanAGe.setSid(444L);
        sanAGe.setAppId(10013);
        sanAGe.setKeyword("keyword三阿哥");
        sanAGe.setStartDate(new Date());
        sanAGe.setEndDate(new Date());
        sanAGe.setName("三阿哥");
        sanAGe.setAge(38);
        sanAGe.setJinqian(new BigDecimal("38.33"));

        taizi.setChildrenDi(sunzi);

        huangshang.setChildrenDi(taizi);
        huangshang.setChildrenOther(Arrays.asList(sanAGe));
        return huangshang;
    }
}
