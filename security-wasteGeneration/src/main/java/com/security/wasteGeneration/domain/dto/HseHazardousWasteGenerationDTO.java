package com.security.wasteGeneration.domain.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.cybstar.base.CybDtoBase;
import com.cybstar.base.secureBase.solidWaste.hazardousWaste.entity.HseHazardousWasteGeneration;
import com.cybstar.base.secureBase.solidWaste.hazardousWaste.vo.HseHazardousWasteGenerationVO;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 固废管理-危废产生实体交互类
 * {"sid":"流水ID","appId":"appId","wasteBaseId":"关联的废物id","generationCode":"产生批次编码","generationOrgId":"产生部门id","generationOrgName":"产生部门name","orgPersonId":"产生部门经办人id","orgPersonName":"产生部门经办人name","wastePositionCode":"产生危险废物设施编码","wastePositionName":"产生危险废物设施名称","containerCode":"容器/包装编码和类型code","containerName":"容器/包装编码和类型name","status":"状态","destination":"去向","measurementUnitCode":"计量单位code","measurementUnitName":"计量单位name","containerNumber":"容器/包装个数","generationQuantity":"产生量/吨","generationTime":"产生时间","fileId":"附件","yearTag":"编号年份标记","serialNumber":"每个标记的最大序号","remark":"备注","updateBy":"修改者","updateTime":"更新时间","createBy":"创建者","createTime":"创建时间","tenantId":"租户id","tenantName":"租户名称","delFlag":"删除标记"}
 *
 * @author A
 * @version v 0.1
 * @File HseHazardousWasteGenerationDTO.java
 * @Desc 固废管理-危废产生
 * @DateTime 2023-07-12 15:06:26
 * @Encoding UTF-8
 * @Description Copyright (c) 2004-2023 All Rights Reserved.
 */
