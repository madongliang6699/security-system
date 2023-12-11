package com.security.study.Security_JWT.Security_JWT.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.study.Security_JWT.Security_JWT.filter.LoginKaptchaFilter;
import com.security.study.Security_JWT.Security_JWT.service.MyUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * ⾃定义资源权限规则，请求拦截规则
 */
@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    //region =====使用security框架的总配置=========
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * 注意：只要重写了这个方法，security默认给我们配置的那些规则就失效了，因为重写了之后就等于把父类里写好的规则给替换了，
         * 所以 所有的拦截规则就需要这里明确的配置好。比如哪些请求需要拦截保护等。
         * 如果这个方法里面没有配置认证拦截规则，那所有接口都不会拦截保护了。
         */

        http.authorizeRequests()
                .mvcMatchers("/vc.jpg").permitAll()//验证码接口放行
                .mvcMatchers("/login.html").permitAll() //登录页面放行
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
                                new AntPathRequestMatcher("/logout1", "GET"),
                                new AntPathRequestMatcher("/logout", "GET")
                        )
                )
//                .logoutUrl("/logout")// 指定退出登录请求地址，默认是 GET 请求，路径为 /logout。前后端分离的就不需要了
                .invalidateHttpSession(true)//退出时是否是 session 失效，默认值为 true
                .clearAuthentication(true)//退出时是否清除认证信息，默认值为 true
//                .logoutSuccessUrl("/login.html")//退出登录时跳转地址。前后端分离的就不需要了
                .logoutSuccessHandler(new MyLogoutSuccessHandler()) //前后端分离情况下，给返回一个json数据
                .and()
                .exceptionHandling()//这个是干嘛的，启动异常处理吗
                .authenticationEntryPoint((req, resp, ex) -> {
                    resp.setContentType("application/json;charset=UTF-8");
                    resp.setStatus(HttpStatus.UNAUTHORIZED.value());
                    resp.getWriter().println(" must Authentication 必须认证之后才能访问");
                })
                .and()
                .csrf().disable(); //这⾥先关闭 CSRF
    }

    //endregion =====使用security框架的总配置===================


    //region ===== 自定义认证数据源 ===============

    /**
     * 总结: AuthenticationManager 是认证管理器，在 Spring Security 中有全局
     * AuthenticationManager，也可以有局部AuthenticationManager。全局的
     * AuthenticationManager⽤来对全局认证进⾏处理，局部的AuthenticationManager⽤
     * 来对某些特殊资源认证处理。当然⽆论是全局认证管理器还是局部认证管理器都是由
     * ProviderManger 进⾏实现。 每⼀个ProviderManger中都代理⼀个
     * AuthenticationProvider的列表，列表中每⼀个实现代表⼀种身份认证⽅式。认证时底
     * 层数据源需要调⽤ UserDetailService 来实现。 默认⾃动配置创建全局AuthenticationManager 默认找当前项⽬中是否存在⾃
     * 定义 UserDetailService 实例 ⾃动将当前项⽬ UserDetailService 实例
     * 设置为数据源。下面这个方法是用来⾃定义全局 AuthenticationManager。
     * <p>
     * 1、⼀旦通过 configure ⽅法⾃定义 AuthenticationManager实现 ，
     * 就会将⼯⼚中⾃动配置AuthenticationManager 进⾏覆盖；
     * 2、⼀旦通过 configure ⽅法⾃定义 AuthenticationManager实现 需要在实现
     * 中指定认证数据源对象 UserDetaiService 实例；
     * 3、⼀旦通过 configure ⽅法⾃定义 AuthenticationManager实现 这种⽅式创
     * 建AuthenticationManager对象⼯⼚内部本地⼀个 AuthenticationManager
     * 对象 不允许在其他⾃定义组件中进⾏注⼊。
     */
//    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        //这里替换了默认的UserDetailsService实现。把默认用户改成了haha。
        // 其实这个方法不用写也行，security会默认先在项目中找UserDetailsService
        //的实现，只要下面的userDetailsService()自定义了UserDetailsService 的bean就行。或其他地方已经自定义实现UserDetailsService
//        auth.userDetailsService(userDetailsService());
        auth.userDetailsService(myUserDetailService);//如果有多出实现了UserDetailsService接口，那就要在这里指定一下使用哪个。
    }

    @Resource
    UserDetailsService myUserDetailService;


    //⾃定义内存数据源，把默认用户改成haha。
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        UserDetails u1 = User.withUsername("haha").password("{noop}111").roles("USER").build();
        inMemoryUserDetailsManager.createUser(u1);
        return inMemoryUserDetailsManager;
    }


    //endregion ===== 自定义认证数据源 ========================


    //region ===== 配置 登录验证码功能 的过滤器 ========================

    @Bean
    public LoginKaptchaFilter loginKaptchaFilter() throws Exception {
        LoginKaptchaFilter loginKaptchaFilter = new LoginKaptchaFilter();
        //1.认证 url
        loginKaptchaFilter.setFilterProcessesUrl("/doLogin");
        //2.认证 接收参数
        loginKaptchaFilter.setUsernameParameter("uname");
        loginKaptchaFilter.setPasswordParameter("passwd");
        loginKaptchaFilter.setKaptchaParameter("kaptcha");
        //3.指定认证管理器
        loginKaptchaFilter.setAuthenticationManager(authenticationManagerBean());
        //4.指定成功时处理
        loginKaptchaFilter.setAuthenticationSuccessHandler((req, resp, authentication) -> {
            Map<String, Object> result = new HashMap();
            result.put("msg", "登录成功");
            result.put("⽤户信息", authentication.getPrincipal());
            resp.setContentType("application/json;charset=UTF-8");
            resp.setStatus(HttpStatus.OK.value());
            String s = new ObjectMapper().writeValueAsString(result);
            resp.getWriter().println(s);
        });
        //5.认证失败处理
        loginKaptchaFilter.setAuthenticationFailureHandler((req, resp, ex) -> {
            Map<String, Object> result = new HashMap();
            result.put("msg", "登录失败: " + ex.getMessage());
            resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setContentType("application/json;charset=UTF-8");
            String s = new ObjectMapper().writeValueAsString(result);
            resp.getWriter().println(s);
        });
        return loginKaptchaFilter;
    }
    //endregion ===== 配置验证码功能 ========================
}
