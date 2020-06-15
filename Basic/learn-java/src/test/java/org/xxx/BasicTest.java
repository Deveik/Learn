package org.xxx;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Deveik
 */
public class BasicTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void conditionAssert() {
        logger.info("Exe {}", "1");
        Assert.assertEquals(1, 1);
    }
}
