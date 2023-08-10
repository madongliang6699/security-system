package com.security.wasteGeneration.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.*;
import com.alibaba.fastjson.annotation.JSONField;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

import static java.math.BigDecimal.ZERO;

@HeadRowHeight(38)
@HeadStyle(horizontalAlignment = HorizontalAlignment.CENTER)
@HeadFontStyle(bold = true)
@ContentRowHeight(25)
@ContentStyle(horizontalAlignment = HorizontalAlignment.CENTER)
@ColumnWidth(13)
@ExcelIgnoreUnannotated
public class HseHazardousWasteOutsourcingExport {

    @ExcelProperty(value = {"序号"})
    private String no;

    /**
     * 委外利用/处置编码
     */
    @ExcelProperty(value = {"委外利用/处置批次编码"})
    private String disposalCode;

    /**
     * 出厂时间
     */
    //@JSONField(format = "yyyy-MM-dd HH:mm")
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    //@ExcelProperty(value = {"出厂时间"}, format = "yyyy-MM-dd HH:mm")
    private String deliveryTime;

    /**
     * 容器/包装编码和类型code
     */
    @ExcelProperty(value = {"容器/包装编码"})
    private String packagingAndTypeCode = "";

    /**
     * 容器/包装编码和类型name
     */
    @ExcelProperty(value = {"容器/包装类型"})
    private String packagingAndTypeName = "";

    /**
     * 容器包装个数
     */
    @ExcelProperty(value = {"容器/包装数量"})
    private String numberOfPackages = "";

    /**
     * 废物名称
     */
    @ExcelProperty(value = {"危险废物名称", "行业俗称/单位内部名称"})
    private String wasteName = "";

    /**
     * 国家危险废物名录名称
     */
    @ExcelProperty(value = {"危险废物名称", "国家危险废物名录名称"})
    private String name = "";

    /**
     * 危废类别
     */
    @ExcelProperty(value = {"危险废物类别"})
    private String type = "";

    /**
     * 危废代码
     */
    @ExcelProperty(value = {"危险废物代码"})
    private String wasteCode = "";

    /**
     * 委外利用/处置量
     */
    @ExcelProperty(value = {"委外利用/处置量"})
    private BigDecimal disposalVolume = ZERO;

    /**
     * 计量单位name
     */
    @ExcelProperty(value = {"计量单位"})
    private String measurementUnitName = "";

    /**
     * 利用处置方式name
     */
    @ExcelProperty(value = {"利用/处置方式"})
    private String disposalMethodName = "";

    /**
     * 接收单位类型name
     */
    @ExcelProperty(value = {"接收单位类型"})
    private String receivingUnitTypeName = "";


    @ExcelProperty(value = {"危险废物经营许可证持有单位", "单位名称"})
    private String unitName1 = "/";

    /**
     * 许可证编码
     */
    @ExcelProperty(value = {"危险废物经营许可证持有单位", "许可证编码"})
    private String licenseCode = "/";


    @ExcelProperty(value = {"危险废物利用处置环节豁免管理单位", "管理单位名称"})
    private String unitName2 = "/";

    @ExcelProperty(value = {"中华人员共和国境外的危险废物利用处置单位", "单位名称"})
    private String unitName3 = "/";

    /**
     * 出口核准通知编单号
     */
    @ExcelProperty(value = {"中华人员共和国境外的危险废物利用处置单位", "出口核准通知编单号"})
    private String exportDocumentNumber = "/";

    /**
     * 产生/出库批次编码
     */
    @ExcelProperty(value = {"产生/出库批次编码"})
    private String generationOutboundBatchEncoding = "";


    //private HseHazardousWasteBaseVO hseHazardousWasteBaseVO;
    //
    ///**
    // * 委外类型code
    // */
    //private String outsourcingTypeCode;
    //
    ///**
    // * 委外类型name
    // */
    //private String outsourcingTypeName;
    //
    ///**
    // * 关联的废物id
    // */
    //private String wasteBaseId;
    //
    ///**
    // * 来源：1-产生，2-出库
    // */
    //private Integer source;
    //
    ///**
    // * 利用处置方式code
    // */
    //private String disposalMethodCode;
    //
    /**
     * 接收单位类型id
     */
    private String receivingUnitTypeId = "";

    public String getReceivingUnitTypeId() {
        return receivingUnitTypeId;
    }

