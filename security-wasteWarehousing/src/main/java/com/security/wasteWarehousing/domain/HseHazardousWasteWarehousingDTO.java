package com.security.wasteWarehousing.domain;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 固废管理-危废入库实体交互类
 * {"sid":"流水ID","appId":"appId","wasteBaseId":"关联的废物id","generationIds":"关联的产生ids","warehousingCode":"入库批次编码","transportOrgId":"运送部门id","transportOrgName":"运送部门name","transportPersonId":"运送部门经办人id","transportPersonName":"运送部门经办人name","storageOrgId":"贮存部门id","storageOrgName":"贮存部门name","storagePersonId":"贮存部门经办人id","storagePersonName":"贮存部门经办人name","storagePositionCode":"贮存设施字典编码","storagePositionName":"贮存设施字典名称","storagePositionType":"贮存设施字典类型","containerCode":"容器/包装编码和类型code","containerName":"容器/包装编码和类型name","status":"状态(0未出库;1部分出库;2已出库)","measurementUnitCode":"计量单位code","measurementUnitName":"计量单位name","containerNumber":"容器/包装个数","warehousingQuantity":"入库量","residueQuantity":"剩余量","warehousingTime":"入库时间","generationCodes":"产生批次编码","fileId":"附件","yearTag":"编号年份标记","serialNumber":"每个标记的最大序号","remark":"备注","updateBy":"修改者","updateTime":"更新时间","createBy":"创建者","createTime":"创建时间","tenantId":"租户id","tenantName":"租户名称","delFlag":"删除标记"}
 *
 * @author A
 * @version v 0.1
 * @File HseHazardousWasteWarehousingDTO.java
 * @Desc 固废管理-危废入库
 * @DateTime 2023-07-14 10:30:25
 * @Encoding UTF-8
 * @Description Copyright (c) 2004-2023 All Rights Reserved.
 */
public class HseHazardousWasteWarehousingDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    
    
    /**
     * 流水ID
     */
    private Long sid;
    
    /**
     * 应用ID
     */
    private Integer appId;
    
    
    /**
     * 备注信息
     */
    private String remark;
    
    /**
     * 创建者
     */
    private String createBy;
    
    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    
    /**
     * 修改者
     */
    private String updateBy;
    
    /**
     * 修改时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;
    
    /**
     * 删除标记
     */
    private Integer delFlag;
    
    /**
     * 获取流水ID
     *
     * @return 流水ID
     */
    public Long getSid() {
        return sid;
    }
    
    /**
     * 设置流水ID
     *
     * @param sid 流水ID
     */
    public void setSid(Long sid) {
        this.sid = sid;
    }
    
    /**
     * 获取应用ID
     *
     * @return 应用ID
     */
    public Integer getAppId() {
        return appId;
    }
    
    /**
     * 设置应用ID
     *
     * @param appId 应用ID
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
    }
    
    /**
     * 获取备注信息
     *
     * @return 备注信息
     */
    public String getRemark() {
        return remark;
    }
    
    /**
     * 设置备注信息
     *
     * @param remark 备注信息
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    /**
     * 获取创建者
     *
     * @return 创建者
     */
    public String getCreateBy() {
        return createBy;
    }
    
    /**
     * 设置创建者
     *
     * @param createBy 创建者
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    
    /**
     * 获取创建时间
     *
     * @return 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }
    
    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    /**
     * 获取修改者
     *
     * @return 修改者
     */
    public String getUpdateBy() {
        return updateBy;
    }
    
    /**
     * 设置修改者
     *
     * @param updateBy 修改者
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
    
    /**
     * 获取修改时间
     *
     * @return 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }
    
    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    /**
     * 获取删除标记
     *
     * @return 删除标记
     */
    public Integer getDelFlag() {
        return delFlag;
    }
    
    /**
     * 设置删除标记
     *
     * @param delFlag 删除标记
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
    
    
    
    
    
    /**
     * 关联的废物id
     */
    private String wasteBaseId;
    
    /**
     * 关联的产生ids
     */
    private String generationIds;
    
    /**
     * 入库批次编码
     */
    private String warehousingCode;
    
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
     * 贮存部门id
     */
    private String storageOrgId;
    
    /**
     * 贮存部门name
     */
    private String storageOrgName;
    
    /**
     * 贮存部门经办人id
     */
    private String storagePersonId;
    
    /**
     * 贮存部门经办人name
     */
    private String storagePersonName;
    
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
     * 状态(0未出库;1部分出库;2已出库)
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
     * 入库量
     */
    private BigDecimal warehousingQuantity;
    
    /**
     * 剩余量
     */
    private BigDecimal residueQuantity;
    
    /**
     * 入库时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date warehousingTime;
    
    /**
     * 产生批次编码
     */
    private String generationCodes;
    
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
     * 设置关联的废物id
     *
     * @param wasteBaseId 关联的废物id
     */
    public HseHazardousWasteWarehousingDTO wasteBaseId(String wasteBaseId) {
        this.wasteBaseId = wasteBaseId;
        return this;
    }
    
    
    /**
     * 获取关联的产生ids
     *
     * @return 关联的产生ids
     */
    public String getGenerationIds() {
        return generationIds;
    }
    
    /**
     * 设置关联的产生ids
     *
     * @param generationIds 关联的产生ids
     */
    public void setGenerationIds(String generationIds) {
        this.generationIds = generationIds;
    }
    
    /**
     * 设置关联的产生ids
     *
     * @param generationIds 关联的产生ids
     */
    public HseHazardousWasteWarehousingDTO generationIds(String generationIds) {
        this.generationIds = generationIds;
        return this;
    }
    
    
    /**
     * 获取入库批次编码
     *
     * @return 入库批次编码
     */
    public String getWarehousingCode() {
        return warehousingCode;
    }
    
    /**
     * 设置入库批次编码
     *
     * @param warehousingCode 入库批次编码
     */
    public void setWarehousingCode(String warehousingCode) {
        this.warehousingCode = warehousingCode;
    }
    
    /**
     * 设置入库批次编码
     *
     * @param warehousingCode 入库批次编码
     */
    public HseHazardousWasteWarehousingDTO warehousingCode(String warehousingCode) {
        this.warehousingCode = warehousingCode;
        return this;
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
     * 设置运送部门id
     *
     * @param transportOrgId 运送部门id
     */
    public HseHazardousWasteWarehousingDTO transportOrgId(String transportOrgId) {
        this.transportOrgId = transportOrgId;
        return this;
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
     * 设置运送部门name
     *
     * @param transportOrgName 运送部门name
     */
    public HseHazardousWasteWarehousingDTO transportOrgName(String transportOrgName) {
        this.transportOrgName = transportOrgName;
        return this;
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
     * 设置运送部门经办人id
     *
     * @param transportPersonId 运送部门经办人id
     */
    public HseHazardousWasteWarehousingDTO transportPersonId(String transportPersonId) {
        this.transportPersonId = transportPersonId;
        return this;
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
     * 设置运送部门经办人name
     *
     * @param transportPersonName 运送部门经办人name
     */
    public HseHazardousWasteWarehousingDTO transportPersonName(String transportPersonName) {
        this.transportPersonName = transportPersonName;
        return this;
    }
    
    
    /**
     * 获取贮存部门id
     *
     * @return 贮存部门id
     */
    public String getStorageOrgId() {
        return storageOrgId;
    }
    
    /**
     * 设置贮存部门id
     *
     * @param storageOrgId 贮存部门id
     */
    public void setStorageOrgId(String storageOrgId) {
        this.storageOrgId = storageOrgId;
    }
    
    /**
     * 设置贮存部门id
     *
     * @param storageOrgId 贮存部门id
     */
    public HseHazardousWasteWarehousingDTO storageOrgId(String storageOrgId) {
        this.storageOrgId = storageOrgId;
        return this;
    }
    
    
    /**
     * 获取贮存部门name
     *
     * @return 贮存部门name
     */
    public String getStorageOrgName() {
        return storageOrgName;
    }
    
    /**
     * 设置贮存部门name
     *
     * @param storageOrgName 贮存部门name
     */
    public void setStorageOrgName(String storageOrgName) {
        this.storageOrgName = storageOrgName;
    }
    
    /**
     * 设置贮存部门name
     *
     * @param storageOrgName 贮存部门name
     */
    public HseHazardousWasteWarehousingDTO storageOrgName(String storageOrgName) {
        this.storageOrgName = storageOrgName;
        return this;
    }
    
    
    /**
     * 获取贮存部门经办人id
     *
     * @return 贮存部门经办人id
     */
    public String getStoragePersonId() {
        return storagePersonId;
    }
    
    /**
     * 设置贮存部门经办人id
     *
     * @param storagePersonId 贮存部门经办人id
     */
    public void setStoragePersonId(String storagePersonId) {
        this.storagePersonId = storagePersonId;
    }
    
    /**
     * 设置贮存部门经办人id
     *
     * @param storagePersonId 贮存部门经办人id
     */
    public HseHazardousWasteWarehousingDTO storagePersonId(String storagePersonId) {
        this.storagePersonId = storagePersonId;
        return this;
    }
    
    
    /**
     * 获取贮存部门经办人name
     *
     * @return 贮存部门经办人name
     */
    public String getStoragePersonName() {
        return storagePersonName;
    }
    
    /**
     * 设置贮存部门经办人name
     *
     * @param storagePersonName 贮存部门经办人name
     */
    public void setStoragePersonName(String storagePersonName) {
        this.storagePersonName = storagePersonName;
    }
    
    /**
     * 设置贮存部门经办人name
     *
     * @param storagePersonName 贮存部门经办人name
     */
    public HseHazardousWasteWarehousingDTO storagePersonName(String storagePersonName) {
        this.storagePersonName = storagePersonName;
        return this;
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
     * 设置贮存设施字典编码
     *
     * @param storagePositionCode 贮存设施字典编码
     */
    public HseHazardousWasteWarehousingDTO storagePositionCode(String storagePositionCode) {
        this.storagePositionCode = storagePositionCode;
        return this;
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
     * 设置贮存设施字典名称
     *
     * @param storagePositionName 贮存设施字典名称
     */
    public HseHazardousWasteWarehousingDTO storagePositionName(String storagePositionName) {
        this.storagePositionName = storagePositionName;
        return this;
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
     * 设置贮存设施字典类型
     *
     * @param storagePositionType 贮存设施字典类型
     */
    public HseHazardousWasteWarehousingDTO storagePositionType(String storagePositionType) {
        this.storagePositionType = storagePositionType;
        return this;
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
     * 设置容器/包装编码和类型code
     *
     * @param containerCode 容器/包装编码和类型code
     */
    public HseHazardousWasteWarehousingDTO containerCode(String containerCode) {
        this.containerCode = containerCode;
        return this;
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
     * 设置容器/包装编码和类型name
     *
     * @param containerName 容器/包装编码和类型name
     */
    public HseHazardousWasteWarehousingDTO containerName(String containerName) {
        this.containerName = containerName;
        return this;
    }
    
    
    /**
     * 获取状态(0未出库;1部分出库;2已出库)
     *
     * @return 状态(0未出库 ; 1部分出库 ; 2已出库)
     */
    public String getStatus() {
        return status;
    }
    
    /**
     * 设置状态(0未出库;1部分出库;2已出库)
     *
     * @param status 状态(0未出库;1部分出库;2已出库)
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * 设置状态(0未出库;1部分出库;2已出库)
     *
     * @param status 状态(0未出库;1部分出库;2已出库)
     */
    public HseHazardousWasteWarehousingDTO status(String status) {
        this.status = status;
        return this;
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
     * 设置计量单位code
     *
     * @param measurementUnitCode 计量单位code
     */
    public HseHazardousWasteWarehousingDTO measurementUnitCode(String measurementUnitCode) {
        this.measurementUnitCode = measurementUnitCode;
        return this;
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
     * 设置计量单位name
     *
     * @param measurementUnitName 计量单位name
     */
    public HseHazardousWasteWarehousingDTO measurementUnitName(String measurementUnitName) {
        this.measurementUnitName = measurementUnitName;
        return this;
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
     * 设置容器/包装个数
     *
     * @param containerNumber 容器/包装个数
     */
    public HseHazardousWasteWarehousingDTO containerNumber(Integer containerNumber) {
        this.containerNumber = containerNumber;
        return this;
    }
    
    
    /**
     * 获取入库量
     *
     * @return 入库量
     */
    public BigDecimal getWarehousingQuantity() {
        return warehousingQuantity;
    }
    
    /**
     * 设置入库量
     *
     * @param warehousingQuantity 入库量
     */
    public void setWarehousingQuantity(BigDecimal warehousingQuantity) {
        this.warehousingQuantity = warehousingQuantity;
    }
    
    /**
     * 设置入库量
     *
     * @param warehousingQuantity 入库量
     */
    public HseHazardousWasteWarehousingDTO warehousingQuantity(BigDecimal warehousingQuantity) {
        this.warehousingQuantity = warehousingQuantity;
        return this;
    }
    
    
    /**
     * 获取剩余量
     *
     * @return 剩余量
     */
    public BigDecimal getResidueQuantity() {
        return residueQuantity;
    }
    
    /**
     * 设置剩余量
     *
     * @param residueQuantity 剩余量
     */
    public void setResidueQuantity(BigDecimal residueQuantity) {
        this.residueQuantity = residueQuantity;
    }
    
    /**
     * 设置剩余量
     *
     * @param residueQuantity 剩余量
     */
    public HseHazardousWasteWarehousingDTO residueQuantity(BigDecimal residueQuantity) {
        this.residueQuantity = residueQuantity;
        return this;
    }
    
    
    /**
     * 获取入库时间
     *
     * @return 入库时间
     */
    public Date getWarehousingTime() {
        return warehousingTime;
    }
    
    /**
     * 设置入库时间
     *
     * @param warehousingTime 入库时间
     */
    public void setWarehousingTime(Date warehousingTime) {
        this.warehousingTime = warehousingTime;
    }
    
    /**
     * 设置入库时间
     *
     * @param warehousingTime 入库时间
     */
    public HseHazardousWasteWarehousingDTO warehousingTime(Date warehousingTime) {
        this.warehousingTime = warehousingTime;
        return this;
    }
    
    
    /**
     * 获取产生批次编码
     *
     * @return 产生批次编码
     */
    public String getGenerationCodes() {
        return generationCodes;
    }
    
    /**
     * 设置产生批次编码
     *
     * @param generationCodes 产生批次编码
     */
    public void setGenerationCodes(String generationCodes) {
        this.generationCodes = generationCodes;
    }
    
    /**
     * 设置产生批次编码
     *
     * @param generationCodes 产生批次编码
     */
    public HseHazardousWasteWarehousingDTO generationCodes(String generationCodes) {
        this.generationCodes = generationCodes;
        return this;
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
     * 设置附件
     *
     * @param fileId 附件
     */
    public HseHazardousWasteWarehousingDTO fileId(String fileId) {
        this.fileId = fileId;
        return this;
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
     * 设置编号年份标记
     *
     * @param yearTag 编号年份标记
     */
    public HseHazardousWasteWarehousingDTO yearTag(String yearTag) {
        this.yearTag = yearTag;
        return this;
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
     * 设置每个标记的最大序号
     *
     * @param serialNumber 每个标记的最大序号
     */
    public HseHazardousWasteWarehousingDTO serialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
        return this;
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
     * 设置租户id
     *
     * @param tenantId 租户id
     */
    public HseHazardousWasteWarehousingDTO tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
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
     * 设置租户名称
     *
     * @param tenantName 租户名称
     */
    public HseHazardousWasteWarehousingDTO tenantName(String tenantName) {
        this.tenantName = tenantName;
        return this;
    }
    
    
    
}