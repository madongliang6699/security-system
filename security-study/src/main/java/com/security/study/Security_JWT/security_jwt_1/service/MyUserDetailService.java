package com.security.study.Security_JWT.security_jwt_1.service;

import com.security.study.Security_JWT.security_jwt_1.domain.entity.MyUser;
import com.security.study.Security_JWT.security_jwt_1.domain.mapper.UserDao;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 这里实现pring security的UserDetailsService接口，自动替换默认的认证数据实现类。
 * 进而实现认证数据源从我们数据库里查询。
 *
 * 实现UserDetailsPasswordService接口可以做到自动升级密码。
 */
@Service
public class MyUserDetailService implements UserDetailsService, UserDetailsPasswordService {

    @Resource
    private UserDao userDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = userDao.selectUserByUsername(username);
        if (ObjectUtils.isEmpty(user)) {
            throw new RuntimeException("⽤户不存在");
        }
        user.setRoles(userDao.getRolesByUid(user.getId()));
        return user;
    }

    /**
     * 这个方法是可以做到自动升级密码的。通过这里去操作数据库修改密码。
     * <p>
     *     什么意思呢：就是如果你原来的库中的密码是{noop}111这种加密方式的，但是升级security版本之后默认的密码是bcrypt了，
     *     那security比对现有数据中中密码加密方式{noop}和现在默认使用的{bcrypt}是不同的方式，那就会自动调用这个方法，做密码的升级，把旧密码换成
     *     重新生成的新加密方式的密码。
     *     也就是这个方法可以升级密码，前提是：使用了默认的加密方式，而不是指定了固定的加密方式，并且通过版本升级导致加密的方式换了，就会调用这个方法。
     *     如果库中已经存在的密码加密方式（{xxx}标记的）和当前security框架中的加密方式相同，是不会每次登录都调用这个方法的。
     * <p/>
     *
     * @param user        the user to modify the password for
     * @param newPassword the password to change to,
     *                    encoded by the configured {@code PasswordEncoder}
     * @return
     */
    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        Integer result = userDao.updatePassword(user.getUsername(), newPassword);
        if (result == 1) {
            ((MyUser) user).setPassword(newPassword);
        }
        return user;
    }
}
