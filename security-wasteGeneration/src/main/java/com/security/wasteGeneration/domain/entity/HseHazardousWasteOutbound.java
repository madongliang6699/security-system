package com.security.wasteGeneration.domain.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.cybstar.base.CybEntityBase;
import com.cybstar.base.secureBase.solidWaste.hazardousWaste.dto.HseHazardousWasteOutboundDTO;
import com.cybstar.base.secureBase.solidWaste.hazardousWaste.vo.HseHazardousWasteOutboundVO;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 固废管理-危废出库实体类
 * {"sid":"流水ID","appId":"appId","wasteBaseId":"关联的废物id","warehousingIds":"关联的入库ids","outboundCode":"出库批次编码","outboundTime":"出库时间","outboundQuantity":"出库量","transportOrgId":"运送部门id","transportOrgName":"运送部门name","transportPersonId":"运送部门经办人id","transportPersonName":"运送部门经办人name","outboundOrgId":"出库部门id","outboundOrgName":"出库部门name","outboundPersonId":"出库部门经办人id","outboundPersonName":"出库部门经办人name","storagePositionCode":"贮存设施字典编码","storagePositionName":"贮存设施字典名称","storagePositionType":"贮存设施字典类型","containerCode":"容器/包装编码和类型code","containerName":"容器/包装编码和类型name","status":"状态(0待处置;1已处置)","measurementUnitCode":"计量单位code","measurementUnitName":"计量单位name","containerNumber":"容器/包装个数","warehousingCodes":"入库批次编码","destinationOrgId":"去向单位id","destinationOrgName":"去向单位name","fileId":"附件","yearTag":"编号年份标记","serialNumber":"每个标记的最大序号","remark":"备注","updateBy":"修改者","updateTime":"更新时间","createBy":"创建者","createTime":"创建时间","tenantId":"租户id","tenantName":"租户名称","delFlag":"删除标记"}
 *
 * @author A
 * @version v 0.1
 * @File HseHazardousWasteOutbound.java
 * @Desc 固废管理-危废出库
 * @DateTime 2023-07-17 15:08:57
 * @Encoding UTF-8
 * @Description Copyright (c) 2004-2023 All Rights Reserved.
 */
