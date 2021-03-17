package org.xxx.search.annotation;

import org.xxx.search.constants.GroupViewType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 简化逻辑 对 SQL 所作的特殊处理，同时用于记录查询方案上的查询方式
 * @author Deveik
 */
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.FIELD)
public @interface QuerySelect {
    /**
     * @return 字段是否存在
     */
    boolean exist() default true;
    /**
     * @return 查询时所用的别称，使用时如果是会产生冲突的字段，请添加上对应表指向
     */
    String alias() default "";
    /**
     * @return 字段所属的表
     */
    String from() default "";
    /**
     * @return 关联参数（即 该字段存在，depend 下的字段也应该存在）
     */
    String[] depend() default {};
    /**
     * @return 允许进行合计
     */
    boolean additive() default false;
    /**
     * @return 默认汇总列
     */
    boolean defaultGroup() default false;
    /**
     * @return 聚合的方式
     */
    GroupViewType defaultGroupView() default GroupViewType.GROUP_VIEW_SUM;
}
