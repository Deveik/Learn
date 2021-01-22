package org.xxx;

import io.ebean.DB;
import io.ebean.PagedList;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xxx.entity.base.Customer;
import org.xxx.entity.base.query.QCustomer;

import java.util.stream.IntStream;

public class FunctionTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before
    public void prepareData() {
        IntStream.range(0, 100)
                .forEach(i -> {
                    Customer customer = new Customer();
                    customer.setName("caa" + i);
                    DB.save(customer);
                });
    }

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

    @Test
    public void findPageTest() {
        PagedList<Customer> pagedList = new QCustomer()
                .name.like("%1%")
                .id.desc()
                .setFirstRow(2)
                .setMaxRows(5)
                .findPagedList();
        pagedList.loadCount();
        logger.info(" {} {} {}", pagedList.getPageSize(), pagedList.getTotalCount(), pagedList.getFutureCount());
        for (Customer customer : pagedList.getList()) {
            logger.info(" {}", customer);
        }
    }
}
