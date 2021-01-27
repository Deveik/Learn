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
}
