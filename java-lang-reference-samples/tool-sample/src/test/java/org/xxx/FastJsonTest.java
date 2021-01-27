package org.xxx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class FastJsonTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void parseObject() {
        String parsedStr = "{\"date\":\"2021-01-26 00:00:00\",\"a\":\"a\",\"inner\":{\"d\":\"ss\"}," +
                "\"array\":[{\"index\":0},{\"index\":1},{\"index\":2},{\"index\":3},{\"index\":4},{\"index\":5}," +
                "{\"index\":6},{\"index\":7},{\"index\":8},{\"index\":9}]}";
        JSONObject parseObject = JSON.parseObject(parsedStr);
        Date date = parseObject.getDate("date");
        String a = parseObject.getString("a");
        JSONObject inner = parseObject.getJSONObject("inner");
        String d = inner.getString("d");
        logger.info("{} {} {}", date, a, d);
        JSONArray array = parseObject.getJSONArray("array");
        for (int i = 0; i < array.size(); i++) {
            JSONObject o = array.getJSONObject(i);
            logger.info("{}", o.getString("index"));
        }

    }
}
