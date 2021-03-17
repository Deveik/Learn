package org.xxx.search.handler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.xxx.search.annotation.ColumnHeader;
import org.xxx.search.annotation.DefaultAnnotationHandler;
import org.xxx.search.annotation.QuerySelect;
import org.xxx.search.constants.ZoomType;
import org.xxx.search.model.CustomColumn;
import org.xxx.search.model.Pair;
import org.xxx.search.model.SearchParam;
import org.xxx.search.model.SearchQuery;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.xxx.search.constants.PropertiesConstants.*;
import static org.xxx.search.constants.ZoomType.*;

/**
 * @author Deveik
 */
public abstract class AbstractParamFactory <T> implements SearchPlanCommand<T> {

    private Set<String> defaultSortedFields = new LinkedHashSet<>();
    private Set<String> summarySet = new HashSet<>(16);

    private Map<String, QuerySelect> selectMap = new HashMap<>(128);

    private Map<String, CustomColumn> columnMap = new HashMap<>(128);
    private Map<String, String> aliasMap = new HashMap<>(128);
    private Map<String, String> typeMap = new HashMap<>(128);

    private Map<ZoomType, List<SearchParam>> paramMap = new HashMap<>(3);

    private final Map<Class<?>, String> CLASS_VALUE_MAP = Stream.of(
            new Pair<>(String.class, STRING_VALUE),
            new Pair<>(Integer.class, NUMBER_VALUE),
            new Pair<>(BigDecimal.class, NUMBER_VALUE),
            new Pair<>(LocalDate.class, DATE_VALUE),
            new Pair<>(LocalDateTime.class, DATE_TIME_VALUE)
    ).collect(Collectors.toMap(Pair::getKey, Pair::getValue));

    protected SearchHandle<T> searchHandle = new SearchHandle<>();

    public List<SearchParam> getSearchParamByZoom(ZoomType type) {
        return paramMap.getOrDefault(type, new ArrayList<>());
    }

    public Map<String, String> getAliasMap() {
        return aliasMap;
    }

    public Map<String, String> getTypeMap() {
        return typeMap;
    }

    /**
     * 准备信息
     *
     * @param classes 基本类
     */
    protected void prepare(Class<?> ...classes) {

        // TODO: 需要一个总括的注解去标识，但又不是非常的合理...后面参考系 Java Validation 中的 Group 看看怎么处理好
        Set<String> tempFieldNames = new LinkedHashSet<>(128);
        // TODO: 对应类所有的域，这里需要回溯被继承的类，也一并包括在内
        List<Field> fields =
                Stream.of(classes).flatMap(clz -> Stream.of(clz.getDeclaredFields())).collect(Collectors.toList());

        for (Field field : fields) {
            String fieldName = field.getName();
            Class<?> fieldClazz = field.getType();

            String fieldTypeValue = CLASS_VALUE_MAP.getOrDefault(fieldClazz, STRING_VALUE);

            QuerySelect select = field.getAnnotation(QuerySelect.class);
            ColumnHeader header = field.getAnnotation(ColumnHeader.class);

            header = header != null ? header : DefaultAnnotationHandler.getDefaultHeader();
            select = select != null ? select : DefaultAnnotationHandler.getDefaultSelect();

            String label = header.label();
            String realFieldType = !fieldTypeValue.equals(header.fieldType()) ? header.fieldType() : fieldTypeValue;

            selectMap.put(fieldName, select);
            typeMap.put(fieldName, realFieldType);

            // 仅当列头不隐藏，且无分组筛选或在分组内
            if (!header.hidden()) {
                String type = header.type();
                int priority = header.priority();
                int width = header.width();
                String align = header.align().getValue();
                String[] relevance = header.relevance();

                columnMap.put(fieldName, new CustomColumn(fieldName, label, priority, type, width, align, relevance));
            }

            // 记录列头的参数信息
            ZoomType[] zoomTypes = header.zoom();
            Stream.of(CONDITION, GROUP, SORT)
                    .filter(flag -> checkInclude(zoomTypes, flag))
                    .forEach(flag -> {
                        // 返回对应参数信息
                        List<SearchParam> searchParams = paramMap.computeIfAbsent(flag, k -> new ArrayList<>());
                        searchParams.add(new SearchParam(fieldName, label, realFieldType));
                        paramMap.put(flag, searchParams);
                    });

            // 存在别名
            if (select.exist()) {
                String replacement = fieldName;
                if (!stringIsEmpty(select.from())) {
                    replacement = "#.#".replaceFirst("#", select.from()).replaceFirst("#", fieldName);
                }
                aliasMap.put(fieldName, stringIsEmpty(select.alias()) ? replacement : select.alias());
            }

            // 如果存在累计标志
            if (select.additive()) {
                summarySet.add(fieldName);
            }

            tempFieldNames.add(fieldName);
        }
        // 记录顺序
        for (String fieldName : tempFieldNames) {
            defaultSortedFields.add(fieldName);

            String[] relevanceList = selectMap.get(fieldName).depend();
            for (String relevance : relevanceList) {
                if (tempFieldNames.contains(relevance)) {
                    defaultSortedFields.add(relevance);
                }
            }
        }
    }

