package org.xxx.search.annotation;

/**
 * @author Deveik
 */
@FunctionalInterface
public interface WrapperInvoke <R, A, T> {
    /**
     * 处理包装器后并原路返回
     * @param wrapper 包装器
     * @param field 域
     * @param param 参数
     * @return 更新参数后的包装器
     */
    R invoke(R wrapper, A field, T param);
}
