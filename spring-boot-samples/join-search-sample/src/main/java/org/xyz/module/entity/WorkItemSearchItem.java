package org.xyz.module.entity;

import org.xxx.search.annotation.ColumnHeader;
import org.xxx.search.annotation.QuerySelect;
import org.xxx.search.constants.AlignType;
import org.xxx.search.constants.PropertiesConstants;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.xxx.search.constants.ZoomType.ZOOM_ALL;
import static org.xxx.search.constants.ZoomType.ZOOM_NONE;
import static org.xyz.module.constants.ColumnDecisionConstants.*;
import static org.xyz.module.constants.FieldTypeConstants.*;

/**
 * @author Deveik
 */
public class WorkItemSearchItem {
    @ColumnHeader( zoom = ZOOM_NONE )
    @QuerySelect( exist = false )
    private String selection;
    @ColumnHeader( label = "作业单号", type = COL_FORM_DETAIL + "")
    @QuerySelect( from = "main" )
    private String workId;
    @ColumnHeader( label = "作业日期", fieldType = PropertiesConstants.DATE_VALUE, hidden = true )
    @QuerySelect( from = "main" )
    private LocalDateTime workTime;
    @ColumnHeader( label = "作业类型", hidden = true )
    @QuerySelect( from = "main" )
    private int workType;
    @ColumnHeader( label = "状态", hidden = true )
    @QuerySelect( from = "main" )
    private int status;
    @ColumnHeader( label = "存储地点", hidden = true )
    @QuerySelect( from = "main" )
    private String locationCode;
    @ColumnHeader( label = "存储地点", zoom = ZOOM_NONE, align = AlignType.LEFT )
    @QuerySelect( from = "main", depend = "locationCode")
    private String locationName;
    @ColumnHeader( label = "执行人", zoom = ZOOM_NONE, hidden = true, type = COL_USER_UIN + "")
    @QuerySelect( from = "main" )
    private Integer executorUin;
    @ColumnHeader( label = "执行人", type = COL_USER + "", width = NORMAL_COL_WIDTH_MIN, fieldType = REPLACE_USER )
    @QuerySelect( from = "main", depend = "executorUin" )
    private String executorName;
    @ColumnHeader( label = "作业仓库", hidden = true )
    @QuerySelect( from = "main")
    private String warehouseCode;
    @ColumnHeader( label = "作业仓库", zoom = ZOOM_NONE, align = AlignType.LEFT, fieldType = REPLACE_CODE )
    @QuerySelect( from =  "main", depend = "warehouseCode" )
    private String warehouseName;
    @ColumnHeader( label = "来源单号", type = COL_FORM_DETAIL + "", width = NORMAL_COL_WIDTH_MID )
    @QuerySelect( from = "main" )
    private String outerCode;
    @ColumnHeader( label = "来源类型", hidden = true, align = AlignType.LEFT )
    @QuerySelect( from = "main" )
    private int outerType;
    @ColumnHeader( label = "创建时间", fieldType = PropertiesConstants.DATE_TIME_VALUE, hidden = true )
    @QuerySelect( from = "main" )
    private LocalDateTime createTime;
    @ColumnHeader( label = "完成时间", fieldType = PropertiesConstants.DATE_TIME_VALUE, hidden = true )
    @QuerySelect( from = "main" )
    private LocalDateTime finishTime;

    @ColumnHeader( label = "料品", zoom = ZOOM_ALL )
    @QuerySelect( from = "req" )
    private String mCode;
    @ColumnHeader( label = "品名", zoom = ZOOM_ALL, align = AlignType.LEFT, width = NORMAL_COL_WIDTH_MAX, fieldType = REPLACE_CODE )
    @QuerySelect( from = "req" )
    private String mName;
    @ColumnHeader( hidden = true, zoom = ZOOM_NONE )
    @QuerySelect( from = "req" )
    private String weightUnit;
    @ColumnHeader( hidden = true, zoom = ZOOM_NONE )
    @QuerySelect( from = "req" )
    private String amountUnit;
    @ColumnHeader( label = "计划数量", fieldType = PropertiesConstants.NUMBER_VALUE )
    @QuerySelect( from = "req" )
    private BigDecimal planAmount;
    @ColumnHeader( label = "计划重量", fieldType = DIVIDE_THOUSAND )
    @QuerySelect( from = "req" )
    private BigDecimal planWeight;

