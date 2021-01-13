package org.xxx.modules.base.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.xxx.modules.base.dao.UserDao;
import org.xxx.modules.base.entity.User;

/**
 * @author Deveik
 */
@Service
public class UserService extends ServiceImpl<UserDao, User> {

}
