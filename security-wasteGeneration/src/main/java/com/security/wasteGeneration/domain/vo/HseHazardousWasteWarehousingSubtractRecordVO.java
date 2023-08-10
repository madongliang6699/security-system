package com.security.wasteGeneration.domain.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.cybstar.base.CybVoBase;
import com.cybstar.base.secureBase.solidWaste.hazardousWaste.dto.HseHazardousWasteWarehousingSubtractRecordDTO;
import com.cybstar.base.secureBase.solidWaste.hazardousWaste.entity.HseHazardousWasteWarehousingSubtractRecord;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 固废管理-危废入库单剩余量的减量去向记录实体展示类
 * {"sid":"流水ID","appId":"appId","warehousingId":"入库单id","outboundId":"出库单id","subtractQuantity":"减去量","operateTime":"操作时间","remark":"备注","updateBy":"修改者","updateTime":"更新时间","createBy":"创建者","createTime":"创建时间","tenantId":"租户id","tenantName":"租户名称","delFlag":"删除标记"}
 *
 * @author A
 * @version v 0.1
 * @File HseHazardousWasteWarehousingSubtractRecordVO.java
 * @Desc 固废管理-危废入库单剩余量的减量去向记录
 * @DateTime 2023-07-18 14:52:58
 * @Encoding UTF-8
 * @Description Copyright (c) 2004-2023 All Rights Reserved.
 */
public class HseHazardousWasteWarehousingSubtractRecordVO extends CybVoBase<HseHazardousWasteWarehousingSubtractRecord, HseHazardousWasteWarehousingSubtractRecordDTO, HseHazardousWasteWarehousingSubtractRecordVO> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 入库单id
     */
    private String warehousingId;
    
    /**
     * 出库单id
     */
    private String outboundId;
    
    /**
     * 减去量
     */
    private BigDecimal subtractQuantity;
    
    /**
     * 操作时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operateTime;
    
    /**
     * 租户id
     */
    private String tenantId;
    
    /**
     * 租户名称
     */
    private String tenantName;
    
    
    /**
     * 获取入库单id
     *
     * @return 入库单id
     */
    public String getWarehousingId() {
        return warehousingId;
    }
    
    /**
     * 设置入库单id
     *
     * @param warehousingId 入库单id
     */
    public void setWarehousingId(String warehousingId) {
        this.warehousingId = warehousingId;
    }
    
    /**
     * 获取出库单id
     *
     * @return 出库单id
     */
    public String getOutboundId() {
        return outboundId;
    }
    
    /**
     * 设置出库单id
     *
     * @param outboundId 出库单id
     */
    public void setOutboundId(String outboundId) {
        this.outboundId = outboundId;
    }
    
    /**
     * 获取减去量
     *
     * @return 减去量
     */
    public BigDecimal getSubtractQuantity() {
        return subtractQuantity;
    }
    
    /**
     * 设置减去量
     *
     * @param subtractQuantity 减去量
     */
    public void setSubtractQuantity(BigDecimal subtractQuantity) {
        this.subtractQuantity = subtractQuantity;
    }
    
    /**
     * 获取操作时间
     *
     * @return 操作时间
     */
    public Date getOperateTime() {
        return operateTime;
    }
    
    /**
     * 设置操作时间
     *
     * @param operateTime 操作时间
     */
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
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
     * 转换成固废管理-危废入库单剩余量的减量去向记录Entity对象
     *
     * @return 固废管理-危废入库单剩余量的减量去向记录Entity对象
     */
    @Override
    protected HseHazardousWasteWarehousingSubtractRecord convertToEntity() {
        HseHazardousWasteWarehousingSubtractRecord hseHazardousWasteWarehousingSubtractRecord = new HseHazardousWasteWarehousingSubtractRecord();
        BeanUtils.copyProperties(this, hseHazardousWasteWarehousingSubtractRecord);
        return hseHazardousWasteWarehousingSubtractRecord;
    }
    
    /**
     * 转换成固废管理-危废入库单剩余量的减量去向记录DTO对象
     *
     * @return 固废管理-危废入库单剩余量的减量去向记录DTO对象
     */
    @Override
    protected HseHazardousWasteWarehousingSubtractRecordDTO convertToDto() {
        HseHazardousWasteWarehousingSubtractRecordDTO hseHazardousWasteWarehousingSubtractRecordDTO = new HseHazardousWasteWarehousingSubtractRecordDTO();
        BeanUtils.copyProperties(this, hseHazardousWasteWarehousingSubtractRecordDTO);
        return hseHazardousWasteWarehousingSubtractRecordDTO;
    }
    
    @Override
    public String toString() {
        return "HseHazardousWasteWarehousingSubtractRecordVO{" +
                
                "appId='" + super.getAppId() + "\'," +
                "warehousingId='" + warehousingId + "\'," +
                "outboundId='" + outboundId + "\'," +
                "subtractQuantity='" + subtractQuantity + "\'," +
                "operateTime='" + operateTime + "\'," +
                "tenantId='" + tenantId + "\'," +
                "tenantName='" + tenantName + "\'," +
                "remark='" + super.getRemark() + "\'" +
                '}';
    }
}