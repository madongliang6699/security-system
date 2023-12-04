package com.security.study.Security_JWT.Security_JWT.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.web.accept.ContentNegotiationStrategy;

/**
 * ⾃定义资源权限规则，请求拦截规则
 */
@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * 注意：只要重写了这个方法，security默认给我们配置的那些规则就失效了，因为重写了之后就等于把父类里写好的规则给替换了，
         * 所以 所有的拦截规则就需要这里明确的配置好。比如哪些请求需要拦截保护等。
         * 如果这个方法里面没有配置认证拦截规则，那所有接口都不会拦截保护了。
         */

        http.authorizeRequests()
                .mvcMatchers("/login.html").permitAll()
                .mvcMatchers("/index").permitAll() //permitAll() 代表放⾏该资源,该资源为公共资源 ⽆需认证和授权可以直接访问。todo 注意: 放⾏资源必须放在所有认证请求之前!
                .anyRequest().authenticated() //anyRequest().authenticated() 代表所有请求,必须认证之后才能访问
                .and()
                .formLogin() //代表开启表单认证。怎么样设置是不开启？如果不开启就不需要登录了吗? （很多认证的逻辑可以进这个方法里断点源码查看）
//                .loginPage("/login.html") //登录页面地址，可以写一个自己的登录页面。（注意：这个页面就是检测到如果没登录就跳转登录的地址，所以上面一定要给这个地址放行拦截，要不然就循环跳转了。【当然现在测试的，没有实际写这个页面，就先注释掉，用默认的登录页】
                .loginProcessingUrl("/doLogin") //指定登录请求的url，点击登录按钮就请求这个按钮，与前端保持一致。前端登录表单 method 必须为 post
                .usernameParameter("uname") //指定用户名的字段名，与前端保持一致
                .passwordParameter("passwd") //指定密码的字段名，与前端保持一致
//                .successForwardUrl("/index") //forward 转发，登录成功后直接跳转到这个指定路径
//                .defaultSuccessUrl("/index" /*, true*/) //redirect 重定向，如果之前有请求路径, 会有优先跳转之前请求路径。否则跳转到这个指定的路径。如果设置了第二个参数为true，就每次都只跳转这个指定的地址。所以不传就是默认的false。
                .successHandler(new MyAuthenticationSuccessHandler()) //如果是前后端分离的项目，登录成功后，是不需要后端跳转地址的，只返回一个成功的json数据就行了，那就需要配置这个参数，上面的两个参数就不需要了。
//                .failureForwardUrl("/xxx")//失败以后的转发跳转。因此如果页面想获取 request 中异常信息进行展示,这⾥只能使⽤failureForwardUrl（知识点：如果登录失败，security把异常信息放在了request（转发的情况下存在了request中）和session（重定向情况下）作用域中了，key是“SPRING_SECURITY_LAST_EXCEPTION”，源码可以参考SimpleUrlAuthenticationFailureHandler类）
//                .failureUrl("/login.html") //认证失败后跳转的路径, 失败以后的重定向跳转
                .failureHandler(new MyAuthenticationFailureHandler())//同上面的successHandler参数
                .and()
                .logout()//开启注销配置
                .logoutRequestMatcher(//如果项⽬中有需要，开发者还可以配置多个注销登录的请求，同时还可以指定请求的⽅法
                        new OrRequestMatcher(
                                new AntPathRequestMatcher("/logout1","GET"),
                                new AntPathRequestMatcher("/logout","GET")
                        )
                )
//                .logoutUrl("/logout")// 指定退出登录请求地址，默认是 GET 请求，路径为 /logout。前后端分离的就不需要了
                .invalidateHttpSession(true)//退出时是否是 session 失效，默认值为 true
                .clearAuthentication(true)//退出时是否清除认证信息，默认值为 true
//                .logoutSuccessUrl("/login.html")//退出登录时跳转地址。前后端分离的就不需要了
                .logoutSuccessHandler(new MyLogoutSuccessHandler()) //前后端分离情况下，给返回一个json数据
                .and()
                .csrf().disable(); //这⾥先关闭 CSRF
    }


//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        super.configure(web);
//    }
//
//
//    protected WebSecurityConfigurer() {
//        super();
//    }
//
//    protected WebSecurityConfigurer(boolean disableDefaults) {
//        super(disableDefaults);
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
//    }
//
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Override
//    protected AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }
//
//    @Override
//    public UserDetailsService userDetailsServiceBean() throws Exception {
//        return super.userDetailsServiceBean();
//    }
//
//    @Override
//    protected UserDetailsService userDetailsService() {
//        return super.userDetailsService();
//    }
//
//    @Override
//    public void init(WebSecurity web) throws Exception {
//        super.init(web);
//    }
//
//    @Override
//    public void setApplicationContext(ApplicationContext context) {
//        super.setApplicationContext(context);
//    }
//
//    @Override
//    public void setTrustResolver(AuthenticationTrustResolver trustResolver) {
//        super.setTrustResolver(trustResolver);
//    }
//
//    @Override
//    public void setContentNegotationStrategy(ContentNegotiationStrategy contentNegotiationStrategy) {
//        super.setContentNegotationStrategy(contentNegotiationStrategy);
//    }
//
//    @Override
//    public void setObjectPostProcessor(ObjectPostProcessor<Object> objectPostProcessor) {
//        super.setObjectPostProcessor(objectPostProcessor);
//    }
//
//    @Override
//    public void setAuthenticationConfiguration(AuthenticationConfiguration authenticationConfiguration) {
//        super.setAuthenticationConfiguration(authenticationConfiguration);
//    }
}
