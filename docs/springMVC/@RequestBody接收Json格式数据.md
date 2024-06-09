
## json格式数据的好处
当http请求传输的数据格式比较复杂的时，可以采用json格式：
数据量更小，
组装数据更容易，
数据结构看起来更清晰

## Http发起json格式的请求
1、头中需要设置Content-Type的值为application/json（application/json;charset=UTF-8）。  
2、请求body中数据格式为json文本


## SpringMVC接受json格式数据(3个步骤)
步骤1：maven添加jackson配置
~~~xml
<!-- 添加jackson配置 -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-core</artifactId>
    <version>2.11.4</version>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.11.4</version>
</dependency>
~~~
jackson是一个json工具包，可以将json格式的字符串和java对象互转。
而我们body中的数据是json格式的，controller中方法是java对象，所以需要将json数据转换为java对象。
而json库有很多，比如jackson、gson、fastjson，此处我们使用springmvc推荐的jackson。


步骤2：springmvc中添加mvc驱动配置（springboot中应该默认就开启了）  
<!-- 添加mvc注解驱动 -->
<mvc:annotation-driven/>

这2个步骤配置好了之后，springmvc就被赋予了一个强大的功能，有能力将body中json格式的数据转换为java对象。
透露一下原理：springmvc容器中被添加了一个MappingJackson2HttpMessageConverter对象，这个类可以将body中json格式的数据转换为java对象，内部用到的是jackson。
MappingJackson2HttpMessageConverter这类就是在步骤1添加的maven包中。

步骤3：方法参数使用@RquestBody注解标注  
当我们希望controller中处理器的方法参数的数据来源于http请求的body时，需要在参数的前面加上@RequestBody注解
~~~java
@PostMapping("/user/add.do")
public ModelAndView add(@RequestBody UserDto user) {
    System.out.println("user:" + user);
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("/WEB-INF/view/result.jsp");
    modelAndView.addObject("msg", user);
    return modelAndView;
}
~~~
就这么简单，此时这个方法就可以接受json格式的数据，springmvc会自动将body中json格式的字符串转换为UserDto对象，
然后传递给上面的add方法的第一个参数。



## @RequestBody注解
用来接收http请求body的数据。

HTTP请求大家比较熟悉，比如POST方式提交的请求，是有个body部分的，在springmvc中，
我们希望控制器的方法中某个参数的值为http请求中的body的值，那么只需要在这个参数的前面加上@RequestBody注解，
springmvc会将http请求中body的数据读取出来，然后传递给这个参数。


