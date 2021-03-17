package org.xxx.search.handler;

import com.baomidou.mybatisplus.core.conditions.interfaces.Compare;
import com.baomidou.mybatisplus.core.conditions.interfaces.Func;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.xxx.search.annotation.WrapperInvoke;
import org.xxx.search.constants.OperationType;
import org.xxx.search.exception.SearchException;
import org.xxx.search.model.Pair;
import org.xxx.search.model.SearchQuery;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.xxx.search.constants.OperationType.*;
import static org.xxx.search.constants.PropertiesConstants.*;

/**
 * @author Deveik
 * 用于处理 Mybatis Plus 的 Wrappers
 */
public class SearchHandle <T> {

    private static final Map<String, Set<OperationType>> CLASS_VALUE_OPERATION_MAP = Stream.of(
            new Pair<>(STRING_VALUE, Stream.of(
                    FILTER_EQ, FILTER_NE,
                    FILTER_LIKE, FILTER_NOT_LIKE, FILTER_LIKE_LEFT, FILTER_LIKE_RIGHT,
                    FILTER_IN, FILTER_NOT_IN,
                    FILTER_IS_NULL, FILTER_IS_NOT_NULL
                ).collect(Collectors.toSet())),
            new Pair<>(NUMBER_VALUE, Stream.of(
                    FILTER_EQ, FILTER_NE,
                    FILTER_GE, FILTER_GT, FILTER_LE, FILTER_LT,
                    FILTER_BETWEEN, FILTER_NOT_BETWEEN,
                    FILTER_IN, FILTER_NOT_IN,
                    FILTER_IS_NULL, FILTER_IS_NOT_NULL
                ).collect(Collectors.toSet())),
            new Pair<>(DATE_VALUE, Stream.of(
                    FILTER_DATE_EQ, FILTER_DATE_NE,
                    FILTER_DATE_GE, FILTER_DATE_LE,
                    FILTER_DATE_BETWEEN, FILTER_DATE_NOT_BETWEEN,
                    FILTER_DATE_IN, FILTER_DATE_NOT_IN,
                    FILTER_GT, FILTER_LT,
                    FILTER_IS_NULL, FILTER_IS_NOT_NULL
                ).collect(Collectors.toSet())),
            new Pair<>(DATE_TIME_VALUE, Stream.of(
                    FILTER_EQ, FILTER_NE,
                    FILTER_GE, FILTER_GT, FILTER_LE, FILTER_LT,
                    FILTER_BETWEEN, FILTER_NOT_BETWEEN,
                    FILTER_IN, FILTER_NOT_IN,
                    FILTER_IS_NULL, FILTER_IS_NOT_NULL
                ).collect(Collectors.toSet()))
    ).collect(Collectors.toMap(Pair::getKey, Pair::getValue));

    private final WrapperInvoke<QueryWrapper<T>, String, String> eq = (Compare::eq);
    private final WrapperInvoke<QueryWrapper<T>, String, String> ne = (Compare::ne);
    private final WrapperInvoke<QueryWrapper<T>, String, String> gt = (Compare::gt);
    private final WrapperInvoke<QueryWrapper<T>, String, String> ge = (Compare::ge);
    private final WrapperInvoke<QueryWrapper<T>, String, String> lt = (Compare::lt);
    private final WrapperInvoke<QueryWrapper<T>, String, String> le = (Compare::le);
    private final WrapperInvoke<QueryWrapper<T>, String, String> like = (Compare::like);
    private final WrapperInvoke<QueryWrapper<T>, String, String> notLike = (Compare::notLike);
    private final WrapperInvoke<QueryWrapper<T>, String, String> likeLeft = (Compare::likeLeft);
    private final WrapperInvoke<QueryWrapper<T>, String, String> likeRight = (Compare::likeRight);

