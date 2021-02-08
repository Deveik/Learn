package org.xxx;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.Data;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

public class HutoolJsonTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void innerJsonObject() {
        JSONObject obj = JSONUtil.createObj();
        obj.setDateFormat("yyyy-MM-dd HH:mm:ss");
        obj.set("a", "a");
        obj.set("date", LocalDate.now());

        JSONObject innerObj = JSONUtil.createObj();
        innerObj.set("d", "ss");

        JSONArray array = JSONUtil.createArray();
        for (int i = 0; i < 10; i++) {
            JSONObject o1 = new JSONObject();
            o1.set("index", i);
            array.add(o1);
        }
        obj.set("inner", innerObj);
        obj.set("array", array);

        logger.info("{}", obj.toStringPretty());
    }

    @Data
    class TestObj {
        String a;
        LocalDate date;

        InnerObj inner;
        List<InnerItemObj> array;
    }

    @Data
    class InnerObj {
        String d;
    }

    @Data
    class InnerItemObj {
        Integer index;
    }

    @Test
    public void innerJsonObjectParse() {
        String parsedStr = "{\"date\":\"2021-01-26 00:00:00\",\"a\":\"a\",\"inner\":{\"d\":\"ss\"}," +
                "\"array\":[{\"index\":0},{\"index\":1},{\"index\":2},{\"index\":3},{\"index\":4},{\"index\":5}," +
                "{\"index\":6},{\"index\":7},{\"index\":8},{\"index\":9}]}";
        JSONObject obj = JSONUtil.parseObj(parsedStr);
        TestObj testObj = obj.toBean(TestObj.class);
        logger.info(" {}", testObj);
    }

    @Test
    public void innerJsonArray() {
        String parsedStr = "{\n" +
                "    \"code\": 0,\n" +
                "    \"data\": {\n" +
                "        \"totalCount\": 39,\n" +
                "        \"itemCount\": 39,\n" +
                "        \"items\": [\n" +
                "            {\n" +
                "                \"accId\": 800049746,\n" +
                "                \"cashBal\": 0,\n" +
                "                \"cashLockBal\": 0,\n" +
                "                \"cashCost\": 0,\n" +
                "                \"rebateBal\": 0,\n" +
                "                \"rebateCost\": 0,\n" +
                "                \"virBal\": 0,\n" +
                "                \"virCost\": 0,\n" +
                "                \"accDayBudget\": 0,\n" +
                "                \"totalBalance\": 0,\n" +
                "                \"lastDayCost\": 0,\n" +
                "                \"ownerId\": 1100049873,\n" +
                "                \"ownerName\": \"LX1612413477\",\n" +
                "                \"ownerType\": 1,\n" +
                "                \"auditStatus\": 1,\n" +
                "                \"insertTime\": 1612413479,\n" +
                "                \"updateTime\": 1612413479,\n" +
                "                \"openId\": \"1612413477\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"accId\": 800049660,\n" +
                "                \"cashBal\": 0,\n" +
                "                \"cashLockBal\": 0,\n" +
                "                \"cashCost\": 0,\n" +
                "                \"rebateBal\": 0,\n" +
                "                \"rebateCost\": 0,\n" +
                "                \"virBal\": 0,\n" +
                "                \"virCost\": 0,\n" +
                "                \"accDayBudget\": 0,\n" +
                "                \"totalBalance\": 0,\n" +
                "                \"lastDayCost\": 0,\n" +
                "                \"ownerId\": 1100049787,\n" +
                "                \"ownerName\": \"LX1612410393\",\n" +
                "                \"ownerType\": 1,\n" +
                "                \"auditStatus\": 1,\n" +
                "                \"insertTime\": 1612410394,\n" +
                "                \"updateTime\": 1612410394,\n" +
                "                \"openId\": \"1612410393\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";
        JSONObject object = JSONUtil.parseObj(parsedStr);
        Object byPath = JSONUtil.getByPath(object, "data.items");
        JSONArray data = object.getJSONObject("data").getJSONArray("items");
        System.out.println(data);
        System.out.println(byPath);
    }
}
