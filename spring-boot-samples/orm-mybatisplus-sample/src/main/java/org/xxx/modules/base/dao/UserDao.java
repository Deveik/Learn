package org.xxx.modules.base.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.xxx.modules.base.entity.User;

/**
 * @author Deveik
 */
@Repository
public interface UserDao extends BaseMapper<User> {

    /**
     * update
     * @param user 用户
     * @return affected row
     */
    @Update( "update user set id = #{id}, name = #{name}, age = #{age}, " +
             "email = #{email}, version = #{version} + 1 where id = #{id} and version = #{version}" )
    int originMybatisUpdate(User user);

    @Update( "update user set name = #{name} where id = #{id}")
    int updateName(String name, int id);

//    @Update( "update user set name = #{name}, age = #{age} where id = #{id}")
//    int updateName(String name, int age, int id);

}