    private final WrapperInvoke<QueryWrapper<T>, String, String> dateEq =
            ((wrapper, field, param) -> wrapper.eq("DATE(" + field + ")", param));
    private final WrapperInvoke<QueryWrapper<T>, String, String> dateNe =
            ((wrapper, field, param) -> wrapper.ne("DATE(" + field + ")", param));
    private final WrapperInvoke<QueryWrapper<T>, String, String> dateGe =
            ((wrapper, field, param) -> wrapper.ge("DATE(" + field + ")", param));
    private final WrapperInvoke<QueryWrapper<T>, String, String> dateLe =
            ((wrapper, field, param) -> wrapper.le("DATE(" + field + ")", param));

    private final WrapperInvoke<QueryWrapper<T>, String, String> isNull = ((wrapper, field, param) -> wrapper.isNull(field));
    private final WrapperInvoke<QueryWrapper<T>, String, String> isNotNull = ((wrapper, field, param) -> wrapper.isNotNull(field));

    private final WrapperInvoke<QueryWrapper<T>, String, String[]> between =
            ((wrapper, field, param) -> wrapper.between(field, param[0], param[1]));
    private final WrapperInvoke<QueryWrapper<T>, String, String[]> notBetween =
            ((wrapper, field, param) -> wrapper.notBetween(field, param[0], param[1]));

    private final WrapperInvoke<QueryWrapper<T>, String, String[]> dateBetween =
            ((wrapper, field, param) -> wrapper.between("DATE(" + field + ")", param[0], param[1]));
    private final WrapperInvoke<QueryWrapper<T>, String, String[]> dateNotBetween =
            ((wrapper, field, param) -> wrapper.notBetween("DATE(" + field + ")", param[0], param[1]));
    private final WrapperInvoke<QueryWrapper<T>, String, String[]> dateIn =
            ((wrapper, field, param) -> wrapper.in("DATE(" + field + ")", (Object[]) param));
    private final WrapperInvoke<QueryWrapper<T>, String, String[]> dateNotIn =
            ((wrapper, field, param) -> wrapper.notIn("DATE(" + field + ")", (Object[]) param));

    private final WrapperInvoke<QueryWrapper<T>, String, String> orderByDesc = (((wrapper, field, param) -> wrapper.orderByDesc(field)));
    private final WrapperInvoke<QueryWrapper<T>, String, String> orderByAsc = (((wrapper, field, param) -> wrapper.orderByAsc(field)));
    private final WrapperInvoke<QueryWrapper<T>, String, String> groupBy = (((wrapper, field, param) -> wrapper.groupBy(field)));

    private final WrapperInvoke<QueryWrapper<T>, String, String[]> in = ((Func::in));
    private final WrapperInvoke<QueryWrapper<T>, String, String[]> notIn = (Func::notIn);

    private final Map<OperationType, WrapperInvoke<QueryWrapper<T>, String, String>> OPERATION_FUNCTION_MAP = Stream.of(
            new Pair<>(FILTER_EQ, eq),
            new Pair<>(FILTER_NE, ne),
            new Pair<>(FILTER_GT, gt),
            new Pair<>(FILTER_GE, ge),
            new Pair<>(FILTER_LT, lt),
            new Pair<>(FILTER_LE, le),
            new Pair<>(FILTER_IS_NULL, isNull),
            new Pair<>(FILTER_IS_NOT_NULL, isNotNull),
            new Pair<>(FILTER_LIKE, like),
            new Pair<>(FILTER_NOT_LIKE, notLike),
            new Pair<>(FILTER_LIKE_LEFT, likeLeft),
            new Pair<>(FILTER_LIKE_RIGHT, likeRight),
            new Pair<>(FILTER_DATE_EQ, dateEq),
            new Pair<>(FILTER_DATE_NE, dateNe),
            new Pair<>(FILTER_DATE_GE, dateGe),
            new Pair<>(FILTER_DATE_LE, dateLe),

            new Pair<>(SORT_DESC, orderByDesc),
            new Pair<>(SORT_ASC, orderByAsc),
            new Pair<>(AGGREGATION, groupBy)
    ).collect(Collectors.toMap(Pair::getKey, Pair::getValue));

