package org.xxx.modules.base.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;
import org.xxx.modules.base.entity.User;

/**
 * @author Deveik
 */
public interface UserDao extends BaseMapper<User> {

    /**
     * update
     * @param user 用户
     * @return affected row
     */
    @Update( "update user set id = #{id}, name = #{name}, age = #{age}, " +
             "email = #{email}, version = #{version} + 1 where id = #{id} and version = #{version}" )
    int originMybatisUpdate(User user);
}
