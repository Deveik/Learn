package org.xyz.module.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.xyz.module.entity.WorkItemSearchItem;

import java.util.List;

/**
 * @author Deveik
 */
@Repository
public interface WorkItemSearchDao {

    /**
     * 查询作业单信息
     * @param page 分页条件
     * @param select 筛选信息
     * @param wrapper 查询条件
     * @return 汇总
     */
    @Select("SELECT ${select} FROM " +
            "t_warehouse_work_req req " +
            "LEFT JOIN t_warehouse_work_item item USING ( reqId ) " +
            "LEFT JOIN t_warehouse_work main ON main.workId = req.workId " +
            "LEFT JOIN t_warehouse_work_ext ext ON ext.workId = req.workId ${ew.customSqlSegment} ")
    List<WorkItemSearchItem> search(Page<WorkItemSearchItem> page, @Param("select") String select, @Param( Constants.WRAPPER ) Wrapper<WorkItemSearchItem> wrapper);

    /**
     * 汇总作业单信息
     * @param select 选择列
     * @param summarySelect 汇总选择列
     * @param wrapper 查询条件
     * @return 汇总
     */
    @Select("SELECT ${summarySelect} FROM (" +
            "SELECT ${select} FROM" +
            "t_warehouse_work_req req " +
            "LEFT JOIN t_warehouse_work_item item USING ( reqId ) " +
            "LEFT JOIN t_warehouse_work main ON main.workId = req.workId " +
            "LEFT JOIN t_warehouse_work_ext ext ON ext.workId = req.workId ${ew.customSqlSegment}) total")
    WorkItemSearchItem summary(String select, String summarySelect, @Param( Constants.WRAPPER ) Wrapper<WorkItemSearchItem> wrapper);
}
