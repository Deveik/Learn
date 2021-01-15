package org.xxx.modules.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Deveik
 */
@Service
public class UserService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public String logSomething() throws InterruptedException {
        Thread.sleep(12000);
        logger.info("空白区域");
        return "123";
    }

}
