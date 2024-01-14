package com.security.study.Security_JWT.security_jwt_2.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义身份认证验证组件
 *
 * @author zhaoxinguo on 2017/9/12.
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomAuthenticationProvider(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

  /**
    *执行与以下合同相同的身份验证
    * {@link org.springframework.security.authentication.AuthenticationManager＃authenticate（Authentication）}
    *。
    *
    * @param authentication 身份验证请求对象。
    *
    * @返回包含凭证的经过完全认证的对象。 可能会回来
    * <code> null </ code>（如果<code> AuthenticationProvider </ code>无法支持）
    * 对传递的<code> Authentication </ code>对象的身份验证。 在这种情况下，
    * 支持所提供的下一个<code> AuthenticationProvider </ code>
    * 将尝试<code> Authentication </ code>类。
    *
    * @throws AuthenticationException 如果身份验证失败。
    */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //这里也可以做验证码校验
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String verificationCode = req.getParameter("verificationCode");//获取前端给的验证码参数
        //String verify_code = (String) req.getSession().getAttribute("verify_code");
        //String verify_code = redisClient.get("verify_code");//获取redis中的验证码
        //if (code == null || verify_code == null || !code.equals(verify_code)) {
        //    throw new AuthenticationServiceException("验证码错误");
        //}



        // 用户名和密码校验
        //获取前端参数中的用户名和密码
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        //获取数据库中对应用户名的用户信息
        UserDetails userDetails = userDetailsService.loadUserByUsername(name);
        if (null == userDetails) {
            throw new UsernameNotFoundException("用户不存在!");
        }
        if (bCryptPasswordEncoder.matches(password, userDetails.getPassword())) {
            // 这里设置权限和角色
            //ArrayList<GrantedAuthority> authorities = new ArrayList<>();
            //authorities.add( new GrantedAuthorityImpl("ROLE_ADMIN"));
            //authorities.add( new GrantedAuthorityImpl("AUTH_WRITE"));
            ArrayList<GrantedAuthority> authorities1 = (ArrayList<GrantedAuthority>)userDetails.getAuthorities();
            // 生成令牌 这里令牌里面存入了:name,password,authorities, 当然你也可以放其他内容
            Authentication auth = new UsernamePasswordAuthenticationToken(name, password, authorities1);
            return auth;
        } else {
            throw new BadCredentialsException("密码错误!");
        }
    }

    /**
     * 是否可以提供输入类型的认证服务
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
