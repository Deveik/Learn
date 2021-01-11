package org.xxx;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

public class ESTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void simpleTest() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("127.0.0.1", 9200, "http")));

        try {
//             indexRequest(client);
            getRequest(client);
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void indexRequest(RestHighLevelClient client) throws IOException {
        IndexRequest indexRequest = new IndexRequest("posts")
                .id("1")
                .source("user", "kimchy",
                        "postDate", new Date(),
                        "message", "trying out Elasticsearch");
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        logger.info(" {}", indexRequest);
    }

    private void getRequest(RestHighLevelClient client) throws IOException {
        GetRequest getRequest = new GetRequest(
                "posts",
                "1");
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);

        String index = getResponse.getIndex();
        String id = getResponse.getId();
        logger.info("{} {}", id, index);
        if (getResponse.isExists()) {
            long version = getResponse.getVersion();
            String sourceAsString = getResponse.getSourceAsString();
            Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
            byte[] sourceAsBytes = getResponse.getSourceAsBytes();

            sourceAsMap.forEach((k, v) -> {
                logger.info("{} {} {}", version, k, v);
            });
        } else {
            logger.error("错误信息");
        }
    }

    private void betaGetRequest() {

    }
}
