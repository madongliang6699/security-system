package com.security.study.Security_JWT.security_jwt_2.filter;

import cn.hutool.core.util.ObjectUtil;
import com.security.study.Security_JWT.security_jwt_2.config.RsaKeyProperties;
import com.security.study.Security_JWT.security_jwt_2.constant.AuthWhiteList;
import com.security.study.Security_JWT.security_jwt_2.constant.ConstantKey;
import com.security.study.Security_JWT.security_jwt_2.entity.Payload;
import com.security.study.Security_JWT.security_jwt_2.entity.SysUser;
import com.security.study.Security_JWT.security_jwt_2.service.impl.GrantedAuthorityImpl;
import com.security.study.Security_JWT.security_jwt_2.utils.JwtUtils;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 自定义JWT认证过滤器
 * 该类继承自BasicAuthenticationFilter，在doFilterInternal方法中，
 * 从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 * 如果校验通过，就认为这是一个取得授权的合法请求
 *
 * @author zhaoxinguo on 2017/9/13.
 */
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    private RsaKeyProperties rsaKeyProperties;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, RsaKeyProperties rsaKeyProperties) {
        super(authenticationManager);
        this.rsaKeyProperties = rsaKeyProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            String requestURI = request.getRequestURI();
            String header = request.getHeader(ConstantKey.HEADER_KEY);
            if (ObjectUtil.isEmpty(header) || !header.startsWith(ConstantKey.BEARER)) {
                // 如果请求路径是放行路径，则直接跳过认证
                List<String> anonUrlList = Arrays.asList(AuthWhiteList.AUTH_WHITELIST);
                if (anonUrlList.contains(requestURI)) {
                    chain.doFilter(request, response);
                    return;
                } else {
                    throw new IllegalArgumentException("Token参数异常");
                }
//                chain.doFilter(request, response);
//                return;
            }

            // 如果token不为空，并且是以指定票据开头
//            if (ObjectUtil.isNotEmpty(header) && header.startsWith(ConstantKey.BEARER)) {
//                // 如果请求路径是放行路径，则直接跳过认证
//                List<String> anonUrlList = Arrays.asList(AuthWhiteList.AUTH_WHITELIST);
//                if (anonUrlList.contains(requestURI)) {
//                    chain.doFilter(request, response);
//                    return;
//                }
//            }
        } catch (IllegalArgumentException e) {
            // 异常捕获、发送到IllegalArgumentException
            request.setAttribute("illegalArgumentException", e);
            // 将异常分发到IllegalArgumentException控制器
            request.getRequestDispatcher("/illegalArgumentException").forward(request, response);
        }


        UsernamePasswordAuthenticationToken authentication = getAuthentication(request, response);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long start = System.currentTimeMillis();
            String token = request.getHeader(ConstantKey.HEADER_KEY);
            if (ObjectUtil.isEmpty(token)) {
                throw new IllegalArgumentException("Token不能为空!");
            }
            //Claims claims = Jwts.parser().setSigningKey(ConstantKey.SIGNING_KEY).parseClaimsJws(token.replace(ConstantKey.BEARER, "")).getBody();
            token = token.replace(ConstantKey.BEARER, "");
            Payload<SysUser> infoFromToken = JwtUtils.getInfoFromToken(token, rsaKeyProperties.getPublicKey(), SysUser.class);
            //能走到这里,说明token校验通过了.
            SysUser sysUser = infoFromToken.getUserInfo();
            long issuedAt = infoFromToken.getIssuedAt().getTime();// token签发时间
            long currentTimeMillis = System.currentTimeMillis();// 当前时间
            long expirationTime = infoFromToken.getExpiration().getTime();// token过期时间


            // region
            /**
             * todo 下面这段逻辑是做JWT token 续期的, 就是根据根据当前时间(本次请求的时间)重新生成一个token返回给前端,
             *  前端后续的请求使用最新的token, 这样就能做到每次请求都会刷新一下token的过期时间(实际上是通过生成新的token做到刷新时间的,并不是给原有的token刷新了时间).
             *
             *  不过下面这里, 针对续期的时机点做了判断,即:并不是每次请求都立马生成新的token,而是看看当前token是不是快过期了再决定是否生成新token,
             *  下面的判断的大致逻辑就是: 如果token的过期时间如果只剩一半时间了,就生成新token给前端刷新过期时间. 比如过期时间是1个小时,那就再半个小时内的请求不生成新token,半小时后的请求刷新token给前端.
             *  这样是减少生成token的频率,但是这样也有问题: 如果某一次请求正好是在过期时间的一半的前面一点(半小时时间点的前面一点), 导致这次请求没有生成新token, 下次请求又正好是在刚过了有效期的时间点,
             *  就会报token已经过期让重新登录,但是这两次请求的时间间隔只有半个小时多一点,导致用户认为过期时间只有半个小时.
             *  所以还是每次请求都生成一个新token比较稳妥.
             */
            // 1. 签发时间 < 当前时间 < (签发时间+((token过期时间-token签发时间)/2)) 不刷新token
            // 2. (签发时间+((token过期时间-token签发时间)/2)) < 当前时间 < token过期时间 刷新token并返回给前端
            // 3. tokne过期时间 < 当前时间 跳转登录，重新登录获取token
            // 验证token时间有效性
            if ((issuedAt + ((expirationTime - issuedAt) / 2)) < currentTimeMillis && currentTimeMillis < expirationTime) {
                // 重新生成token
                String refreshToken = JwtUtils.generateTokenExpireInMinutes(sysUser, rsaKeyProperties.getPrivateKey(), 24 * 60);
                // 主动刷新token，并返回给前端
                response.addHeader("refreshToken", refreshToken);
            }
            // endregion



            long end = System.currentTimeMillis();
            logger.info("执行时间: {}", (end - start) + " 毫秒");
            return new UsernamePasswordAuthenticationToken(sysUser, null, sysUser.getAuthorities());
        } catch (ExpiredJwtException e) {
            // 异常捕获、发送到ExpiredJwtException
            request.setAttribute("expiredJwtException", e);
            // 将异常分发到ExpiredJwtException控制器
            request.getRequestDispatcher("/expiredJwtException").forward(request, response);
        } catch (UnsupportedJwtException e) {
            // 异常捕获、发送到UnsupportedJwtException
            request.setAttribute("unsupportedJwtException", e);
            // 将异常分发到UnsupportedJwtException控制器
            request.getRequestDispatcher("/unsupportedJwtException").forward(request, response);
        } catch (MalformedJwtException e) {
            // 异常捕获、发送到MalformedJwtException
            request.setAttribute("malformedJwtException", e);
            // 将异常分发到MalformedJwtException控制器
            request.getRequestDispatcher("/malformedJwtException").forward(request, response);
        } catch (SignatureException e) {
            // 异常捕获、发送到SignatureException
            request.setAttribute("signatureException", e);
            // 将异常分发到SignatureException控制器
            request.getRequestDispatcher("/signatureException").forward(request, response);
        } catch (IllegalArgumentException e) {
            // 异常捕获、发送到IllegalArgumentException
            request.setAttribute("illegalArgumentException", e);
            // 将异常分发到IllegalArgumentException控制器
            request.getRequestDispatcher("/illegalArgumentException").forward(request, response);
        }
        return null;
    }

}