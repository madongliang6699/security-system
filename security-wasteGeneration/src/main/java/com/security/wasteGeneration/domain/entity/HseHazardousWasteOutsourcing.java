package com.security.wasteGeneration.domain.entity;

import java.io.Serializable;
import com.cybstar.base.CybEntityBase;

import com.cybstar.base.secureBase.solidWaste.hazardousWaste.vo.HseHazardousWasteOutsourcingVO;
import com.cybstar.base.secureBase.solidWaste.hazardousWaste.dto.HseHazardousWasteOutsourcingDTO;

import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * 固废处理-危废委外利用处置实体类
 * {"sid":"流水ID","appId":"appId","remark":"备注","createBy":"创建者","createTime":"创建时间","updateBy":"修改者","updateTime":"更新时间","delFlag":"删除标记","tenantId":"租户id","tenantName":"租户名称","outsourcingTypeCode":"委外类型code","outsourcingTypeName":"委外类型name","wasteBaseId":"关联的废物id","source":"来源：1-产生，2-出库","deliveryTime":"出厂时间","disposalCode":"委外利用/处置编码","disposalVolume":"委外利用/处置量","packagingAndTypeCode":"容器/包装编码和类型code","packagingAndTypeName":"容器/包装编码和类型name","numberOfPackages":"容器包装个数","disposalMethodCode":"利用处置方式code","disposalMethodName":"利用处置方式name","receivingUnitTypeId":"接收单位类型id","receivingUnitTypeName":"接收单位类型name","unitId":"单位名称id","unitName":"单位名称name","exportDocumentNumber":"出口核准通知编单号","licenseCode":"许可证编码","generationOutboundIds":"关联的产生/出库ids","generationOutboundBatchEncoding":"产生/出库批次编码","yearTag":"编号年份标记","serialNumber":"每个标记的最大序号"}
 *
 * @author A
 * @version v 0.1
 * @File HseHazardousWasteOutsourcing.java
 * @Desc 固废处理-危废委外利用处置
 * @DateTime 2023-07-18 11:04:20
 * @Encoding UTF-8
 * @Description Copyright (c) 2004-2023 All Rights Reserved.
 */
