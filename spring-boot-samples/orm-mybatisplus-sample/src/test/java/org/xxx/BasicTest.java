package org.xxx;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void simpleTest() {
        logger.info("ss");
    }
}