public class HseHazardousWasteOutbound extends CybEntityBase<HseHazardousWasteOutbound, HseHazardousWasteOutboundDTO, HseHazardousWasteOutboundVO> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    /**
     * 关联的废物id
     */
    private String wasteBaseId;
    /**
     * 关联的入库ids
     */
    private String warehousingIds;
    /**
     * 出库批次编码
     */
    private String outboundCode;
    /**
     * 出库时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date outboundTime;
    /**
     * 出库量
     */
    private BigDecimal outboundQuantity;
    /**
     * 运送部门id
     */
    private String transportOrgId;
    /**
     * 运送部门name
     */
    private String transportOrgName;
    /**
     * 运送部门经办人id
     */
    private String transportPersonId;
    /**
     * 运送部门经办人name
     */
    private String transportPersonName;
    /**
     * 出库部门id
     */
    private String outboundOrgId;
    /**
     * 出库部门name
     */
    private String outboundOrgName;
    /**
     * 出库部门经办人id
     */
    private String outboundPersonId;
    /**
     * 出库部门经办人name
     */
    private String outboundPersonName;
    /**
     * 贮存设施字典编码
     */
    private String storagePositionCode;
    /**
     * 贮存设施字典名称
     */
    private String storagePositionName;
    /**
     * 贮存设施字典类型
     */
    private String storagePositionType;
    /**
     * 容器/包装编码和类型code
     */
    private String containerCode;
    /**
     * 容器/包装编码和类型name
     */
    private String containerName;
    /**
     * 状态(0待处置;1已处置)
     */
    private String status;
    /**
     * 计量单位code
     */
    private String measurementUnitCode;
    /**
     * 计量单位name
     */
    private String measurementUnitName;
    /**
     * 容器/包装个数
     */
    private Integer containerNumber;
    /**
     * 入库批次编码
     */
    private String warehousingCodes;
    /**
     * 去向单位id
     */
    private String destinationOrgId;
    /**
     * 去向单位name
     */
    private String destinationOrgName;
    /**
     * 附件
     */
    private String fileId;
    /**
     * 编号年份标记
     */
    private String yearTag;
    /**
     * 每个标记的最大序号
     */
    private Integer serialNumber;
    /**
     * 租户id
     */
    private String tenantId;
    /**
     * 租户名称
     */
    private String tenantName;
    
    
    /**
     * 获取关联的废物id
     *
     * @return 关联的废物id
     */
    public String getWasteBaseId() {
        return wasteBaseId;
    }
    
    /**
     * 设置关联的废物id
     *
     * @param wasteBaseId 关联的废物id
     */
    public void setWasteBaseId(String wasteBaseId) {
        this.wasteBaseId = wasteBaseId;
    }
    
    /**
     * 获取关联的入库ids
     *
     * @return 关联的入库ids
     */
    public String getWarehousingIds() {
        return warehousingIds;
    }
    
    /**
     * 设置关联的入库ids
     *
     * @param warehousingIds 关联的入库ids
     */
    public void setWarehousingIds(String warehousingIds) {
        this.warehousingIds = warehousingIds;
    }
    
    /**
     * 获取出库批次编码
     *
     * @return 出库批次编码
     */
    public String getOutboundCode() {
        return outboundCode;
    }
    
    /**
     * 设置出库批次编码
     *
     * @param outboundCode 出库批次编码
     */
    public void setOutboundCode(String outboundCode) {
        this.outboundCode = outboundCode;
    }
    
    /**
     * 获取出库时间
     *
     * @return 出库时间
     */
    public Date getOutboundTime() {
        return outboundTime;
    }
    
    /**
     * 设置出库时间
     *
     * @param outboundTime 出库时间
     */
    public void setOutboundTime(Date outboundTime) {
        this.outboundTime = outboundTime;
    }
    
    /**
     * 获取出库量
     *
     * @return 出库量
     */
    public BigDecimal getOutboundQuantity() {
        return outboundQuantity;
    }
    
    /**
     * 设置出库量
     *
     * @param outboundQuantity 出库量
     */
    public void setOutboundQuantity(BigDecimal outboundQuantity) {
        this.outboundQuantity = outboundQuantity;
    }
    
    /**
     * 获取运送部门id
     *
     * @return 运送部门id
     */
    public String getTransportOrgId() {
        return transportOrgId;
    }
    
    /**
     * 设置运送部门id
     *
     * @param transportOrgId 运送部门id
     */
    public void setTransportOrgId(String transportOrgId) {
        this.transportOrgId = transportOrgId;
    }
    
    /**
     * 获取运送部门name
     *
     * @return 运送部门name
     */
    public String getTransportOrgName() {
        return transportOrgName;
    }
    
    /**
     * 设置运送部门name
     *
     * @param transportOrgName 运送部门name
     */
    public void setTransportOrgName(String transportOrgName) {
        this.transportOrgName = transportOrgName;
    }
    
    /**
     * 获取运送部门经办人id
     *
     * @return 运送部门经办人id
     */
    public String getTransportPersonId() {
        return transportPersonId;
    }
    
    /**
     * 设置运送部门经办人id
     *
     * @param transportPersonId 运送部门经办人id
     */
    public void setTransportPersonId(String transportPersonId) {
        this.transportPersonId = transportPersonId;
    }
    
    /**
     * 获取运送部门经办人name
     *
     * @return 运送部门经办人name
     */
    public String getTransportPersonName() {
        return transportPersonName;
    }
    
    /**
     * 设置运送部门经办人name
     *
     * @param transportPersonName 运送部门经办人name
     */
    public void setTransportPersonName(String transportPersonName) {
        this.transportPersonName = transportPersonName;
    }
    
    /**
     * 获取出库部门id
     *
     * @return 出库部门id
     */
    public String getOutboundOrgId() {
        return outboundOrgId;
    }
    
    /**
     * 设置出库部门id
     *
     * @param outboundOrgId 出库部门id
     */
    public void setOutboundOrgId(String outboundOrgId) {
        this.outboundOrgId = outboundOrgId;
    }
    
    /**
     * 获取出库部门name
     *
     * @return 出库部门name
     */
    public String getOutboundOrgName() {
        return outboundOrgName;
    }
    
    /**
     * 设置出库部门name
     *
     * @param outboundOrgName 出库部门name
     */
    public void setOutboundOrgName(String outboundOrgName) {
        this.outboundOrgName = outboundOrgName;
    }
    
    /**
     * 获取出库部门经办人id
     *
     * @return 出库部门经办人id
     */
    public String getOutboundPersonId() {
        return outboundPersonId;
    }
    
    /**
     * 设置出库部门经办人id
     *
     * @param outboundPersonId 出库部门经办人id
     */
    public void setOutboundPersonId(String outboundPersonId) {
        this.outboundPersonId = outboundPersonId;
    }
    
    /**
     * 获取出库部门经办人name
     *
     * @return 出库部门经办人name
     */
    public String getOutboundPersonName() {
        return outboundPersonName;
    }
    
    /**
     * 设置出库部门经办人name
     *
     * @param outboundPersonName 出库部门经办人name
     */
    public void setOutboundPersonName(String outboundPersonName) {
        this.outboundPersonName = outboundPersonName;
    }
    
    /**
     * 获取贮存设施字典编码
     *
     * @return 贮存设施字典编码
     */
    public String getStoragePositionCode() {
        return storagePositionCode;
    }
    
    /**
     * 设置贮存设施字典编码
     *
     * @param storagePositionCode 贮存设施字典编码
     */
    public void setStoragePositionCode(String storagePositionCode) {
        this.storagePositionCode = storagePositionCode;
    }
    
    /**
     * 获取贮存设施字典名称
     *
     * @return 贮存设施字典名称
     */
    public String getStoragePositionName() {
        return storagePositionName;
    }
    
    /**
     * 设置贮存设施字典名称
     *
     * @param storagePositionName 贮存设施字典名称
     */
    public void setStoragePositionName(String storagePositionName) {
        this.storagePositionName = storagePositionName;
    }
    
    /**
     * 获取贮存设施字典类型
     *
     * @return 贮存设施字典类型
     */
    public String getStoragePositionType() {
        return storagePositionType;
    }
    
    /**
     * 设置贮存设施字典类型
     *
     * @param storagePositionType 贮存设施字典类型
     */
    public void setStoragePositionType(String storagePositionType) {
        this.storagePositionType = storagePositionType;
    }
    
    /**
     * 获取容器/包装编码和类型code
     *
     * @return 容器/包装编码和类型code
     */
    public String getContainerCode() {
        return containerCode;
    }
    
    /**
     * 设置容器/包装编码和类型code
     *
     * @param containerCode 容器/包装编码和类型code
     */
    public void setContainerCode(String containerCode) {
        this.containerCode = containerCode;
    }
    
    /**
     * 获取容器/包装编码和类型name
     *
     * @return 容器/包装编码和类型name
     */
    public String getContainerName() {
        return containerName;
    }
    
    /**
     * 设置容器/包装编码和类型name
     *
     * @param containerName 容器/包装编码和类型name
     */
    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }
    
    /**
     * 获取状态(0待处置;1已处置)
     *
     * @return 状态(0待处置 ; 1已处置)
     */
    public String getStatus() {
        return status;
    }
    
    /**
     * 设置状态(0待处置;1已处置)
     *
     * @param status 状态(0待处置;1已处置)
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * 获取计量单位code
     *
     * @return 计量单位code
     */
    public String getMeasurementUnitCode() {
        return measurementUnitCode;
    }
    
    /**
     * 设置计量单位code
     *
     * @param measurementUnitCode 计量单位code
     */
    public void setMeasurementUnitCode(String measurementUnitCode) {
        this.measurementUnitCode = measurementUnitCode;
    }
    
    /**
     * 获取计量单位name
     *
     * @return 计量单位name
     */
    public String getMeasurementUnitName() {
        return measurementUnitName;
    }
    
    /**
     * 设置计量单位name
     *
     * @param measurementUnitName 计量单位name
     */
    public void setMeasurementUnitName(String measurementUnitName) {
        this.measurementUnitName = measurementUnitName;
    }
    
    /**
     * 获取容器/包装个数
     *
     * @return 容器/包装个数
     */
    public Integer getContainerNumber() {
        return containerNumber;
    }
    
    /**
     * 设置容器/包装个数
     *
     * @param containerNumber 容器/包装个数
     */
    public void setContainerNumber(Integer containerNumber) {
        this.containerNumber = containerNumber;
    }
    
    /**
     * 获取入库批次编码
     *
     * @return 入库批次编码
     */
    public String getWarehousingCodes() {
        return warehousingCodes;
    }
    
    /**
     * 设置入库批次编码
     *
     * @param warehousingCodes 入库批次编码
     */
    public void setWarehousingCodes(String warehousingCodes) {
        this.warehousingCodes = warehousingCodes;
    }
    
    /**
     * 获取去向单位id
     *
     * @return 去向单位id
     */
    public String getDestinationOrgId() {
        return destinationOrgId;
    }
    
    /**
     * 设置去向单位id
     *
     * @param destinationOrgId 去向单位id
     */
    public void setDestinationOrgId(String destinationOrgId) {
        this.destinationOrgId = destinationOrgId;
    }
    
    /**
     * 获取去向单位name
     *
     * @return 去向单位name
     */
    public String getDestinationOrgName() {
        return destinationOrgName;
    }
    
    /**
     * 设置去向单位name
     *
     * @param destinationOrgName 去向单位name
     */
    public void setDestinationOrgName(String destinationOrgName) {
        this.destinationOrgName = destinationOrgName;
    }
    
    /**
     * 获取附件
     *
     * @return 附件
     */
    public String getFileId() {
        return fileId;
    }
    
    /**
     * 设置附件
     *
     * @param fileId 附件
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
    
    /**
     * 获取编号年份标记
     *
     * @return 编号年份标记
     */
    public String getYearTag() {
        return yearTag;
    }
    
    /**
     * 设置编号年份标记
     *
     * @param yearTag 编号年份标记
     */
    public void setYearTag(String yearTag) {
        this.yearTag = yearTag;
    }
    
    /**
     * 获取每个标记的最大序号
     *
     * @return 每个标记的最大序号
     */
    public Integer getSerialNumber() {
        return serialNumber;
    }
    
    /**
     * 设置每个标记的最大序号
     *
     * @param serialNumber 每个标记的最大序号
     */
    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }
    
    /**
     * 获取租户id
     *
     * @return 租户id
     */
    public String getTenantId() {
        return tenantId;
    }
    
    /**
     * 设置租户id
     *
     * @param tenantId 租户id
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
    
    /**
     * 获取租户名称
     *
     * @return 租户名称
     */
    public String getTenantName() {
        return tenantName;
    }
    
    /**
     * 设置租户名称
     *
     * @param tenantName 租户名称
     */
    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }
    
    /**
     * 转换成固废管理-危废出库VO对象
     *
     * @return 固废管理-危废出库VO对象
     */
    @Override
    protected HseHazardousWasteOutboundVO convertToVo() {
        HseHazardousWasteOutboundVO hseHazardousWasteOutboundVO = new HseHazardousWasteOutboundVO();
        BeanUtils.copyProperties(this, hseHazardousWasteOutboundVO);
        return hseHazardousWasteOutboundVO;
    }
    
    /**
     * 转换成固废管理-危废出库DTO对象
     *
     * @return 固废管理-危废出库DTO对象
     */
    @Override
    protected HseHazardousWasteOutboundDTO convertToDto() {
        HseHazardousWasteOutboundDTO hseHazardousWasteOutboundDTO = new HseHazardousWasteOutboundDTO();
        BeanUtils.copyProperties(this, hseHazardousWasteOutboundDTO);
        return hseHazardousWasteOutboundDTO;
    }
    
    @Override
    public String toString() {
        return "HseHazardousWasteOutbound{" +
                
                "appId='" + super.getAppId() + "\'," +
                "wasteBaseId='" + wasteBaseId + "\'," +
                "warehousingIds='" + warehousingIds + "\'," +
                "outboundCode='" + outboundCode + "\'," +
                "outboundTime='" + outboundTime + "\'," +
                "outboundQuantity='" + outboundQuantity + "\'," +
                "transportOrgId='" + transportOrgId + "\'," +
                "transportOrgName='" + transportOrgName + "\'," +
                "transportPersonId='" + transportPersonId + "\'," +
                "transportPersonName='" + transportPersonName + "\'," +
                "outboundOrgId='" + outboundOrgId + "\'," +
                "outboundOrgName='" + outboundOrgName + "\'," +
                "outboundPersonId='" + outboundPersonId + "\'," +
                "outboundPersonName='" + outboundPersonName + "\'," +
                "storagePositionCode='" + storagePositionCode + "\'," +
                "storagePositionName='" + storagePositionName + "\'," +
                "storagePositionType='" + storagePositionType + "\'," +
                "containerCode='" + containerCode + "\'," +
                "containerName='" + containerName + "\'," +
                "status='" + status + "\'," +
                "measurementUnitCode='" + measurementUnitCode + "\'," +
                "measurementUnitName='" + measurementUnitName + "\'," +
                "containerNumber='" + containerNumber + "\'," +
                "warehousingCodes='" + warehousingCodes + "\'," +
                "destinationOrgId='" + destinationOrgId + "\'," +
                "destinationOrgName='" + destinationOrgName + "\'," +
                "fileId='" + fileId + "\'," +
                "yearTag='" + yearTag + "\'," +
                "serialNumber='" + serialNumber + "\'," +
                "tenantId='" + tenantId + "\'," +
                "tenantName='" + tenantName + "\'," +
                "remark='" + super.getRemark() + "\'," +
                "createBy='" + super.getCreateBy() + "\'," +
                "createTime='" + super.getCreateTime() + "\'," +
                "updateBy='" + super.getUpdateBy() + "\'," +
                "updateTime='" + super.getUpdateTime() + "\'," +
                "delFlag='" + super.getDelFlag() + "\'" +
                '}';
    }
}