public class HseHazardousWasteGenerationDTO extends CybDtoBase<HseHazardousWasteGeneration, HseHazardousWasteGenerationDTO, HseHazardousWasteGenerationVO> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 关联的废物id
     */
    private String wasteBaseId;
    
    /**
     * 产生批次编码
     */
    private String generationCode;
    
    /**
     * 产生部门id
     */
    private String generationOrgId;
    
    /**
     * 产生部门name
     */
    private String generationOrgName;
    
    /**
     * 产生部门经办人id
     */
    private String orgPersonId;
    
    /**
     * 产生部门经办人name
     */
    private String orgPersonName;
    
    /**
     * 产生危险废物设施编码
     */
    private String wastePositionCode;
    
    /**
     * 产生危险废物设施名称
     */
    private String wastePositionName;
    
    /**
     * 容器/包装编码和类型code
     */
    private String containerCode;
    
    /**
     * 容器/包装编码和类型name
     */
    private String containerName;
    
    /**
     * 状态
     */
    private String status;
    
    /**
     * 去向
     */
    private String destination;
    
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
     * 产生量/吨
     */
    private BigDecimal generationQuantity;
    
    /**
     * 产生时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date generationTime;
    
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
    public HseHazardousWasteGenerationDTO wasteBaseId(String wasteBaseId) {
        this.wasteBaseId = wasteBaseId;
        return this;
    }
    
    
    /**
     * 获取产生批次编码
     *
     * @return 产生批次编码
     */
    public String getGenerationCode() {
        return generationCode;
    }
    
    /**
     * 设置产生批次编码
     *
     * @param generationCode 产生批次编码
     */
    public void setGenerationCode(String generationCode) {
        this.generationCode = generationCode;
    }
    
    /**
     * 设置产生批次编码
     *
     * @param generationCode 产生批次编码
     */
    public HseHazardousWasteGenerationDTO generationCode(String generationCode) {
        this.generationCode = generationCode;
        return this;
    }
    
    
    /**
     * 获取产生部门id
     *
     * @return 产生部门id
     */
    public String getGenerationOrgId() {
        return generationOrgId;
    }
    
    /**
     * 设置产生部门id
     *
     * @param generationOrgId 产生部门id
     */
    public void setGenerationOrgId(String generationOrgId) {
        this.generationOrgId = generationOrgId;
    }
    
    /**
     * 设置产生部门id
     *
     * @param generationOrgId 产生部门id
     */
    public HseHazardousWasteGenerationDTO generationOrgId(String generationOrgId) {
        this.generationOrgId = generationOrgId;
        return this;
    }
    
    
    /**
     * 获取产生部门name
     *
     * @return 产生部门name
     */
    public String getGenerationOrgName() {
        return generationOrgName;
    }
    
    /**
     * 设置产生部门name
     *
     * @param generationOrgName 产生部门name
     */
    public void setGenerationOrgName(String generationOrgName) {
        this.generationOrgName = generationOrgName;
    }
    
    /**
     * 设置产生部门name
     *
     * @param generationOrgName 产生部门name
     */
    public HseHazardousWasteGenerationDTO generationOrgName(String generationOrgName) {
        this.generationOrgName = generationOrgName;
        return this;
    }
    
    
    /**
     * 获取产生部门经办人id
     *
     * @return 产生部门经办人id
     */
    public String getOrgPersonId() {
        return orgPersonId;
    }
    
    /**
     * 设置产生部门经办人id
     *
     * @param orgPersonId 产生部门经办人id
     */
    public void setOrgPersonId(String orgPersonId) {
        this.orgPersonId = orgPersonId;
    }
    
    /**
     * 设置产生部门经办人id
     *
     * @param orgPersonId 产生部门经办人id
     */
    public HseHazardousWasteGenerationDTO orgPersonId(String orgPersonId) {
        this.orgPersonId = orgPersonId;
        return this;
    }
    
    
    /**
     * 获取产生部门经办人name
     *
     * @return 产生部门经办人name
     */
    public String getOrgPersonName() {
        return orgPersonName;
    }
    
    /**
     * 设置产生部门经办人name
     *
     * @param orgPersonName 产生部门经办人name
     */
    public void setOrgPersonName(String orgPersonName) {
        this.orgPersonName = orgPersonName;
    }
    
    /**
     * 设置产生部门经办人name
     *
     * @param orgPersonName 产生部门经办人name
     */
    public HseHazardousWasteGenerationDTO orgPersonName(String orgPersonName) {
        this.orgPersonName = orgPersonName;
        return this;
    }
    
    
    /**
     * 获取产生危险废物设施编码
     *
     * @return 产生危险废物设施编码
     */
    public String getWastePositionCode() {
        return wastePositionCode;
    }
    
    /**
     * 设置产生危险废物设施编码
     *
     * @param wastePositionCode 产生危险废物设施编码
     */
    public void setWastePositionCode(String wastePositionCode) {
        this.wastePositionCode = wastePositionCode;
    }
    
    /**
     * 设置产生危险废物设施编码
     *
     * @param wastePositionCode 产生危险废物设施编码
     */
    public HseHazardousWasteGenerationDTO wastePositionCode(String wastePositionCode) {
        this.wastePositionCode = wastePositionCode;
        return this;
    }
    
    
    /**
     * 获取产生危险废物设施名称
     *
     * @return 产生危险废物设施名称
     */
    public String getWastePositionName() {
        return wastePositionName;
    }
    
    /**
     * 设置产生危险废物设施名称
     *
     * @param wastePositionName 产生危险废物设施名称
     */
    public void setWastePositionName(String wastePositionName) {
        this.wastePositionName = wastePositionName;
    }
    
    /**
     * 设置产生危险废物设施名称
     *
     * @param wastePositionName 产生危险废物设施名称
     */
    public HseHazardousWasteGenerationDTO wastePositionName(String wastePositionName) {
        this.wastePositionName = wastePositionName;
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
    public HseHazardousWasteGenerationDTO containerCode(String containerCode) {
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
    public HseHazardousWasteGenerationDTO containerName(String containerName) {
        this.containerName = containerName;
        return this;
    }
    
    
    /**
     * 获取状态
     *
     * @return 状态
     */
    public String getStatus() {
        return status;
    }
    
    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * 设置状态
     *
     * @param status 状态
     */
    public HseHazardousWasteGenerationDTO status(String status) {
        this.status = status;
        return this;
    }
    
    
    /**
     * 获取去向
     *
     * @return 去向
     */
    public String getDestination() {
        return destination;
    }
    
    /**
     * 设置去向
     *
     * @param destination 去向
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }
    
    /**
     * 设置去向
     *
     * @param destination 去向
     */
    public HseHazardousWasteGenerationDTO destination(String destination) {
        this.destination = destination;
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
    public HseHazardousWasteGenerationDTO measurementUnitCode(String measurementUnitCode) {
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
    public HseHazardousWasteGenerationDTO measurementUnitName(String measurementUnitName) {
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
    public HseHazardousWasteGenerationDTO containerNumber(Integer containerNumber) {
        this.containerNumber = containerNumber;
        return this;
    }
    
    
    /**
     * 获取产生量/吨
     *
     * @return 产生量/吨
     */
    public BigDecimal getGenerationQuantity() {
        return generationQuantity;
    }
    
    /**
     * 设置产生量/吨
     *
     * @param generationQuantity 产生量/吨
     */
    public void setGenerationQuantity(BigDecimal generationQuantity) {
        this.generationQuantity = generationQuantity;
    }
    
    /**
     * 设置产生量/吨
     *
     * @param generationQuantity 产生量/吨
     */
    public HseHazardousWasteGenerationDTO generationQuantity(BigDecimal generationQuantity) {
        this.generationQuantity = generationQuantity;
        return this;
    }
    
    
    /**
     * 获取产生时间
     *
     * @return 产生时间
     */
    public Date getGenerationTime() {
        return generationTime;
    }
    
    /**
     * 设置产生时间
     *
     * @param generationTime 产生时间
     */
    public void setGenerationTime(Date generationTime) {
        this.generationTime = generationTime;
    }
    
    /**
     * 设置产生时间
     *
     * @param generationTime 产生时间
     */
    public HseHazardousWasteGenerationDTO generationTime(Date generationTime) {
        this.generationTime = generationTime;
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
    public HseHazardousWasteGenerationDTO fileId(String fileId) {
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
    public HseHazardousWasteGenerationDTO yearTag(String yearTag) {
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
    public HseHazardousWasteGenerationDTO serialNumber(Integer serialNumber) {
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
    public HseHazardousWasteGenerationDTO tenantId(String tenantId) {
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
    public HseHazardousWasteGenerationDTO tenantName(String tenantName) {
        this.tenantName = tenantName;
        return this;
    }
    
    
    public HseHazardousWasteGenerationDTO appId(Integer appId) {
        super.setAppId(appId);
        return this;
    }
    
    
    /**
     * 转换成固废管理-危废产生Entity对象
     *
     * @return 固废管理-危废产生Entity对象
     */
    @Override
    protected HseHazardousWasteGeneration convertToEntity() {
        HseHazardousWasteGeneration hseHazardousWasteGeneration = new HseHazardousWasteGeneration();
        BeanUtils.copyProperties(this, hseHazardousWasteGeneration);
        return hseHazardousWasteGeneration;
    }
    
    /**
     * 转换成固废管理-危废产生VO对象
     *
     * @return 固废管理-危废产生VO对象
     */
    @Override
    protected HseHazardousWasteGenerationVO convertToVo() {
        HseHazardousWasteGenerationVO hseHazardousWasteGenerationVO = new HseHazardousWasteGenerationVO();
        BeanUtils.copyProperties(this, hseHazardousWasteGenerationVO);
        return hseHazardousWasteGenerationVO;
    }
    
    @Override
    public String toString() {
        return "HseHazardousWasteGenerationDTO{" +
                
                "appId='" + super.getAppId() + "\'," +
                "wasteBaseId='" + wasteBaseId + "\'," +
                "generationCode='" + generationCode + "\'," +
                "generationOrgId='" + generationOrgId + "\'," +
                "generationOrgName='" + generationOrgName + "\'," +
                "orgPersonId='" + orgPersonId + "\'," +
                "orgPersonName='" + orgPersonName + "\'," +
                "wastePositionCode='" + wastePositionCode + "\'," +
                "wastePositionName='" + wastePositionName + "\'," +
                "containerCode='" + containerCode + "\'," +
                "containerName='" + containerName + "\'," +
                "status='" + status + "\'," +
                "destination='" + destination + "\'," +
                "measurementUnitCode='" + measurementUnitCode + "\'," +
                "measurementUnitName='" + measurementUnitName + "\'," +
                "containerNumber='" + containerNumber + "\'," +
                "generationQuantity='" + generationQuantity + "\'," +
                "generationTime='" + generationTime + "\'," +
                "fileId='" + fileId + "\'," +
                "yearTag='" + yearTag + "\'," +
                "serialNumber='" + serialNumber + "\'," +
                "tenantId='" + tenantId + "\'," +
                "tenantName='" + tenantName + "\'," +
                "remark='" + super.getRemark() + "\'" +
                '}';
    }
    
}