    @ColumnHeader( hidden = true, label = "规格", fieldType = DIVIDE_THOUSAND )
    @QuerySelect( from = "item" , depend = {"weightUnit", "amountUnit"})
    private int spec;
    @ColumnHeader( hidden = true, zoom = ZOOM_ALL )
    @QuerySelect( from = "item" )
    private int mType;
    @ColumnHeader( hidden = true, zoom = ZOOM_NONE )
    @QuerySelect( from = "item" )
    private int itemType;
    @ColumnHeader( label = "批号", align = AlignType.LEFT )
    @QuerySelect( from = "item" )
    private String batchNumber;
    @ColumnHeader( hidden = true, zoom = ZOOM_NONE )
    @QuerySelect( from = "item" )
    private String positionCode;
    @ColumnHeader( label = "作业库位", align = AlignType.LEFT, fieldType = REPLACE_CODE )
    @QuerySelect( from = "item", depend = "positionCode" )
    private String positionName;
    @ColumnHeader( label = "作业数量", hidden = true, fieldType = PropertiesConstants.NUMBER_VALUE,
            align = AlignType.RIGHT, zoom = ZOOM_NONE  )
    @QuerySelect( from = "item", additive = true)
    private BigDecimal executeAmount;
    @ColumnHeader( label = "作业重量", hidden = true, fieldType = DIVIDE_THOUSAND,
            zoom = ZOOM_NONE )
    @QuerySelect( from = "item", additive = true)
    private long executeWeight;

    @ColumnHeader( label = "业务员", hidden = true )
    @QuerySelect( from = "ext" )
    private Integer saleUin;
    @ColumnHeader( label = "业务员", zoom = ZOOM_NONE, fieldType = REPLACE_USER,  type = COL_USER + "", width = NORMAL_COL_WIDTH_MIN )
    @QuerySelect( from = "ext", depend = "saleUin" )
    private String saleName;
    @ColumnHeader( label = "司机", hidden = true )
    @QuerySelect( from = "ext" )
    private Integer driverUin;
    @ColumnHeader( label = "司机", zoom = ZOOM_NONE, fieldType = REPLACE_USER, type = COL_USER + "", width = NORMAL_COL_WIDTH_MIN )
    @QuerySelect( from = "ext", depend = "driverUin" )
    private String driverName;
    @ColumnHeader( label = "车辆", align = AlignType.LEFT )
    @QuerySelect( from = "ext" )
    private String truckNo;
    @ColumnHeader( label = "客户名称", hidden = true )
    @QuerySelect( from = "ext" )
    private String customerCode;
    @ColumnHeader( label = "客户名称", zoom = ZOOM_NONE, align = AlignType.LEFT, width = NORMAL_COL_WIDTH_MAX, fieldType = REPLACE_CODE )
    @QuerySelect( from = "ext", depend = "customerCode" )
    private String customerName;
    @ColumnHeader( label = "供应商", hidden = true )
    @QuerySelect( from = "ext" )
    private String supplierCode;
    @ColumnHeader( label = "供应商", zoom = ZOOM_NONE, align = AlignType.LEFT, width = NORMAL_COL_WIDTH_MAX, fieldType = REPLACE_CODE )
    @QuerySelect( from = "ext", depend = "supplierCode" )
    private String supplierName;
    @ColumnHeader( label = "制造商", hidden = true )
    @QuerySelect( from = "ext" )
    private String producerCode;
    @ColumnHeader( label = "制造商", zoom = ZOOM_NONE, align = AlignType.LEFT, width = NORMAL_COL_WIDTH_MAX, fieldType = REPLACE_CODE )
    @QuerySelect( from = "ext", depend = "producerCode" )
    private String producerName;
    @ColumnHeader( label = "柜号/封条", align = AlignType.LEFT )
    @QuerySelect( from = "ext" )
    private String containerNo;
    @ColumnHeader( label = "运输方式", hidden = true )
    @QuerySelect( from = "ext" )
    private Integer shipWay;
    @ColumnHeader( label = "运输方式", zoom = ZOOM_NONE )
    @QuerySelect( from = "ext", depend = "shipWay" )
    private String shipWayName;
    @ColumnHeader( label = "受益人", hidden = true )
    @QuerySelect( from = "ext" )
    private Integer profitUin;
    @ColumnHeader( label = "受益人", zoom = ZOOM_NONE, type = COL_USER + "", width = NORMAL_COL_WIDTH_MIN, fieldType = REPLACE_USER )
    @QuerySelect( from = "ext", depend = "profitUin" )
    private String profitName;
    @ColumnHeader( label = "受益部门", zoom = ZOOM_NONE, hidden = true )
    @QuerySelect( from = "ext" )
    private Integer profitDepId;
    @ColumnHeader( label = "受益部门", align = AlignType.LEFT, width = NORMAL_COL_WIDTH_MID, fieldType = REPLACE_ID )
    @QuerySelect( from = "ext" )
    private String profitDepName;
    @ColumnHeader( label = "退货原因", hidden = true )
    @QuerySelect( from = "ext" )
    private String reasonCode;
    @ColumnHeader( label = "渠道", align = AlignType.LEFT, width = NORMAL_COL_WIDTH_MID )
    @QuerySelect( from = "ext" )
    private String channel;
    @ColumnHeader( label = "备注", align = AlignType.LEFT, width = NORMAL_COL_WIDTH_MID )
    @QuerySelect( from = "ext" )
    private String remark;
    @ColumnHeader( label = "出米率", fieldType = PropertiesConstants.NUMBER_VALUE )
    @QuerySelect( from = "ext" )
    private String actualConvertRate;
    @ColumnHeader( label = "原粮损耗率", fieldType = PropertiesConstants.NUMBER_VALUE )
    @QuerySelect( from = "ext" )
    private String materialConvertRate;
    @QuerySelect( from = "ext" )
    @ColumnHeader( label = "包装损耗率", fieldType = PropertiesConstants.NUMBER_VALUE )
    private String packageConsumeRate;
    @ColumnHeader(label = "包装损耗量",  zoom = ZOOM_NONE, align = AlignType.RIGHT)
    private BigDecimal packageConsumeAmount;

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public LocalDateTime getWorkTime() {
        return workTime;
    }

