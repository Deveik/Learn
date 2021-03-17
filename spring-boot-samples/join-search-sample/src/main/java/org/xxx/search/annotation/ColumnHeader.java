package org.xxx.search.annotation;

import org.xxx.search.constants.AlignType;
import org.xxx.search.constants.ZoomType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.xxx.search.constants.PropertiesConstants.*;
import static org.xxx.search.constants.ZoomType.ZOOM_ALL;

/**
 * 简化逻辑，只对前端的处理
 * @author Deveik
 */
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.FIELD)
public @interface ColumnHeader {
    /**
     * @return 单列组件类型
     */
    String type() default COL_TYPE_NORMAL;
    /**
     * @return 列表单列宽度
     */
    int width() default COL_PROP_WIDTH;
    /**
     * @return 单列是否隐藏
     */
    boolean hidden() default false;
    /**
     * @return 单列标题
     */
    String label() default "";
    /**
     * @return 优先级
     */
    int priority() default 9999;
    /**
     * @return 排列规则
     */
    AlignType align() default AlignType.CENTER;
    /**
     * @return 相关参数（按顺序）
     */
    String[] relevance() default {};
    /**
     * @return 该属性是否可以成为 条件 或作为 分组 以及按照此 排序(默认 都设置 条件、分组、排序)
     */
    ZoomType[] zoom() default ZOOM_ALL;
    /**
     * @return 该属性的类型（默认是字符串）
     */
    String fieldType() default "String";
}
