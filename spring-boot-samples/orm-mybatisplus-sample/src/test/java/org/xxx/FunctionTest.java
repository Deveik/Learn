package org.xxx;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.xxx.modules.base.dao.UserDao;
import org.xxx.modules.base.entity.User;

import java.util.List;

@RunWith( SpringRunner.class )
@SpringBootTest
public class FunctionTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired UserDao userDao;

    @Test
    public void selectTest() {
        List<User> userList = userDao.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    public void updateWithLock() {
        User user = userDao.selectById(1);
        logger.info(" {}", user);
        user.setName("JoneWick");
        userDao.updateById(user);
        logger.info(" {}", user);

        User updatedUser = userDao.selectById(1);
        logger.info(" {}", updatedUser);
    }

    @Test
    public void updateWithOriginLock() {
        User user = userDao.selectById(1);
        logger.info(" {}", user);
        user.setName("JoneWick");
        userDao.originMybatisUpdate(user);
        logger.info(" {}", user);

        User updatedUser = userDao.selectById(1);
        logger.info(" {}", updatedUser);
    }
}
