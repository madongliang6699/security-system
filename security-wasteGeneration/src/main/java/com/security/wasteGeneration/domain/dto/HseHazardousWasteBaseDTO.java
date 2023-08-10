package com.security.wasteGeneration.domain.dto;

import com.cybstar.base.CybDtoBase;
import com.cybstar.base.secureBase.solidWaste.hazardousWaste.entity.HseHazardousWasteBase;
import com.cybstar.base.secureBase.solidWaste.hazardousWaste.vo.HseHazardousWasteBaseVO;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 固废管理-危废基础信息管理实体交互类
 * {"sid":"流水ID","appId":"appId","wasteName":"废物名称","directoryId":"国家危险废物名录id","storageQuantity":"贮存量","storagePositionCode":"贮存位置字典编码","storagePositionName":"贮存位置字典名称","storagePositionType":"贮存位置字典类型","responsiblePersonId":"负责人id","responsiblePersonName":"负责人name","fileId":"附件","remark":"备注","updateBy":"修改者","updateTime":"更新时间","createBy":"创建者","createTime":"创建时间","tenantId":"租户id","tenantName":"租户名称","delFlag":"删除标记"}
 *
 * @author A
 * @version v 0.1
 * @File HseHazardousWasteBaseDTO.java
 * @Desc 固废管理-危废基础信息管理
 * @DateTime 2023-07-10 17:33:20
 * @Encoding UTF-8
 * @Description Copyright (c) 2004-2023 All Rights Reserved.
 */
