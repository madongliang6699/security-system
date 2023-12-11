package com.security.study.Security_JWT.Security_JWT.domain.mapper;

import com.security.study.Security_JWT.Security_JWT.domain.entity.MyUser;
import com.security.study.Security_JWT.Security_JWT.domain.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 这里只是模拟，不写xml文件了
 */
@Mapper
public interface UserDao {
    //根据⽤户名查询⽤户
    default MyUser selectUserByUsername(String username) {
        return new MyUser(){{
            setUsername("aaa");
            setPassword("{noop}111");
            setId(1);
            setEnabled(true);
            setAccountNonLocked(true);
            setCredentialsNonExpired(true);
            setAccountNonExpired(true);

        }};
    }

    //根据⽤户id查询⻆⾊
    default List<Role> getRolesByUid(Integer uid){
        return new ArrayList(){{
            add(new Role(){{
                setId(1);
                setName("user");
                setNameZh("普通用户");
            }});
            add(new Role(){{
                setId(1);
                setName("admin");
                setNameZh("管理员");
            }});
        }};
    };
}