    public void setReceivingUnitTypeId(String receivingUnitTypeId) {
        this.receivingUnitTypeId = receivingUnitTypeId;
    }
//
    ///**
    // * 单位名称id
    // */
    //private String unitId;
    //
    ///**
    // * 关联的产生/出库ids
    // */
    //private String generationOutboundIds;
    //
    ///**
    // * 编号年份标记
    // */
    //private String yearTag;
    //
    ///**
    // * 每个标记的最大序号
    // */
    //private Integer serialNumber;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getWasteName() {
        return wasteName;
    }

    public void setWasteName(String wasteName) {
        this.wasteName = wasteName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWasteCode() {
        return wasteCode;
    }

    public void setWasteCode(String wasteCode) {
        this.wasteCode = wasteCode;
    }

    public String getMeasurementUnitName() {
        return measurementUnitName;
    }

    public void setMeasurementUnitName(String measurementUnitName) {
        this.measurementUnitName = measurementUnitName;
    }

    public String getUnitName1() {
        return unitName1;
    }

    public void setUnitName1(String unitName1) {
        this.unitName1 = unitName1;
    }

    public String getUnitName2() {
        return unitName2;
    }

    public void setUnitName2(String unitName2) {
        this.unitName2 = unitName2;
    }

    public String getUnitName3() {
        return unitName3;
    }

    public void setUnitName3(String unitName3) {
        this.unitName3 = unitName3;
    }


    /**
     * 获取出厂时间
     *
     * @return 出厂时间
     */
    public String getDeliveryTime() {
        return deliveryTime;
    }

    /**
     * 设置出厂时间
     *
     * @param deliveryTime 出厂时间
     */
    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    /**
     * 获取委外利用/处置编码
     *
     * @return 委外利用/处置编码
     */
    public String getDisposalCode() {
        return disposalCode;
    }

    /**
     * 设置委外利用/处置编码
     *
     * @param disposalCode 委外利用/处置编码
     */
    public void setDisposalCode(String disposalCode) {
        this.disposalCode = disposalCode;
    }

    /**
     * 获取委外利用/处置量
     *
     * @return 委外利用/处置量
     */
    public BigDecimal getDisposalVolume() {
        return disposalVolume;
    }

    /**
     * 设置委外利用/处置量
     *
     * @param disposalVolume 委外利用/处置量
     */
    public void setDisposalVolume(BigDecimal disposalVolume) {
        this.disposalVolume = disposalVolume;
    }

    /**
     * 获取容器/包装编码和类型code
     *
     * @return 容器/包装编码和类型code
     */
    public String getPackagingAndTypeCode() {
        return packagingAndTypeCode;
    }

    /**
     * 设置容器/包装编码和类型code
     *
     * @param packagingAndTypeCode 容器/包装编码和类型code
     */
    public void setPackagingAndTypeCode(String packagingAndTypeCode) {
        this.packagingAndTypeCode = packagingAndTypeCode;
    }

    /**
     * 获取容器/包装编码和类型name
     *
     * @return 容器/包装编码和类型name
     */
    public String getPackagingAndTypeName() {
        return packagingAndTypeName;
    }

    /**
     * 设置容器/包装编码和类型name
     *
     * @param packagingAndTypeName 容器/包装编码和类型name
     */
    public void setPackagingAndTypeName(String packagingAndTypeName) {
        this.packagingAndTypeName = packagingAndTypeName;
    }

    /**
     * 获取容器包装个数
     *
     * @return 容器包装个数
     */
    public String getNumberOfPackages() {
        return numberOfPackages;
    }

    /**
     * 设置容器包装个数
     *
     * @param numberOfPackages 容器包装个数
     */
    public void setNumberOfPackages(String numberOfPackages) {
        this.numberOfPackages = numberOfPackages;
    }


    /**
     * 获取利用处置方式name
     *
     * @return 利用处置方式name
     */
    public String getDisposalMethodName() {
        return disposalMethodName;
    }

    /**
     * 设置利用处置方式name
     *
     * @param disposalMethodName 利用处置方式name
     */
    public void setDisposalMethodName(String disposalMethodName) {
        this.disposalMethodName = disposalMethodName;
    }


    /**
     * 获取接收单位类型name
     *
     * @return 接收单位类型name
     */
    public String getReceivingUnitTypeName() {
        return receivingUnitTypeName;
    }

    /**
     * 设置接收单位类型name
     *
     * @param receivingUnitTypeName 接收单位类型name
     */
    public void setReceivingUnitTypeName(String receivingUnitTypeName) {
        this.receivingUnitTypeName = receivingUnitTypeName;
    }


    /**
     * 获取出口核准通知编单号
     *
     * @return 出口核准通知编单号
     */
    public String getExportDocumentNumber() {
        return exportDocumentNumber;
    }

    /**
     * 设置出口核准通知编单号
     *
     * @param exportDocumentNumber 出口核准通知编单号
     */
    public void setExportDocumentNumber(String exportDocumentNumber) {
        this.exportDocumentNumber = exportDocumentNumber;
    }

    /**
     * 获取许可证编码
     *
     * @return 许可证编码
     */
    public String getLicenseCode() {
        return licenseCode;
    }

    /**
     * 设置许可证编码
     *
     * @param licenseCode 许可证编码
     */
    public void setLicenseCode(String licenseCode) {
        this.licenseCode = licenseCode;
    }


    /**
     * 获取产生/出库批次编码
     *
     * @return 产生/出库批次编码
     */
    public String getGenerationOutboundBatchEncoding() {
        return generationOutboundBatchEncoding;
    }

    /**
     * 设置产生/出库批次编码
     *
     * @param generationOutboundBatchEncoding 产生/出库批次编码
     */
    public void setGenerationOutboundBatchEncoding(String generationOutboundBatchEncoding) {
        this.generationOutboundBatchEncoding = generationOutboundBatchEncoding;
    }


}