public class HseHazardousWasteBaseDTO extends CybDtoBase<HseHazardousWasteBase, HseHazardousWasteBaseDTO, HseHazardousWasteBaseVO> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 废物名称
     */
    private String wasteName;
    
    /**
     * 国家危险废物名录id
     */
    private String directoryId;
    
    /**
     * 贮存量
     */
    private BigDecimal storageQuantity;
    
    /**
     * 计量单位编码
     */
    private String measurementUnitCode;
    
    /**
     * 计量单位name
     */
    private String measurementUnitName;
    
    /**
     * 贮存位置字典编码
     */
    private String storagePositionCode;
    
    /**
     * 贮存位置字典名称
     */
    private String storagePositionName;
    
    /**
     * 贮存位置字典类型
     */
    private String storagePositionType;
    
    /**
     * 负责人id
     */
    private String responsiblePersonId;
    
    /**
     * 负责人name
     */
    private String responsiblePersonName;
    
    /**
     * 附件
     */
    private String fileId;
    
    /**
     * 租户id
     */
    private String tenantId;
    
    /**
     * 租户名称
     */
    private String tenantName;
    
    
    /**
     * 获取废物名称
     *
     * @return 废物名称
     */
    public String getWasteName() {
        return wasteName;
    }
    
    /**
     * 设置废物名称
     *
     * @param wasteName 废物名称
     */
    public void setWasteName(String wasteName) {
        this.wasteName = wasteName;
    }
    
    /**
     * 设置废物名称
     *
     * @param wasteName 废物名称
     */
    public HseHazardousWasteBaseDTO wasteName(String wasteName) {
        this.wasteName = wasteName;
        return this;
    }
    
    
    /**
     * 获取国家危险废物名录id
     *
     * @return 国家危险废物名录id
     */
    public String getDirectoryId() {
        return directoryId;
    }
    
    /**
     * 设置国家危险废物名录id
     *
     * @param directoryId 国家危险废物名录id
     */
    public void setDirectoryId(String directoryId) {
        this.directoryId = directoryId;
    }
    
    /**
     * 设置国家危险废物名录id
     *
     * @param directoryId 国家危险废物名录id
     */
    public HseHazardousWasteBaseDTO directoryId(String directoryId) {
        this.directoryId = directoryId;
        return this;
    }
    
    
    /**
     * 获取贮存量
     *
     * @return 贮存量
     */
    public BigDecimal getStorageQuantity() {
        return storageQuantity;
    }
    
    /**
     * 设置贮存量
     *
     * @param storageQuantity 贮存量
     */
    public void setStorageQuantity(BigDecimal storageQuantity) {
        this.storageQuantity = storageQuantity;
    }
    
    /**
     * 设置贮存量
     *
     * @param storageQuantity 贮存量
     */
    public HseHazardousWasteBaseDTO storageQuantity(BigDecimal storageQuantity) {
        this.storageQuantity = storageQuantity;
        return this;
    }
    
    
    /**
     * 获取计量单位编码
     *
     * @return 计量单位编码
     */
    public String getMeasurementUnitCode() {
        return measurementUnitCode;
    }
    
    /**
     * 设置计量单位编码
     *
     * @param measurementUnitCode 计量单位编码
     */
    public void setMeasurementUnitCode(String measurementUnitCode) {
        this.measurementUnitCode = measurementUnitCode;
    }
    
    /**
     * 设置计量单位编码
     *
     * @param measurementUnitCode 计量单位编码
     */
    public HseHazardousWasteBaseDTO measurementUnitCode(String measurementUnitCode) {
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
    public HseHazardousWasteBaseDTO measurementUnitName(String measurementUnitName) {
        this.measurementUnitName = measurementUnitName;
        return this;
    }
    
    
    /**
     * 获取贮存位置字典编码
     *
     * @return 贮存位置字典编码
     */
    public String getStoragePositionCode() {
        return storagePositionCode;
    }
    
    /**
     * 设置贮存位置字典编码
     *
     * @param storagePositionCode 贮存位置字典编码
     */
    public void setStoragePositionCode(String storagePositionCode) {
        this.storagePositionCode = storagePositionCode;
    }
    
    /**
     * 设置贮存位置字典编码
     *
     * @param storagePositionCode 贮存位置字典编码
     */
    public HseHazardousWasteBaseDTO storagePositionCode(String storagePositionCode) {
        this.storagePositionCode = storagePositionCode;
        return this;
    }
    
    
    /**
     * 获取贮存位置字典名称
     *
     * @return 贮存位置字典名称
     */
    public String getStoragePositionName() {
        return storagePositionName;
    }
    
    /**
     * 设置贮存位置字典名称
     *
     * @param storagePositionName 贮存位置字典名称
     */
    public void setStoragePositionName(String storagePositionName) {
        this.storagePositionName = storagePositionName;
    }
    
    /**
     * 设置贮存位置字典名称
     *
     * @param storagePositionName 贮存位置字典名称
     */
    public HseHazardousWasteBaseDTO storagePositionName(String storagePositionName) {
        this.storagePositionName = storagePositionName;
        return this;
    }
    
    
    /**
     * 获取贮存位置字典类型
     *
     * @return 贮存位置字典类型
     */
    public String getStoragePositionType() {
        return storagePositionType;
    }
    
    /**
     * 设置贮存位置字典类型
     *
     * @param storagePositionType 贮存位置字典类型
     */
    public void setStoragePositionType(String storagePositionType) {
        this.storagePositionType = storagePositionType;
    }
    
    /**
     * 设置贮存位置字典类型
     *
     * @param storagePositionType 贮存位置字典类型
     */
    public HseHazardousWasteBaseDTO storagePositionType(String storagePositionType) {
        this.storagePositionType = storagePositionType;
        return this;
    }
    
    
    /**
     * 获取负责人id
     *
     * @return 负责人id
     */
    public String getResponsiblePersonId() {
        return responsiblePersonId;
    }
    
    /**
     * 设置负责人id
     *
     * @param responsiblePersonId 负责人id
     */
    public void setResponsiblePersonId(String responsiblePersonId) {
        this.responsiblePersonId = responsiblePersonId;
    }
    
    /**
     * 设置负责人id
     *
     * @param responsiblePersonId 负责人id
     */
    public HseHazardousWasteBaseDTO responsiblePersonId(String responsiblePersonId) {
        this.responsiblePersonId = responsiblePersonId;
        return this;
    }
    
    
    /**
     * 获取负责人name
     *
     * @return 负责人name
     */
    public String getResponsiblePersonName() {
        return responsiblePersonName;
    }
    
    /**
     * 设置负责人name
     *
     * @param responsiblePersonName 负责人name
     */
    public void setResponsiblePersonName(String responsiblePersonName) {
        this.responsiblePersonName = responsiblePersonName;
    }
    
    /**
     * 设置负责人name
     *
     * @param responsiblePersonName 负责人name
     */
    public HseHazardousWasteBaseDTO responsiblePersonName(String responsiblePersonName) {
        this.responsiblePersonName = responsiblePersonName;
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
    public HseHazardousWasteBaseDTO fileId(String fileId) {
        this.fileId = fileId;
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
    public HseHazardousWasteBaseDTO tenantId(String tenantId) {
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
    public HseHazardousWasteBaseDTO tenantName(String tenantName) {
        this.tenantName = tenantName;
        return this;
    }
    
    
    public HseHazardousWasteBaseDTO appId(Integer appId) {
        super.setAppId(appId);
        return this;
    }
    
    
    /**
     * 转换成固废管理-危废基础信息管理Entity对象
     *
     * @return 固废管理-危废基础信息管理Entity对象
     */
    @Override
    protected HseHazardousWasteBase convertToEntity() {
        HseHazardousWasteBase hseHazardousWasteBase = new HseHazardousWasteBase();
        BeanUtils.copyProperties(this, hseHazardousWasteBase);
        return hseHazardousWasteBase;
    }
    
    /**
     * 转换成固废管理-危废基础信息管理VO对象
     *
     * @return 固废管理-危废基础信息管理VO对象
     */
    @Override
    protected HseHazardousWasteBaseVO convertToVo() {
        HseHazardousWasteBaseVO hseHazardousWasteBaseVO = new HseHazardousWasteBaseVO();
        BeanUtils.copyProperties(this, hseHazardousWasteBaseVO);
        return hseHazardousWasteBaseVO;
    }
    
    @Override
    public String toString() {
        return "HseHazardousWasteBaseDTO{" +
                
                "appId='" + super.getAppId() + "\'," +
                "wasteName='" + wasteName + "\'," +
                "directoryId='" + directoryId + "\'," +
                "storageQuantity='" + storageQuantity + "\'," +
                "measurementUnitCode='" + measurementUnitCode + "\'," +
                "measurementUnitName='" + measurementUnitName + "\'," +
                "storagePositionCode='" + storagePositionCode + "\'," +
                "storagePositionName='" + storagePositionName + "\'," +
                "storagePositionType='" + storagePositionType + "\'," +
                "responsiblePersonId='" + responsiblePersonId + "\'," +
                "responsiblePersonName='" + responsiblePersonName + "\'," +
                "fileId='" + fileId + "\'," +
                "tenantId='" + tenantId + "\'," +
                "tenantName='" + tenantName + "\'," +
                "remark='" + super.getRemark() + "\'" +
                '}';
    }
    
}