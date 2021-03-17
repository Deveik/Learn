package org.xxx.search.handler;

import org.xxx.search.model.SearchQuery;

import java.util.Collection;

/**
 * @author Deveik
 */
public interface SearchPlanCommand <T> {

    /**
     * 查询分页
     * @param params 查询条件
     * @return 分页结果
     */
    Collection<T> search(Collection<? extends SearchQuery> params);
}
