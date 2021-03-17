package org.xyz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.xyz.module.entity.WorkItemSearchItem;
import org.xxx.search.model.SearchQuery;
import org.xyz.module.service.WorkItemSearchService;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith( SpringRunner.class )
@SpringBootTest()
public class ApplicationTest {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WorkItemSearchService searchService;

    @Test
    public void simpleSearchTest() {
        List<SearchQuery> queries = Arrays.asList(
                new SearchQuery("workId", "like", "123"),
                new SearchQuery("mCode", "like", "10"),

                new SearchQuery("workId", "sort_desc", null),

                new SearchQuery("workId", "non_aggregation", null),
                new SearchQuery("mName", "non_aggregation", null),
                new SearchQuery("batchNumber", "non_aggregation", null),
                new SearchQuery("positionCode", "non_aggregation", null),
                new SearchQuery("planAmount", "non_aggregation", null)
        );

        Collection<WorkItemSearchItem> result = searchService.search(queries);
        logger.info(" {}", result);
    }

}
