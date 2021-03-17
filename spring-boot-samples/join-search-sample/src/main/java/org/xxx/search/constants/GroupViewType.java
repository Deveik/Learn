package org.xxx.search.constants;

/**
 * @author Deveik
 * 可选类型请参考[aggregate-functions](https://dev.mysql.com/doc/refman/5.7/en/aggregate-functions.html#function_group-concat)
 */
public enum GroupViewType {
    /**
     * 默认一条
     */
    GROUP_VIEW_FIRST("#"),
    /**
     * 平均值
     */
    GROUP_VIEW_AVG("AVG(#)"),
    /**
     * 拼接字符串
     */
    GROUP_VIEW_CONCAT("GROUP_CONCAT(#)"),
    /**
     * 数量（特殊处理，返回结果）
     */
    GROUP_VIEW_COUNT("COUNT(#)"),
    /**
     * 不重复的数量（特殊处理，返回结果）
     */
    GROUP_VIEW_COUNT_DISTINCT("COUNT(DISTINCT #)"),
    /**
     * 最大值
     */
    GROUP_VIEW_MAX("MAX(#)"),
    /**
     * 最小值
     */
    GROUP_VIEW_MIN("MIN(#)"),
    /**
     * 合计
     */
    GROUP_VIEW_SUM("SUM(#)");

    private final String template;

    GroupViewType(String template) {
        this.template = template;
    }

    public String getTemplate() {
        return template;
    }
}
