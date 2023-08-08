package com.security.study.spring.autowire.aa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UserController0424 {
    
    /**
     * 按照类型注入还有2中比较牛逼的用法:
     *
     * 1, 一个容器中满足某种类型的bean可以有很多个，将容器中某种类型中的所有bean，
     * 通过set方法注入给一个java.util.List<需要注入的Bean的类型或者其父类型或者其接口>对象
     *
     * 2, 将容器中某种类型中的所有bean，通过set方法注入给一个java.util.Map<String,需要注入的Bean的类型或者其父类型或者其接口>对象
     *
     * 出处：http://itsoku.com/course/5/90
     */
    
    @Autowired
    List<BaseUserService0424> baseUserService0424List; //参数类型是List<BaseUserService0424>，这个集合中元素的类型是BaseUserService0424，
                                                        // spring会找到容器中所有满足BaseUserService0424.isAssignableFrom(bean的类型)的bean列表，
                                                        // 将其通过set方法进行注入。
    @Autowired
    BaseUserService0424 baseUserService0424;
    @Autowired
    IUserService0424 iUserService0424;
    @Autowired
    List<IUserService0424> iUserService0424List;
    
    @Autowired
    Map<String, BaseUserService0424> baseUserService0424Map;  // todo 这的功能的效果和 ApplicationContext对象的getBeansOfType(BaseUserService0424.class)方法很像。
                                                                //  getBeansOfType这个方法也能拿到BaseUserService0424的所有子类的实例bean。
    @Autowired
    Map<String, IUserService0424> iUserService0424Map;
    
    @RequestMapping(value = "/UserController")
    public String UserController(Integer a, String dd) {
        System.out.println(baseUserService0424List);
        System.out.println(baseUserService0424);
        System.out.println(iUserService0424);
        System.out.println(iUserService0424List);
        System.out.println(baseUserService0424Map);
        System.out.println(iUserService0424Map);
    
        /**
         * 执行结果：
         * [User0424{name='ma', age='null'}, UserService0424_2{name='xiaoming', age='23'}]
         * User0424{name='ma', age='null'}
         * User0424{name='ma', age='null'}
         * [User0424{name='ma', age='null'}, UserService0424_2{name='xiaoming', age='23'}]
         * {userService0424=User0424{name='ma', age='null'}, userService0424_2=UserService0424_2{name='xiaoming', age='23'}}
         * {userService0424=User0424{name='ma', age='null'}, userService0424_2=UserService0424_2{name='xiaoming', age='23'}}
         */
    
        
        
        return "地方";
    }
}
