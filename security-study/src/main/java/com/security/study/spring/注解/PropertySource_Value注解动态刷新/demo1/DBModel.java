package com.security.study.spring.注解.PropertySource_Value注解动态刷新.demo1;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MapPropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DBModel {
    
    @Value("${db.url}")
    String url;
    
    
    @Value("${db.userName}")
    String userName;
    
    @Value("${db.password}")
    String password;
    
    @Value("${db.tag:1212}") //todo 如果db.tag为空，使用1212默认值
    String tag;
    
    
    /**
     *
     * 通常情况下我们@Value的数据来源于配置文件，不过，还可以用其他方式，比如我们可以将配置文件的内容放在数据库，这样修改起来更容易一些。
     * 我们需要先了解一下@Value中的数据直接来源是位于spring的什么地方。【配置文件或者数据库只是@Value的最终来源】
     *
     * spring中有个类：org.springframework.core.env.PropertySource
     * 可以将其理解为一个配置源，里面包含了key->value的配置信息，可以通过这个类中提供的方法获取key对应的value信息。
     * 内部有个方法：
     * public abstract Object getProperty(String name);
     * 通过name获取对应的配置信息。
     *【猜测，配置文件中的key-value数据应该就是在适当时机已经预先解析到这个类里面了，@Value注解是直接从这里面拿的数据】
     *
     *
     * 另外：
     * 系统有个比较重要的接口：org.springframework.core.env.Environment
     * 用来表示环境配置信息，这个接口有几个方法比较重要：
     * String resolvePlaceholders(String text);
     * MutablePropertySources getPropertySources();
     *
     * resolvePlaceholders用来解析${text}的，@Value注解最后就是调用这个方法来解析的。
     * getPropertySources返回MutablePropertySources对象。
     * 来看一下MutablePropertySources这个类
     * public class MutablePropertySources implements PropertySources {
     *     private final List<PropertySource<?>> propertySourceList = new CopyOnWriteArrayList<>();
     * }
     * 内部包含一个propertySourceList列表。【就是上面说的预存了key-value键值对PropertySource对象集合，也就是我们配置文件的数据都在这里了】
     *
     * spring容器中会有一个Environment对象，最后会调用这个对象的resolvePlaceholders方法解析@Value。
     *
     * 大家可以捋一下，最终解析@Value的过程：
     * 1.将@Value注解的value参数值【比如：${db.url}】作为Environment.resolvePlaceholders方法参数进行解析
     * 2. Environment内部会调用getPropertySources()方法拿到MutablePropertySources。
     * 3. MutablePropertySources内部有多个PropertySource【我们配置的键值对】，此时会遍历PropertySource列表，调用PropertySource.getProperty(String name)方法来解析key对应的值
     *
     *
     * 通过上面过程，如果我们想改变@Value数据的来源，只需要将配置信息包装为PropertySource对象，丢到Environment中的MutablePropertySources内部就可以了。
     *下面通过 mail.host 来测试一下
     */
    
    public DBModel() {
        //模拟从db中获取配置信息
        Map<String, Object> infoFromDb = new HashMap<>();
        infoFromDb.put("mail.host", "smtp.qq.com");
        infoFromDb.put("mail.username", "路人");
        infoFromDb.put("mail.password", "123");
        //将其丢在MapPropertySource中（MapPropertySource类是spring提供的一个类，是PropertySource的子类）
        MapPropertySource mailPropertySource = new MapPropertySource("mail", infoFromDb);
        //将mailPropertySource丢在Environment中的PropertySource列表的第一个中，让优先级最高
        AnnotationConfigServletWebServerApplicationContext applicationContext = (AnnotationConfigServletWebServerApplicationContext)SpringUtil.getApplicationContext();
        applicationContext.getEnvironment().getPropertySources().addFirst(mailPropertySource);
    }
    
    
    
    @Value("${mail.host:默认值12}") //todo 如果db.tag为空，使用1212默认值
    String host;
    
    //通过MyController_0725中打印测试，host属性确实拿到了值“smtp.qq.com”

    
    public String getHost() {
        return host;
    }
    
    public void setHost(String host) {
        this.host = host;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getTag() {
        return tag;
    }
    
    public void setTag(String tag) {
        this.tag = tag;
    }
    
    
    @Override
    public String toString() {
        return "DBModel{" +
                "url='" + url + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", tag='" + tag + '\'' +
                ", host='" + host + '\'' +
                '}';
    }
}
