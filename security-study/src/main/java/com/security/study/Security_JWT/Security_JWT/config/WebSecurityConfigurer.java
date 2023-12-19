package com.security.study.Security_JWT.Security_JWT.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.study.Security_JWT.Security_JWT.filter.LoginKaptchaFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static com.security.study.Security_JWT.Security_JWT.filter.LoginKaptchaFilter.VERIFICATION_CODE;

/**
 * ⾃定义资源权限规则，请求拦截规则
 */
@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Resource
    UserDetailsService myUserDetailService;

    //region =====使用security框架的总配置=========
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * 注意：只要重写了这个方法，security默认给我们配置的那些规则就失效了，因为重写了之后就等于把父类里写好的规则给替换了，
         * 所以 所有的拦截规则就需要这里明确的配置好。比如哪些请求需要拦截保护等。
         * 如果这个方法里面没有配置认证拦截规则，那所有接口都不会拦截保护了。
         */

        http.authorizeRequests()
                .mvcMatchers("/vc.png").permitAll()//验证码接口放行
                .mvcMatchers("/login.html").permitAll() //登录页面放行
                .mvcMatchers("/index").permitAll() //permitAll() 代表放⾏该资源,该资源为公共资源 ⽆需认证和授权可以直接访问。todo 注意: 放⾏资源必须放在所有认证请求之前!
                .anyRequest().authenticated() //anyRequest().authenticated() 代表所有请求,必须认证之后才能访问
                .and()
//                .formLogin() //代表开启表单认证。怎么样设置是不开启？如果不开启就不需要登录了吗? （很多认证的逻辑可以进这个方法里断点源码查看）
//                .loginPage("/mylogin.html") //登录页面地址，可以写一个自己的登录页面。（注意：这个页面就是检测到如果没登录就跳转登录的地址，所以上面一定要给这个地址放行拦截，要不然就循环跳转了。【当然现在测试的，没有实际写这个页面，就先注释掉，用默认的登录页】
//                .loginProcessingUrl("/doLogin") //指定登录请求的url，点击登录按钮就请求这个按钮，与前端保持一致。前端登录表单 method 必须为 post
//                .usernameParameter("myname") //指定用户名的字段名，与前端保持一致
//                .passwordParameter("mypasswd") //指定密码的字段名，与前端保持一致
//                .successForwardUrl("/index") //forward 转发，登录成功后直接跳转到这个指定路径
//                .defaultSuccessUrl("/index" /*, true*/) //redirect 重定向，如果之前有请求路径, 会有优先跳转之前请求路径。否则跳转到这个指定的路径。如果设置了第二个参数为true，就每次都只跳转这个指定的地址。所以不传就是默认的false。
//                .successHandler(new MyAuthenticationSuccessHandler()) //如果是前后端分离的项目，登录成功后，是不需要后端跳转地址的，只返回一个成功的json数据就行了，那就需要配置这个参数，上面的两个参数就不需要了。
//                .failureForwardUrl("/xxx")//失败以后的转发跳转。因此如果页面想获取 request 中异常信息进行展示,这⾥只能使⽤failureForwardUrl（知识点：如果登录失败，security把异常信息放在了request（转发的情况下存在了request中）和session（重定向情况下）作用域中了，key是“SPRING_SECURITY_LAST_EXCEPTION”，源码可以参考SimpleUrlAuthenticationFailureHandler类）
//                .failureUrl("/login.html") //认证失败后跳转的路径, 失败以后的重定向跳转
//                .failureHandler(new MyAuthenticationFailureHandler())//同上面的successHandler参数
//                .and()
                .exceptionHandling()
                .authenticationEntryPoint((req, resp, ex) -> { //前后端分离的项目拦截请求之后不应该跳转到某个页面，应该给前端返回一个提示认证的json数据就行了，可以这样设置。
                    Map<String, Object> result = new HashMap<String, Object>();
                    result.put("msg", "必须认证之后才能访问, " + ex.getMessage());
                    String s = new ObjectMapper().writeValueAsString(result);
                    resp.setContentType("application/json;charset=UTF-8");
                    resp.setStatus(HttpStatus.UNAUTHORIZED.value());
                    resp.getWriter().println(s);
                })
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
                .csrf().disable()//这⾥先关闭 CSRF
                .sessionManagement() //开启会话管理，todo 注意，会话管理起作用的前提是User用户类的username字段重写了 equals hashCode 方法。
                .maximumSessions(1) //允许同⼀个⽤户只允许创建⼀个会话,也就是只能在一个客户端登录
                //.expiredUrl("/login")//会话过期处理 传统 web 开发
                .expiredSessionStrategy(event -> {
                    HttpServletResponse response = event.getResponse();
                    response.setContentType("application/json;charset=UTF-8");
                    Map<String, Object> result = new HashMap();
                    result.put("status", 500);
                    result.put("msg", "当前会话已经失效,请重新登录!");
                    String s = new ObjectMapper().writeValueAsString(result);
                    response.getWriter().println(s);
                    response.flushBuffer();
                })//前后端分离开发处理
                .maxSessionsPreventsLogin(true)//true：登录之后禁⽌再次在其他客户端登录; 如果这里不指定或者设置false，就是后登录的挤掉前面登录的
        ;

        //添加验证码过滤器
        http.addFilterAfter(loginKaptchaFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    //endregion =====使用security框架的总配置===================


    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }


    //region ===== 自定义认证数据源 和 密码加密方式指定 ===============

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
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        //这里替换了默认的UserDetailsService实现。把默认用户改成了haha。
        // 其实这个方法不用写也行，security会默认先在项目中找UserDetailsService
        //的实现，只要下面的userDetailsService()自定义了UserDetailsService 的bean就行。或其他地方已经自定义实现UserDetailsService

        //auth.userDetailsService(hahaUserDetailsService());//如果有多出实现了UserDetailsService接口，那就要在这里指定一下使用哪个。
        auth.userDetailsService(myUserDetailService);     //如果有多出实现了UserDetailsService接口，那就要在这里指定一下使用哪个。
    }


    //⾃定义内存数据源，把默认用户改成haha。
    @Bean
    public UserDetailsService hahaUserDetailsService() {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();

        /**如下面的配置默认用户，security给密码加密的方式是很灵活的，可以使用明文，可以使用MD5，可以使用推荐的bcrypt，可以随时变动加密方式，
         * 因为他保存密码的方式是前面带个大括号，大括号里指定加密的方式，即：{加密方式标记}， 这样的方式只要指定加密方式，security就按指定的方式去校验密码，
         * 这个设计应该是能比较灵活的兼容很多老系统的不同加密方式的密码适配security框架。
         * 同理在数据库中保存的密码，也是这样存储的，使用这种带大括号的前缀。
         *
         * 如果就是一个新项目，就是想固定使用bcrypt的加密方式，根据源码，只需要给spring容器中注册一个指定的加密方式对象就行了。因为security是
         * 优先查看容器中有没有 PasswordEncoder 这个密码加密接口的实现对象的，如果有就使用已有的，如果没有才使用上面说的大括号灵活匹配的方式（灵活匹配默认的也是bcrypt）。
         */
        //UserDetails u1 = User.withUsername("haha").password("{noop}111").roles("USER").build();//{noop}是指明文存储的密码
        UserDetails u1 = User.withUsername("haha").password("{bcrypt}$2a$10$g8JKr1T0J2BF7lkyftZqy.rD0yLbyOr6p.xw6F5bRkUdQJlKMk2WW").roles("USER").build();//{bcrypt}是指bcrypt加密方式存储的密码

        inMemoryUserDetailsManager.createUser(u1);
        return inMemoryUserDetailsManager;
    }


    //指定固定的密码加密方式，使用 bcrypt方式。只要这里给容器中注册了加密对象bean，security发现已经有了加密方式对象，就优先使用指定的这个。如果没有指定，它再使用默认的逻辑去加解密。
    //注意，一旦这里指定了固定的加密方式，上面的密码就不能带 {加密方式标识} 的前缀了，security给我们保存到数据库的时候也不会加大括号了。
    //todo 有的老师建议使用上面那种带大括号的灵活的方式存储，因为security可以帮我们自带修改密码，这样，当security升级本版后，使用了一个新的更安全加密方式，
    // 我们就不需要修改代码去适配新加密方式，并且security还可以帮我们自动替换掉老的加密（只要实现了 UserDetailsPasswordService 的修改密码的方法，每次登录认证之后都会帮我们重新加密并保存到数据库），
    // 做到密码自动升级的效果。
    // 但是另外一个老师说带前缀的密码存储太麻烦，或者不适用其他框架吧，就推荐使用固定的加密方式，就向下面这里指定固定的加密方式保存数据库。
