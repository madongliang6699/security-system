package com.security.study.Security_JWT.security_jwt_1.domain.mapper;

import com.security.study.Security_JWT.security_jwt_1.domain.entity.MyUser;
import com.security.study.Security_JWT.security_jwt_1.domain.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * 这里只是模拟，不写xml文件了
 */
@Mapper
public interface UserDao {
    //根据⽤户名查询⽤户
    default MyUser selectUserByUsername(String username) {
        return new MyUser() {{
            setUsername("aaa");
            setPassword("{noop}111");
//            setPassword("{bcrypt}$2a$10$aIkHBzVaWXci.qCvPnVYBecXK3XD7bvS2lD2uCX6R4GDZzcHEdsYO");//指定使用BCrypt加密之后，密码就要写成这样了
            setId(1);
            setEnabled(true);
            setAccountNonLocked(true);
            setCredentialsNonExpired(true);
            setAccountNonExpired(true);

        }};
    }

    //根据⽤户id查询⻆⾊
    default List<Role> getRolesByUid(Integer uid) {
        return new ArrayList() {{
            add(new Role() {{
                setId(1);
                setName("user");
                setNameZh("普通用户");
            }});
            add(new Role() {{
                setId(1);
                setName("admin");
                setNameZh("管理员");
            }});
        }};
    }

    //模拟更新数据库密码
    default Integer updatePassword(@Param("username") String username, @Param("password") String password) {

        //测试密码是否改了
        System.out.println("------- username: " + username + ", password: " + password);

        return 1;
    }
}