    /**
     * 返回自定义列表列头信息
     *
     * @param groups 查询方案
     * @return 列头信息列表
     */
    public List<CustomColumn> collectHeaders(Collection<? extends SearchParam> groups) {
        Set<String> sortedGroups;
        if (collectionIsEmpty(groups)) {
            sortedGroups = new LinkedHashSet<>();
            for (SearchParam param : groups) {
                String fieldName = param.getFieldName();
                sortedGroups.add(fieldName);

                QuerySelect select = selectMap.get(fieldName);
                sortedGroups.addAll(Arrays.asList(select.depend()));
            }
        } else {
            sortedGroups = new LinkedHashSet<>(defaultSortedFields);
        }

        // 为 Group 分配值
        String[] groupArray = sortedGroups.toArray(new String[]{});
        Map<String, Integer> sortedGroupMap = IntStream.range(0, groupArray.length)
                .mapToObj(ind -> new Pair<>(groupArray[ind], (ind + 1) * COL_CROSS_STEP))
                .collect(Collectors.toMap(Pair::getKey, Pair::getValue));

        Set<CustomColumn> columns = new HashSet<>();
        sortedGroupMap.forEach((fieldName, index) -> {
            CustomColumn customColumn = columnMap.get(fieldName);
            if (customColumn != null) {
                customColumn.setPriority(index);

                columns.add(customColumn);
            }

        });

        // 根据优先属性或者分组情况排序
        List<CustomColumn> sortedColumns = new ArrayList<>(columns);
        sortedColumns.sort(Comparator.comparingInt(CustomColumn::getPriority));
        return sortedColumns;
    }

    /**
     * 负责收集统计信息的信息
     *
     * @param groups 查询方案
     * @return 转换后的项列表
     */
    public Set<String> collectSummarySelect(Collection<? extends SearchParam> groups) {
        if (collectionIsEmpty(groups)) {
            groups = paramMap.get(GROUP);
        }

        // 查找 select 相关
        Collection<? extends SearchParam> finalGroups = groups;
        return selectMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue().defaultGroup())
                .collect(Collectors.groupingBy(
                        entry -> entry.getValue().defaultGroupView(),
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())))
                .entrySet()
                .stream()
                .flatMap(entry -> {
                    List<? extends SearchParam> collect =
                            finalGroups.stream()
                                    .filter(searchParam -> entry.getValue().contains(searchParam.getFieldName()))
                                    .collect(Collectors.toList());
                    return getSelectSnippet(collect, entry.getKey().getTemplate()).stream();
                })
                .collect(Collectors.toSet());
    }

    /**
     * 负责收集所需要的列信息
     *
     * @param groups 查询方案
     * @return 转换后的项列表
     */
    public Set<String> collectAliasSelect(Collection<? extends SearchParam> groups) {
        if (collectionIsEmpty(groups)) {
            groups = paramMap.get(GROUP);
        }

        // TODO 分组情况收集
        String template = "# as #";
        return getSelectSnippet(groups, template);
    }


    protected QueryWrapper<T> getQueryWrapper(Collection<? extends SearchQuery> collection) {
        collection.forEach(item -> item.setAliasName(aliasMap.get(item.getFieldName())));
        return searchHandle.getQueryWrapper(collection);
    }

    private Set<String> getSelectSnippet(Collection<? extends SearchParam> groups, String template) {
        return groups.stream()
                .filter(group -> aliasMap.containsKey(group.getFieldName()))
                .flatMap(group -> {
                    String[] relevanceList = selectMap.get(group.getFieldName()).depend();
                    String[] resultList = new String[relevanceList.length + 1];
                    resultList[0] = group.getFieldName();
                    System.arraycopy(relevanceList, 0, resultList, 1, relevanceList.length);
                    return Stream.of(resultList);
                })
                .map(fieldName -> template
                        .replaceFirst("#", aliasMap.get(fieldName))
                        .replaceFirst("#", fieldName))
                .collect(Collectors.toSet());
    }

    private String convertStringByFormat(String[] params, String contentTemplate) {
        return String.format(params[0], Stream.of(params)
                .skip(1)
                .map(param -> contentTemplate.replace("#", param))
                .toArray());
    }

    private boolean stringIsEmpty(String value) {
        return value == null || value.isEmpty();
    }

    private boolean collectionIsEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

}