//    @Bean
    public PasswordEncoder BcryptPasswordEncoder() {
        //BCrypt是每次都随机加盐的，同一个密码每次生成的密文都不一样，
        return new BCryptPasswordEncoder();
    }

    //endregion ===== 自定义认证数据源 和 密码加密方式指定 ========================


    //region ===== 配置 登录验证码功能 的过滤器 ========================

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 注册验证码过滤器bean。既然登陆需要验证码，那使用默认表单登录（.formLogin()）就不适用了，需要加传验证码的参数给后端。
     * 可以使用postman发送登录请求。
     */
    @Bean
    public LoginKaptchaFilter loginKaptchaFilter() throws Exception {
        LoginKaptchaFilter loginKaptchaFilter = new LoginKaptchaFilter();
        //1.认证 url
        loginKaptchaFilter.setFilterProcessesUrl("/doLogin");
        //2.认证 接收参数
        loginKaptchaFilter.setUsernameParameter("myname");//可以指定参数名，不指定就用默认的
        loginKaptchaFilter.setPasswordParameter("mypasswd");
        //loginKaptchaFilter.setKaptchaParameter("verificationCode");
        //3.指定认证管理器
        loginKaptchaFilter.setAuthenticationManager(authenticationManagerBean());
        //4.指定成功时处理
        loginKaptchaFilter.setAuthenticationSuccessHandler((req, resp, authentication) -> {
            //把验证码失效
            req.getSession().removeAttribute(VERIFICATION_CODE);

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
