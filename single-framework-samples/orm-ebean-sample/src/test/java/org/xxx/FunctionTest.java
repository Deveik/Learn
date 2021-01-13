package org.xxx;

import io.ebean.DB;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xxx.entity.base.Customer;

public class FunctionTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void basicTest() {
        Customer customer = new Customer();
        customer.setName("Hello world");

        // insert the customer in the DB
        DB.save(customer);

        // Find by Id
        Customer foundHello = DB.find(Customer.class, 1);

        logger.info(" {}", foundHello);
        // delete the customer
        DB.delete(customer);
    }
}
