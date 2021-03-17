package org.xyz.module.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Deveik
 */
public class FieldTypeConstants {
    public static final String REPLACE_CODE = "REPLACE_CODE";
    public static final String REPLACE_USER = "REPLACE_USER";
    public static final String REPLACE_ID = "REPLACE_ID";
    public static final String DIVIDE_THOUSAND = "DIVIDE_THOUSAND";

    public static final Map<String, Integer> FILED_TYPE_MAP;
    static {
        FILED_TYPE_MAP = new HashMap<>();
        FILED_TYPE_MAP.put(REPLACE_CODE, 10001);
        FILED_TYPE_MAP.put(REPLACE_USER, 10002);
        FILED_TYPE_MAP.put(REPLACE_ID,   10003);
        FILED_TYPE_MAP.put(DIVIDE_THOUSAND, 10004);
    }
}
