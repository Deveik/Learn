package org.xxx.search.constants;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Deveik
 * 操作类
 * TODO: 可以把每个操作对应的描述都放在这里，现在仅仅只是安放对应的值
 */
public class OperationType {
    /**
     * 等于
     */
    public static final OperationType FILTER_EQ = new OperationType("eq");
    /**
     * 不等于
     */
    public static final OperationType FILTER_NE = new OperationType("ne");
    /**
     * 大于
     */
    public static final OperationType FILTER_GT = new OperationType("gt");
    /**
     * 大于等于
     */
    public static final OperationType FILTER_GE = new OperationType("ge");
    /**
     * 小于
     */
    public static final OperationType FILTER_LT = new OperationType("lt");
    /**
     * 小于等于
     */
    public static final OperationType FILTER_LE = new OperationType("le");
    /**
     * 在...之间
     */
    public static final OperationType FILTER_BETWEEN = new OperationType("between");
    /**
     * 不在...之间
     */
    public static final OperationType FILTER_NOT_BETWEEN = new OperationType("not_between");
    /**
     * 包含
     */
    public static final OperationType FILTER_LIKE = new OperationType("like");
    /**
     * 不包含
     */
    public static final OperationType FILTER_NOT_LIKE = new OperationType("not_like");
    /**
     * 左包含
     */
    public static final OperationType FILTER_LIKE_LEFT = new OperationType("left");
    /**
     * 右包含
     */
    public static final OperationType FILTER_LIKE_RIGHT = new OperationType("right");
    /**
     * 为空
     */
    public static final OperationType FILTER_IS_NULL = new OperationType("null");
    /**
     * 不为空
     */
    public static final OperationType FILTER_IS_NOT_NULL = new OperationType("not_null");
    /**
     * 在...之中
     */
    public static final OperationType FILTER_IN = new OperationType("in");
    /**
     * 不在...之中
     */
    public static final OperationType FILTER_NOT_IN = new OperationType("not_in");

    /**
     * 日期等于
     */
    public static final OperationType FILTER_DATE_EQ = new OperationType("date_eq");
    /**
     * 日期不等于
     */
    public static final OperationType FILTER_DATE_NE = new OperationType("date_ne");
    /**
     * 日期大于等于
     */
    public static final OperationType FILTER_DATE_GE = new OperationType("date_ge");
    /**
     * 日期小于等于
     */
    public static final OperationType FILTER_DATE_LE = new OperationType("date_le");
    /**
     * 日期在...之间
     */
    public static final OperationType FILTER_DATE_BETWEEN = new OperationType("date_between");
    /**
     * 日期不在...之间
     */
    public static final OperationType FILTER_DATE_NOT_BETWEEN = new OperationType("date_not_between");
    /**
     * 日期在...之中
     */
    public static final OperationType FILTER_DATE_IN = new OperationType("date_in");
    /**
     * 日期不在...之中
     */
    public static final OperationType FILTER_DATE_NOT_IN = new OperationType("date_not_in");

    /**
     * 日期时间等于
     */
    public static final OperationType FILTER_DATE_TIME_EQ = new OperationType("date_time_eq");
    /**
     * 日期时间不等于
     */
    public static final OperationType FILTER_DATE_TIME_NE = new OperationType("date_time_ne");
    /**
     * 日期时间大于等于
     */
    public static final OperationType FILTER_DATE_TIME_GE = new OperationType("date_time_ge");
    /**
     * 日期时间小于等于
     */
    public static final OperationType FILTER_DATE_TIME_LE = new OperationType("date_time_le");
    /**
     * 日期时间在...之间
     */
    public static final OperationType FILTER_DATE_TIME_BETWEEN = new OperationType("date_time_between");
    /**
     * 日期时间不在...之间
     */
    public static final OperationType FILTER_DATE_TIME_NOT_BETWEEN = new OperationType("date_time_not_between");
    /**
     * 日期时间在...之中
     */
    public static final OperationType FILTER_DATE_TIME_IN = new OperationType("date_time_in");
    /**
     * 日期时间不在...之中
     */
    public static final OperationType FILTER_DATE_TIME_NOT_IN = new OperationType("date_time_not_in");

    /**
     * 顺序
     */
    public static final OperationType SORT_ASC = new OperationType("sort_asc");
    /**
     * 倒序
     */
    public static final OperationType SORT_DESC= new OperationType("sort_desc");

    /**
     * 聚合
     */
    public static final OperationType AGGREGATION = new OperationType("aggregation");
    /**
     * 非聚合
     */
    public static final OperationType NON_AGGREGATION = new OperationType("non_aggregation");

    public static final Map<OperationType, String> OPERATION_TYPE_DESC_MAP = new HashMap<>();

