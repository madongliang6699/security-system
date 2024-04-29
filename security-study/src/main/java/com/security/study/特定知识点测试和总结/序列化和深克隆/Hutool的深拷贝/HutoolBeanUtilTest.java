package com.security.study.特定知识点测试和总结.序列化和深克隆.Hutool的深拷贝;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.cglib.CglibUtil;
import com.security.study.特定知识点测试和总结.序列化和深克隆.pojo.HseUser240428DTO;
import com.security.study.特定知识点测试和总结.序列化和深克隆.pojo.HseUser240428RpcDTO;
import com.security.study.特定知识点测试和总结.序列化和深克隆.序列化深克隆.TestDeepClone;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class HutoolBeanUtilTest {

    public static void main(String[] args) {

        HseUser240428DTO huangshang = TestDeepClone.getHseUser240428DTO();
        HseUser240428DTO huangshang2 = TestDeepClone.getHseUser240428DTO();


        HseUser240428RpcDTO hseUser240428RpcDTO = BeanUtil.copyProperties(huangshang, HseUser240428RpcDTO.class);
        HseUser240428RpcDTO hseUser240428RpcDTO1 = new HseUser240428RpcDTO();
        BeanUtils.copyProperties(huangshang, hseUser240428RpcDTO1);

        huangshang.setName(huangshang.getName()+"--");
        HseUser240428DTO childrenDi = huangshang.getChildrenDi();
        childrenDi.setName(childrenDi.getName()+"---");
        HseUser240428DTO childrenDi1 = huangshang.getChildrenDi().getChildrenDi();
        childrenDi1.setName(childrenDi1.getName()+"----");

        System.out.println(huangshang);
        System.out.println(hseUser240428RpcDTO);
        /**
         * 从上面的两个打印可以看出，hutool的BeanUtils.copyProperties()是深拷贝，并且是可以从A对象深拷贝到B对象的，只要A和B对象的属性名称一样，
         * 甚至类型不一样也能拷贝赋值，只要名称一样。比如上面的Integer类型的age属性转换成了String类型的age属性。
         */

        System.out.println(hseUser240428RpcDTO1);


        System.out.println("-----------------------------");

        List<HseUser240428DTO> list = new ArrayList<>();
        list.add(huangshang);
        list.add(huangshang2);

        List<HseUser240428RpcDTO> hseUser240428RpcDTOS = BeanUtil.copyToList(list, HseUser240428RpcDTO.class);

        System.out.println(list);
        System.out.println(hseUser240428RpcDTOS);

        HseUser240428RpcDTO cglibCopy = CglibUtil.copy(huangshang, HseUser240428RpcDTO.class);
        HseUser240428RpcDTO cglibCopyList = CglibUtil.copy(list, HseUser240428RpcDTO.class);

        //看结果只能浅拷贝,还不能类型转换
        System.out.println("cglibCopy==>" + cglibCopy);
        System.out.println("cglibCopyList==>" + cglibCopyList);
    }


}
