package org.xyz.module.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xxx.search.constants.OperationType;
import org.xxx.search.handler.AbstractParamFactory;
import org.xxx.search.handler.SearchHandle;
import org.xxx.search.model.SearchQuery;
import org.xyz.module.dao.WorkItemSearchDao;
import org.xyz.module.entity.WorkItemSearchItem;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.xyz.module.constants.FieldTypeConstants.*;

/**
 * @author Deveik
 */
@Service
public class WorkItemSearchService extends AbstractParamFactory<WorkItemSearchItem> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WorkItemSearchDao searchDao;

    private String replaceField(String field, String replacement) {
        return field.replace("Name", replacement);
    }

     {
        OperationType d1000Eq = OperationType.addOperation("divide_eq");
        OperationType d1000Gt = OperationType.addOperation("divide_gt");
        OperationType d1000Ge = OperationType.addOperation("divide_ge");
        OperationType d1000Lt = OperationType.addOperation("divide_lt");
        OperationType d1000Le = OperationType.addOperation("divide_le");

        OperationType.OPERATION_TYPE_DESC_MAP.put(d1000Eq, "等于");
        OperationType.OPERATION_TYPE_DESC_MAP.put(d1000Gt, "大于");
        OperationType.OPERATION_TYPE_DESC_MAP.put(d1000Ge, "大于等于");
        OperationType.OPERATION_TYPE_DESC_MAP.put(d1000Lt, "小于");
        OperationType.OPERATION_TYPE_DESC_MAP.put(d1000Le, "小于等于");

        SearchHandle.addOperationType(DIVIDE_THOUSAND, d1000Eq);
        SearchHandle.addOperationType(DIVIDE_THOUSAND, d1000Gt);
        SearchHandle.addOperationType(DIVIDE_THOUSAND, d1000Ge);
        SearchHandle.addOperationType(DIVIDE_THOUSAND, d1000Lt);
        SearchHandle.addOperationType(DIVIDE_THOUSAND, d1000Le);

        searchHandle.addSpecialHandle(d1000Eq, (wrapper, field, param) -> wrapper.eq("(" + field + " / 1000)", param));
        searchHandle.addSpecialHandle(d1000Gt, (wrapper, field, param) -> wrapper.gt("(" + field + " / 1000)", param));
        searchHandle.addSpecialHandle(d1000Ge, (wrapper, field, param) -> wrapper.ge("(" + field + " / 1000)", param));
        searchHandle.addSpecialHandle(d1000Lt, (wrapper, field, param) -> wrapper.lt("(" + field + " / 1000)", param));
        searchHandle.addSpecialHandle(d1000Le, (wrapper, field, param) -> wrapper.le("(" + field + " / 1000)", param));

        OperationType pairEq = OperationType.addOperation("pair_eq");
        OperationType pairNq = OperationType.addOperation("pair_nq");
        OperationType pairIn = OperationType.addOperation("pair_in");
        OperationType pairNotIn = OperationType.addOperation("pair_not_in");

        OperationType.OPERATION_TYPE_DESC_MAP.put(pairEq, "等于");
        OperationType.OPERATION_TYPE_DESC_MAP.put(pairNq, "不等于");
        OperationType.OPERATION_TYPE_DESC_MAP.put(pairIn, "在...之中");
        OperationType.OPERATION_TYPE_DESC_MAP.put(pairNotIn, "不在...之中");

        SearchHandle.addOperationType(REPLACE_CODE, pairEq);
        SearchHandle.addOperationType(REPLACE_CODE, pairNq);
        SearchHandle.addOperationType(REPLACE_CODE, pairIn);
        SearchHandle.addOperationType(REPLACE_CODE, pairNotIn);

        searchHandle.addSpecialHandle(pairEq, (wrapper, field, param) -> wrapper.or(i -> i.eq(field, param).eq(replaceField(field, "Code"), param)));
        searchHandle.addSpecialHandle(pairNq, (wrapper, field, param) -> wrapper.or(i -> i.gt(field, param).gt(replaceField(field, "Code"), param)));
        searchHandle.addSpecialHandle(pairIn, (wrapper, field, param) -> wrapper.or(i -> i.ge(field, param).ge(replaceField(field, "Code"), param)));
        searchHandle.addSpecialHandle(pairNotIn, (wrapper, field, param) -> wrapper.or(i -> i.lt(field, param).lt(replaceField(field, "Code"), param)));

        OperationType userEq = OperationType.addOperation("user_eq");
        OperationType userNq = OperationType.addOperation("user_nq");
        OperationType userIn = OperationType.addOperation("user_in");
        OperationType userNotIn = OperationType.addOperation("user_not_in");

        OperationType.OPERATION_TYPE_DESC_MAP.put(userEq, "等于");
        OperationType.OPERATION_TYPE_DESC_MAP.put(userNq, "不等于");
        OperationType.OPERATION_TYPE_DESC_MAP.put(userIn, "在...之中");
        OperationType.OPERATION_TYPE_DESC_MAP.put(userNotIn, "不在...之中");

        SearchHandle.addOperationType(REPLACE_USER, userEq);
        SearchHandle.addOperationType(REPLACE_USER, userNq);
        SearchHandle.addOperationType(REPLACE_USER, userIn);
        SearchHandle.addOperationType(REPLACE_USER, userNotIn);

        searchHandle.addSpecialHandle(userEq, (wrapper, field, param) -> wrapper.or(i -> i.eq(field, param).eq(replaceField(field, "Uin"), param)));
        searchHandle.addSpecialHandle(userNq, (wrapper, field, param) -> wrapper.or(i -> i.gt(field, param).gt(replaceField(field, "Uin"), param)));
        searchHandle.addSpecialHandle(userIn, (wrapper, field, param) -> wrapper.or(i -> i.ge(field, param).ge(replaceField(field, "Uin"), param)));
        searchHandle.addSpecialHandle(userNotIn, (wrapper, field, param) -> wrapper.or(i -> i.lt(field, param).lt(replaceField(field, "Uin"), param)));

        OperationType idEq = OperationType.addOperation("id_eq");
        OperationType idNq = OperationType.addOperation("id_nq");
        OperationType idIn = OperationType.addOperation("id_in");
        OperationType idNotIn = OperationType.addOperation("id_not_in");

        OperationType.OPERATION_TYPE_DESC_MAP.put(idEq, "等于");
        OperationType.OPERATION_TYPE_DESC_MAP.put(idNq, "不等于");
        OperationType.OPERATION_TYPE_DESC_MAP.put(idIn, "在...之中");
        OperationType.OPERATION_TYPE_DESC_MAP.put(idNotIn, "不在...之中");

        SearchHandle.addOperationType(REPLACE_ID, idEq);
        SearchHandle.addOperationType(REPLACE_ID, idNq);
        SearchHandle.addOperationType(REPLACE_ID, idIn);
        SearchHandle.addOperationType(REPLACE_ID, idNotIn);

        searchHandle.addSpecialHandle(idEq, (wrapper, field, param) -> wrapper.or(i -> i.eq(field, param).eq(replaceField(field, "Id"), param)));
        searchHandle.addSpecialHandle(idNq, (wrapper, field, param) -> wrapper.or(i -> i.gt(field, param).gt(replaceField(field, "Id"), param)));
        searchHandle.addSpecialHandle(idIn, (wrapper, field, param) -> wrapper.or(i -> i.ge(field, param).ge(replaceField(field, "Id"), param)));
        searchHandle.addSpecialHandle(idNotIn, (wrapper, field, param) -> wrapper.or(i -> i.lt(field, param).lt(replaceField(field, "Id"), param)));
    }

    public WorkItemSearchService() {
        // 这一块直接清空（多线程相关的话，可能需要注意一下）
        prepare(WorkItemSearchItem.class);
    }

    @Override
    public Collection<WorkItemSearchItem> search(Collection<? extends SearchQuery> collection) {
       QueryWrapper<WorkItemSearchItem> queryWrapper = getQueryWrapper(collection);

       List<? extends SearchQuery> groups =
               collection.stream()
                       .filter(item -> OperationType.NON_AGGREGATION.getVal().equals(item.getOperation()))
                       .collect(Collectors.toList());
       Set<String> selectColumns = collectAliasSelect(groups);
       String frontedSelect = String.join(", ", selectColumns);
       logger.info("WorkItemSearchService.search select sql:{}", selectColumns);

       queryWrapper.select(frontedSelect);
       Page<WorkItemSearchItem> page = new Page<>();
       List<WorkItemSearchItem> result = searchDao.search(page, frontedSelect, queryWrapper);

       logger.info("WorkItemSearchService log {}", result);

       return result;
    }
}