    static {
        OPERATION_TYPE_DESC_MAP.put(FILTER_EQ, "等于");
        OPERATION_TYPE_DESC_MAP.put(FILTER_NE, "不等于");
        OPERATION_TYPE_DESC_MAP.put(FILTER_GT, "大于");
        OPERATION_TYPE_DESC_MAP.put(FILTER_GE, "大于等于");
        OPERATION_TYPE_DESC_MAP.put(FILTER_LT, "小于");
        OPERATION_TYPE_DESC_MAP.put(FILTER_LE, "小于等于");
        OPERATION_TYPE_DESC_MAP.put(FILTER_BETWEEN, "在...之间");
        OPERATION_TYPE_DESC_MAP.put(FILTER_NOT_BETWEEN, "不在...之间");
        OPERATION_TYPE_DESC_MAP.put(FILTER_LIKE, "包含");
        OPERATION_TYPE_DESC_MAP.put(FILTER_NOT_LIKE, "不包含");
        OPERATION_TYPE_DESC_MAP.put(FILTER_LIKE_LEFT, "左包含");
        OPERATION_TYPE_DESC_MAP.put(FILTER_LIKE_RIGHT, "右包含");
        OPERATION_TYPE_DESC_MAP.put(FILTER_IS_NULL, "为空");
        OPERATION_TYPE_DESC_MAP.put(FILTER_IS_NOT_NULL, "不为空");
        OPERATION_TYPE_DESC_MAP.put(FILTER_IN, "在...之中");
        OPERATION_TYPE_DESC_MAP.put(FILTER_NOT_IN, "不在...之中");

        OPERATION_TYPE_DESC_MAP.put(FILTER_DATE_EQ, "等于");
        OPERATION_TYPE_DESC_MAP.put(FILTER_DATE_NE, "不等于");
        OPERATION_TYPE_DESC_MAP.put(FILTER_DATE_GE, "大于等于");
        OPERATION_TYPE_DESC_MAP.put(FILTER_DATE_LE, "小于等于");
        OPERATION_TYPE_DESC_MAP.put(FILTER_DATE_BETWEEN, "在...之间");
        OPERATION_TYPE_DESC_MAP.put(FILTER_DATE_NOT_BETWEEN, "不在...之间");
        OPERATION_TYPE_DESC_MAP.put(FILTER_DATE_IN, "在...之中");
        OPERATION_TYPE_DESC_MAP.put(FILTER_DATE_NOT_IN, "不在...之中");

        OPERATION_TYPE_DESC_MAP.put(FILTER_DATE_TIME_EQ, "等于");
        OPERATION_TYPE_DESC_MAP.put(FILTER_DATE_TIME_NE, "不等于");
        OPERATION_TYPE_DESC_MAP.put(FILTER_DATE_TIME_GE, "大于等于");
        OPERATION_TYPE_DESC_MAP.put(FILTER_DATE_TIME_LE, "小于等于");
        OPERATION_TYPE_DESC_MAP.put(FILTER_DATE_TIME_BETWEEN, "在...之间");
        OPERATION_TYPE_DESC_MAP.put(FILTER_DATE_TIME_NOT_BETWEEN, "不在...之间");
        OPERATION_TYPE_DESC_MAP.put(FILTER_DATE_TIME_IN, "在...之中");
        OPERATION_TYPE_DESC_MAP.put(FILTER_DATE_TIME_NOT_IN, "不在...之中");

        OPERATION_TYPE_DESC_MAP.put(SORT_ASC, "顺序");
        OPERATION_TYPE_DESC_MAP.put(SORT_DESC, "倒序");

        OPERATION_TYPE_DESC_MAP.put(AGGREGATION, "聚合");
        OPERATION_TYPE_DESC_MAP.put(NON_AGGREGATION, "非聚合");
    }

    public static Map<String, OperationType> valueMap = Stream.of(
            FILTER_EQ,
            FILTER_NE,
            FILTER_GT,
            FILTER_GE,
            FILTER_LT,
            FILTER_LE,
            FILTER_BETWEEN,
            FILTER_NOT_BETWEEN,
            FILTER_LIKE,
            FILTER_NOT_LIKE,
            FILTER_LIKE_LEFT,
            FILTER_LIKE_RIGHT,
            FILTER_IS_NULL,
            FILTER_IS_NOT_NULL,
            FILTER_IN,
            FILTER_NOT_IN,
            SORT_ASC,
            SORT_DESC,
            AGGREGATION,
            NON_AGGREGATION
    ).collect(Collectors.toMap(OperationType::getVal, type -> type));

    private String val;
    private OperationType(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public static OperationType valueOf(String val) {
        return valueMap.get(val);
    }

    public static OperationType addOperation(String val) {
        valueMap.computeIfAbsent(val, OperationType::new);
        return valueOf(val);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OperationType that = (OperationType) o;
        return val.equals(that.val);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }
}
