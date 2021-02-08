package org.xxx;

import org.apache.ibatis.reflection.ParamNameUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xxx.modules.base.dao.UserDao;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Deveik
 */
public class FeatureTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void parameterUtilsTest() {
        Method[] methods = UserDao.class.getDeclaredMethods();
        for (Method method : methods) {
            List<String> paramNames = ParamNameUtil.getParamNames(method);
            logger.info(String.join(",", paramNames));
        }
    }
}