    private final Map<OperationType, WrapperInvoke<QueryWrapper<T>, String, String[]>> OPERATION_FUNCTION_MULTI_PARAMS = Stream.of(
            new Pair<>(FILTER_BETWEEN, between),
            new Pair<>(FILTER_NOT_BETWEEN, notBetween),
            new Pair<>(FILTER_IN, in),
            new Pair<>(FILTER_NOT_IN, notIn),
            new Pair<>(FILTER_DATE_BETWEEN, dateBetween),
            new Pair<>(FILTER_DATE_NOT_BETWEEN, dateNotBetween),
            new Pair<>(FILTER_DATE_IN, dateIn),
            new Pair<>(FILTER_DATE_NOT_IN, dateNotIn)
    ).collect(Collectors.toMap(Pair::getKey, Pair::getValue));

    private final List<OperationType> ignoreOperations = Collections.singletonList(NON_AGGREGATION);

    private final Map<OperationType, WrapperInvoke<QueryWrapper<T>, String, String>> CUSTOM_OPERATION_FUNCTION_MAP = new HashMap<>(32);

    private String splitString = ", ";

    public SearchHandle() {
    }

    public SearchHandle(String splitString) {
        this.splitString = splitString;
    }

    private boolean checkValidSearch() {
        // TODO: 校验
        //  === 对应的 FieldName 是否存在
        //  === 对应的 Field 所属的类型是否匹配查询类型
        return true;
    }

    /**
     * 根据查询方案获取对应的值
     * @param params 查询方案
     * @return Mybatis Plus 的查询包装器
     */
    public QueryWrapper<T> getQueryWrapper(Collection<? extends SearchQuery> params) {
        QueryWrapper<T> query = Wrappers.query();

        for (SearchQuery param : params) {
            OperationType operationType = param.getOperationType();
            String searchValue = param.getSearchValue();
            String aliasName = param.getAliasName();

            if (OPERATION_FUNCTION_MULTI_PARAMS.containsKey(operationType)) {
                // 这里来决定是以什么方式来决定分割符号方式
                String[] pass = new String[]{};
                if (searchValue != null && searchValue.length() != 0) {
                    pass = searchValue.split(splitString);
                }
                OPERATION_FUNCTION_MULTI_PARAMS.get(operationType).invoke(query, aliasName, pass);
            } else {
                if (OPERATION_FUNCTION_MAP.containsKey(operationType)) {
                    OPERATION_FUNCTION_MAP.get(operationType).invoke(query, aliasName, searchValue);
                }
                else if (CUSTOM_OPERATION_FUNCTION_MAP.containsKey(operationType)) {
                    CUSTOM_OPERATION_FUNCTION_MAP.get(operationType).invoke(query, aliasName, searchValue);
                }
                else if (ignoreOperations.contains(operationType)) {
                    // 忽略 不做任何事
                }
                else {
                    throw new SearchException();
                }
            }
        }
        return query;
    }

    /**
     * 添加特殊的类型的处理方式
     * 例子：
     * 有一种类型的属性既可以接受编码的固定查询，又可以接受对应名称的模糊查询
     * 传过来的值可以是 Json 也可以是特殊形式的字符串，这里解析出所需要的内容，然后进行包装器的组装
     */
    public void addSpecialHandle(OperationType operationType, WrapperInvoke<QueryWrapper<T>, String, String> method) {
        CUSTOM_OPERATION_FUNCTION_MAP.putIfAbsent(operationType, method);
    }

    /**
     * 根据自定义的字段类型获取可操作类型
     * @param fieldType 字段类型（自定义）
     * @return 可操作类型集合
     */
    public static Set<OperationType> getOperationTypes(String fieldType) {
        return CLASS_VALUE_OPERATION_MAP.getOrDefault(fieldType, new HashSet<>());
    }

    /**
     * 添加自定义的字段
     * @param fieldType 字段类型（自定义）
     * @param operationType 可操作类型
     */
    public static void addOperationType(String fieldType, OperationType operationType) {
        Set<OperationType> types = getOperationTypes(fieldType);
        types.add(operationType);
        CLASS_VALUE_OPERATION_MAP.put(fieldType, types);
    }

}