public class HseHazardousWasteOutsourcing extends CybEntityBase<HseHazardousWasteOutsourcing, HseHazardousWasteOutsourcingDTO, HseHazardousWasteOutsourcingVO> implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 租户id
     */
    private String tenantId;
    /**
     * 租户名称
     */
    private String tenantName;
    /**
     * 委外类型code
     */
    private String outsourcingTypeCode;
    /**
     * 委外类型name
     */
    private String outsourcingTypeName;
    /**
     * 关联的废物id
     */
    private String wasteBaseId;
    /**
     * 来源：1-产生，2-出库
     */
    private Integer source;
    /**
     * 出厂时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    private Date deliveryTime;
    /**
     * 委外利用/处置编码
     */
    private String disposalCode;
    /**
     * 委外利用/处置量
     */
    private BigDecimal disposalVolume;
    /**
     * 容器/包装编码和类型code
     */
    private String packagingAndTypeCode;
    /**
     * 容器/包装编码和类型name
     */
    private String packagingAndTypeName;
    /**
     * 容器包装个数
     */
    private String numberOfPackages;
    /**
     * 利用处置方式code
     */
    private String disposalMethodCode;
    /**
     * 利用处置方式name
     */
    private String disposalMethodName;
    /**
     * 接收单位类型id
     */
    private String receivingUnitTypeId;
    /**
     * 接收单位类型name
     */
    private String receivingUnitTypeName;
    /**
     * 单位名称id
     */
    private String unitId;
    /**
     * 单位名称name
     */
    private String unitName;
    /**
     * 出口核准通知编单号
     */
    private String exportDocumentNumber;
    /**
     * 许可证编码
     */
    private String licenseCode;
    /**
     * 关联的产生/出库ids
     */
    private String generationOutboundIds;
    /**
     * 产生/出库批次编码
     */
    private String generationOutboundBatchEncoding;
    /**
     * 编号年份标记
     */
    private String yearTag;
    /**
     * 每个标记的最大序号
     */
    private Integer serialNumber;


    /**
     * 获取租户id
     *
     * @return 租户id
     */
    public String getTenantId(){
        return tenantId;
    }

    /**
     * 设置租户id
     *
     * @param tenantId 租户id
     */
    public void setTenantId(String tenantId){
        this.tenantId = tenantId;
    }
    /**
     * 获取租户名称
     *
     * @return 租户名称
     */
    public String getTenantName(){
        return tenantName;
    }

    /**
     * 设置租户名称
     *
     * @param tenantName 租户名称
     */
    public void setTenantName(String tenantName){
        this.tenantName = tenantName;
    }
    /**
     * 获取委外类型code
     *
     * @return 委外类型code
     */
    public String getOutsourcingTypeCode(){
        return outsourcingTypeCode;
    }

    /**
     * 设置委外类型code
     *
     * @param outsourcingTypeCode 委外类型code
     */
    public void setOutsourcingTypeCode(String outsourcingTypeCode){
        this.outsourcingTypeCode = outsourcingTypeCode;
    }
    /**
     * 获取委外类型name
     *
     * @return 委外类型name
     */
    public String getOutsourcingTypeName(){
        return outsourcingTypeName;
    }

    /**
     * 设置委外类型name
     *
     * @param outsourcingTypeName 委外类型name
     */
    public void setOutsourcingTypeName(String outsourcingTypeName){
        this.outsourcingTypeName = outsourcingTypeName;
    }
    /**
     * 获取关联的废物id
     *
     * @return 关联的废物id
     */
    public String getWasteBaseId(){
        return wasteBaseId;
    }

    /**
     * 设置关联的废物id
     *
     * @param wasteBaseId 关联的废物id
     */
    public void setWasteBaseId(String wasteBaseId){
        this.wasteBaseId = wasteBaseId;
    }
    /**
     * 获取来源：1-产生，2-出库
     *
     * @return 来源：1-产生，2-出库
     */
    public Integer getSource(){
        return source;
    }

    /**
     * 设置来源：1-产生，2-出库
     *
     * @param source 来源：1-产生，2-出库
     */
    public void setSource(Integer source){
        this.source = source;
    }
    /**
     * 获取出厂时间
     *
     * @return 出厂时间
     */
    public Date getDeliveryTime(){
        return deliveryTime;
    }

    /**
     * 设置出厂时间
     *
     * @param deliveryTime 出厂时间
     */
    public void setDeliveryTime(Date deliveryTime){
        this.deliveryTime = deliveryTime;
    }
    /**
     * 获取委外利用/处置编码
     *
     * @return 委外利用/处置编码
     */
    public String getDisposalCode(){
        return disposalCode;
    }

    /**
     * 设置委外利用/处置编码
     *
     * @param disposalCode 委外利用/处置编码
     */
    public void setDisposalCode(String disposalCode){
        this.disposalCode = disposalCode;
    }
    /**
     * 获取委外利用/处置量
     *
     * @return 委外利用/处置量
     */
    public BigDecimal getDisposalVolume(){
        return disposalVolume;
    }

    /**
     * 设置委外利用/处置量
     *
     * @param disposalVolume 委外利用/处置量
     */
    public void setDisposalVolume(BigDecimal disposalVolume){
        this.disposalVolume = disposalVolume;
    }
    /**
     * 获取容器/包装编码和类型code
     *
     * @return 容器/包装编码和类型code
     */
    public String getPackagingAndTypeCode(){
        return packagingAndTypeCode;
    }

    /**
     * 设置容器/包装编码和类型code
     *
     * @param packagingAndTypeCode 容器/包装编码和类型code
     */
    public void setPackagingAndTypeCode(String packagingAndTypeCode){
        this.packagingAndTypeCode = packagingAndTypeCode;
    }
    /**
     * 获取容器/包装编码和类型name
     *
     * @return 容器/包装编码和类型name
     */
    public String getPackagingAndTypeName(){
        return packagingAndTypeName;
    }

    /**
     * 设置容器/包装编码和类型name
     *
     * @param packagingAndTypeName 容器/包装编码和类型name
     */
    public void setPackagingAndTypeName(String packagingAndTypeName){
        this.packagingAndTypeName = packagingAndTypeName;
    }
    /**
     * 获取容器包装个数
     *
     * @return 容器包装个数
     */
    public String getNumberOfPackages(){
        return numberOfPackages;
    }

    /**
     * 设置容器包装个数
     *
     * @param numberOfPackages 容器包装个数
     */
    public void setNumberOfPackages(String numberOfPackages){
        this.numberOfPackages = numberOfPackages;
    }
    /**
     * 获取利用处置方式code
     *
     * @return 利用处置方式code
     */
    public String getDisposalMethodCode(){
        return disposalMethodCode;
    }

    /**
     * 设置利用处置方式code
     *
     * @param disposalMethodCode 利用处置方式code
     */
    public void setDisposalMethodCode(String disposalMethodCode){
        this.disposalMethodCode = disposalMethodCode;
    }
    /**
     * 获取利用处置方式name
     *
     * @return 利用处置方式name
     */
    public String getDisposalMethodName(){
        return disposalMethodName;
    }

    /**
     * 设置利用处置方式name
     *
     * @param disposalMethodName 利用处置方式name
     */
    public void setDisposalMethodName(String disposalMethodName){
        this.disposalMethodName = disposalMethodName;
    }
    /**
     * 获取接收单位类型id
     *
     * @return 接收单位类型id
     */
    public String getReceivingUnitTypeId(){
        return receivingUnitTypeId;
    }

    /**
     * 设置接收单位类型id
     *
     * @param receivingUnitTypeId 接收单位类型id
     */
    public void setReceivingUnitTypeId(String receivingUnitTypeId){
        this.receivingUnitTypeId = receivingUnitTypeId;
    }
    /**
     * 获取接收单位类型name
     *
     * @return 接收单位类型name
     */
    public String getReceivingUnitTypeName(){
        return receivingUnitTypeName;
    }

    /**
     * 设置接收单位类型name
     *
     * @param receivingUnitTypeName 接收单位类型name
     */
    public void setReceivingUnitTypeName(String receivingUnitTypeName){
        this.receivingUnitTypeName = receivingUnitTypeName;
    }
    /**
     * 获取单位名称id
     *
     * @return 单位名称id
     */
    public String getUnitId(){
        return unitId;
    }

    /**
     * 设置单位名称id
     *
     * @param unitId 单位名称id
     */
    public void setUnitId(String unitId){
        this.unitId = unitId;
    }
    /**
     * 获取单位名称name
     *
     * @return 单位名称name
     */
    public String getUnitName(){
        return unitName;
    }

    /**
     * 设置单位名称name
     *
     * @param unitName 单位名称name
     */
    public void setUnitName(String unitName){
        this.unitName = unitName;
    }
    /**
     * 获取出口核准通知编单号
     *
     * @return 出口核准通知编单号
     */
    public String getExportDocumentNumber(){
        return exportDocumentNumber;
    }

    /**
     * 设置出口核准通知编单号
     *
     * @param exportDocumentNumber 出口核准通知编单号
     */
    public void setExportDocumentNumber(String exportDocumentNumber){
        this.exportDocumentNumber = exportDocumentNumber;
    }
    /**
     * 获取许可证编码
     *
     * @return 许可证编码
     */
    public String getLicenseCode(){
        return licenseCode;
    }

    /**
     * 设置许可证编码
     *
     * @param licenseCode 许可证编码
     */
    public void setLicenseCode(String licenseCode){
        this.licenseCode = licenseCode;
    }
    /**
     * 获取关联的产生/出库ids
     *
     * @return 关联的产生/出库ids
     */
    public String getGenerationOutboundIds(){
        return generationOutboundIds;
    }

    /**
     * 设置关联的产生/出库ids
     *
     * @param generationOutboundIds 关联的产生/出库ids
     */
    public void setGenerationOutboundIds(String generationOutboundIds){
        this.generationOutboundIds = generationOutboundIds;
    }
    /**
     * 获取产生/出库批次编码
     *
     * @return 产生/出库批次编码
     */
    public String getGenerationOutboundBatchEncoding(){
        return generationOutboundBatchEncoding;
    }

    /**
     * 设置产生/出库批次编码
     *
     * @param generationOutboundBatchEncoding 产生/出库批次编码
     */
    public void setGenerationOutboundBatchEncoding(String generationOutboundBatchEncoding){
        this.generationOutboundBatchEncoding = generationOutboundBatchEncoding;
    }
    /**
     * 获取编号年份标记
     *
     * @return 编号年份标记
     */
    public String getYearTag(){
        return yearTag;
    }

    /**
     * 设置编号年份标记
     *
     * @param yearTag 编号年份标记
     */
    public void setYearTag(String yearTag){
        this.yearTag = yearTag;
    }
    /**
     * 获取每个标记的最大序号
     *
     * @return 每个标记的最大序号
     */
    public Integer getSerialNumber(){
        return serialNumber;
    }

    /**
     * 设置每个标记的最大序号
     *
     * @param serialNumber 每个标记的最大序号
     */
    public void setSerialNumber(Integer serialNumber){
        this.serialNumber = serialNumber;
    }

    /**
     * 转换成固废处理-危废委外利用处置VO对象
     *
     * @return 固废处理-危废委外利用处置VO对象
     */
    @Override
    protected HseHazardousWasteOutsourcingVO convertToVo(){
        HseHazardousWasteOutsourcingVO hseHazardousWasteOutsourcingVO =new HseHazardousWasteOutsourcingVO();
        hseHazardousWasteOutsourcingVO.setTenantId(this.getTenantId());
        hseHazardousWasteOutsourcingVO.setTenantName(this.getTenantName());
        hseHazardousWasteOutsourcingVO.setOutsourcingTypeCode(this.getOutsourcingTypeCode());
        hseHazardousWasteOutsourcingVO.setOutsourcingTypeName(this.getOutsourcingTypeName());
        hseHazardousWasteOutsourcingVO.setWasteBaseId(this.getWasteBaseId());
        hseHazardousWasteOutsourcingVO.setSource(this.getSource());
        hseHazardousWasteOutsourcingVO.setDeliveryTime(this.getDeliveryTime());
        hseHazardousWasteOutsourcingVO.setDisposalCode(this.getDisposalCode());
        hseHazardousWasteOutsourcingVO.setDisposalVolume(this.getDisposalVolume());
        hseHazardousWasteOutsourcingVO.setPackagingAndTypeCode(this.getPackagingAndTypeCode());
        hseHazardousWasteOutsourcingVO.setPackagingAndTypeName(this.getPackagingAndTypeName());
        hseHazardousWasteOutsourcingVO.setNumberOfPackages(this.getNumberOfPackages());
        hseHazardousWasteOutsourcingVO.setDisposalMethodCode(this.getDisposalMethodCode());
        hseHazardousWasteOutsourcingVO.setDisposalMethodName(this.getDisposalMethodName());
        hseHazardousWasteOutsourcingVO.setReceivingUnitTypeId(this.getReceivingUnitTypeId());
        hseHazardousWasteOutsourcingVO.setReceivingUnitTypeName(this.getReceivingUnitTypeName());
        hseHazardousWasteOutsourcingVO.setUnitId(this.getUnitId());
        hseHazardousWasteOutsourcingVO.setUnitName(this.getUnitName());
        hseHazardousWasteOutsourcingVO.setExportDocumentNumber(this.getExportDocumentNumber());
        hseHazardousWasteOutsourcingVO.setLicenseCode(this.getLicenseCode());
        hseHazardousWasteOutsourcingVO.setGenerationOutboundIds(this.getGenerationOutboundIds());
        hseHazardousWasteOutsourcingVO.setGenerationOutboundBatchEncoding(this.getGenerationOutboundBatchEncoding());
        hseHazardousWasteOutsourcingVO.setYearTag(this.getYearTag());
        hseHazardousWasteOutsourcingVO.setSerialNumber(this.getSerialNumber());
        return hseHazardousWasteOutsourcingVO;
    }

    /**
     * 转换成固废处理-危废委外利用处置DTO对象
     *
     * @return 固废处理-危废委外利用处置DTO对象
     */
    @Override
    protected HseHazardousWasteOutsourcingDTO convertToDto(){
        HseHazardousWasteOutsourcingDTO hseHazardousWasteOutsourcingDTO =new HseHazardousWasteOutsourcingDTO();
        hseHazardousWasteOutsourcingDTO.setTenantId(this.getTenantId());
        hseHazardousWasteOutsourcingDTO.setTenantName(this.getTenantName());
        hseHazardousWasteOutsourcingDTO.setOutsourcingTypeCode(this.getOutsourcingTypeCode());
        hseHazardousWasteOutsourcingDTO.setOutsourcingTypeName(this.getOutsourcingTypeName());
        hseHazardousWasteOutsourcingDTO.setWasteBaseId(this.getWasteBaseId());
        hseHazardousWasteOutsourcingDTO.setSource(this.getSource());
        hseHazardousWasteOutsourcingDTO.setDeliveryTime(this.getDeliveryTime());
        hseHazardousWasteOutsourcingDTO.setDisposalCode(this.getDisposalCode());
        hseHazardousWasteOutsourcingDTO.setDisposalVolume(this.getDisposalVolume());
        hseHazardousWasteOutsourcingDTO.setPackagingAndTypeCode(this.getPackagingAndTypeCode());
        hseHazardousWasteOutsourcingDTO.setPackagingAndTypeName(this.getPackagingAndTypeName());
        hseHazardousWasteOutsourcingDTO.setNumberOfPackages(this.getNumberOfPackages());
        hseHazardousWasteOutsourcingDTO.setDisposalMethodCode(this.getDisposalMethodCode());
        hseHazardousWasteOutsourcingDTO.setDisposalMethodName(this.getDisposalMethodName());
        hseHazardousWasteOutsourcingDTO.setReceivingUnitTypeId(this.getReceivingUnitTypeId());
        hseHazardousWasteOutsourcingDTO.setReceivingUnitTypeName(this.getReceivingUnitTypeName());
        hseHazardousWasteOutsourcingDTO.setUnitId(this.getUnitId());
        hseHazardousWasteOutsourcingDTO.setUnitName(this.getUnitName());
        hseHazardousWasteOutsourcingDTO.setExportDocumentNumber(this.getExportDocumentNumber());
        hseHazardousWasteOutsourcingDTO.setLicenseCode(this.getLicenseCode());
        hseHazardousWasteOutsourcingDTO.setGenerationOutboundIds(this.getGenerationOutboundIds());
        hseHazardousWasteOutsourcingDTO.setGenerationOutboundBatchEncoding(this.getGenerationOutboundBatchEncoding());
        hseHazardousWasteOutsourcingDTO.setYearTag(this.getYearTag());
        hseHazardousWasteOutsourcingDTO.setSerialNumber(this.getSerialNumber());
        return hseHazardousWasteOutsourcingDTO;
    }
    
    @Override
    public String toString() {
        return "HseHazardousWasteOutsourcingVO{" +
                    "sid='"+super.getSid()+ "\'," +
                    "appId='"+super.getAppId()+ "\'," +
                    "tenantId='"+tenantId+ "\'," +
                    "tenantName='"+tenantName+ "\'," +
                    "outsourcingTypeCode='"+outsourcingTypeCode+ "\'," +
                    "outsourcingTypeName='"+outsourcingTypeName+ "\'," +
                    "wasteBaseId='"+wasteBaseId+ "\'," +
                    "source='"+source+ "\'," +
                    "deliveryTime='"+deliveryTime+ "\'," +
                    "disposalCode='"+disposalCode+ "\'," +
                    "disposalVolume='"+disposalVolume+ "\'," +
                    "packagingAndTypeCode='"+packagingAndTypeCode+ "\'," +
                    "packagingAndTypeName='"+packagingAndTypeName+ "\'," +
                    "numberOfPackages='"+numberOfPackages+ "\'," +
                    "disposalMethodCode='"+disposalMethodCode+ "\'," +
                    "disposalMethodName='"+disposalMethodName+ "\'," +
                    "receivingUnitTypeId='"+receivingUnitTypeId+ "\'," +
                    "receivingUnitTypeName='"+receivingUnitTypeName+ "\'," +
                    "unitId='"+unitId+ "\'," +
                    "unitName='"+unitName+ "\'," +
                    "exportDocumentNumber='"+exportDocumentNumber+ "\'," +
                    "licenseCode='"+licenseCode+ "\'," +
                    "generationOutboundIds='"+generationOutboundIds+ "\'," +
                    "generationOutboundBatchEncoding='"+generationOutboundBatchEncoding+ "\'," +
                    "yearTag='"+yearTag+ "\'," +
                    "serialNumber='"+serialNumber+ "\'," +
                    "remark='"+super.getRemark()+ "\'," +
                    "createBy='"+super.getCreateBy()+ "\'," +
                    "createTime='"+super.getCreateTime()+ "\'," +
                    "updateBy='"+super.getUpdateBy()+ "\'," +
                    "updateTime='"+super.getUpdateTime()+ "\'," +
                    "delFlag='"+super.getDelFlag()+ "\'" +
                '}';
    }
}