    public void setWorkTime(LocalDateTime workTime) {
        this.workTime = workTime;
    }

    public int getWorkType() {
        return workType;
    }

    public void setWorkType(int workType) {
        this.workType = workType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Integer getExecutorUin() {
        return executorUin;
    }

    public void setExecutorUin(Integer executorUin) {
        this.executorUin = executorUin;
    }

    public String getExecutorName() {
        return executorName;
    }

    public void setExecutorName(String executorName) {
        this.executorName = executorName;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getOuterCode() {
        return outerCode;
    }

    public void setOuterCode(String outerCode) {
        this.outerCode = outerCode;
    }

    public int getOuterType() {
        return outerType;
    }

    public void setOuterType(int outerType) {
        this.outerType = outerType;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public String getmCode() {
        return mCode;
    }

    public void setmCode(String mCode) {
        this.mCode = mCode;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getAmountUnit() {
        return amountUnit;
    }

    public void setAmountUnit(String amountUnit) {
        this.amountUnit = amountUnit;
    }

    public BigDecimal getPlanAmount() {
        return planAmount;
    }

    public void setPlanAmount(BigDecimal planAmount) {
        this.planAmount = planAmount;
    }

    public BigDecimal getPlanWeight() {
        return planWeight;
    }

    public void setPlanWeight(BigDecimal planWeight) {
        this.planWeight = planWeight;
    }

    public int getSpec() {
        return spec;
    }

    public void setSpec(int spec) {
        this.spec = spec;
    }

    public int getmType() {
        return mType;
    }

    public void setmType(int mType) {
        this.mType = mType;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public BigDecimal getExecuteAmount() {
        return executeAmount;
    }

    public void setExecuteAmount(BigDecimal executeAmount) {
        this.executeAmount = executeAmount;
    }

    public long getExecuteWeight() {
        return executeWeight;
    }

    public void setExecuteWeight(long executeWeight) {
        this.executeWeight = executeWeight;
    }

    public Integer getSaleUin() {
        return saleUin;
    }

    public void setSaleUin(Integer saleUin) {
        this.saleUin = saleUin;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public Integer getDriverUin() {
        return driverUin;
    }

    public void setDriverUin(Integer driverUin) {
        this.driverUin = driverUin;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getTruckNo() {
        return truckNo;
    }

    public void setTruckNo(String truckNo) {
        this.truckNo = truckNo;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getProducerCode() {
        return producerCode;
    }

    public void setProducerCode(String producerCode) {
        this.producerCode = producerCode;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public String getContainerNo() {
        return containerNo;
    }

    public void setContainerNo(String containerNo) {
        this.containerNo = containerNo;
    }

    public Integer getShipWay() {
        return shipWay;
    }

    public void setShipWay(Integer shipWay) {
        this.shipWay = shipWay;
    }

    public String getShipWayName() {
        return shipWayName;
    }

    public void setShipWayName(String shipWayName) {
        this.shipWayName = shipWayName;
    }

    public Integer getProfitUin() {
        return profitUin;
    }

    public void setProfitUin(Integer profitUin) {
        this.profitUin = profitUin;
    }

    public String getProfitName() {
        return profitName;
    }

    public void setProfitName(String profitName) {
        this.profitName = profitName;
    }

    public Integer getProfitDepId() {
        return profitDepId;
    }

    public void setProfitDepId(Integer profitDepId) {
        this.profitDepId = profitDepId;
    }

    public String getProfitDepName() {
        return profitDepName;
    }

    public void setProfitDepName(String profitDepName) {
        this.profitDepName = profitDepName;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getActualConvertRate() {
        return actualConvertRate;
    }

    public void setActualConvertRate(String actualConvertRate) {
        this.actualConvertRate = actualConvertRate;
    }

    public String getMaterialConvertRate() {
        return materialConvertRate;
    }

    public void setMaterialConvertRate(String materialConvertRate) {
        this.materialConvertRate = materialConvertRate;
    }

    public String getPackageConsumeRate() {
        return packageConsumeRate;
    }

    public void setPackageConsumeRate(String packageConsumeRate) {
        this.packageConsumeRate = packageConsumeRate;
    }

    public BigDecimal getPackageConsumeAmount() {
        return packageConsumeAmount;
    }

    public void setPackageConsumeAmount(BigDecimal packageConsumeAmount) {
        this.packageConsumeAmount = packageConsumeAmount;
    }
}
