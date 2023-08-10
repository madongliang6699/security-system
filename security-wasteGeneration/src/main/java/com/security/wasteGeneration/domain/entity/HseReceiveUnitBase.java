package com.security.wasteGeneration.domain.entity;

import com.cybstar.base.CybEntityBase;
import com.cybstar.base.secureBase.solidWaste.hazardousWaste.dto.HseReceiveUnitBaseDTO;
import com.cybstar.base.secureBase.solidWaste.hazardousWaste.vo.HseReceiveUnitBaseVO;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * 固废管理-接收单位基础信息管理实体类
 * {"sid":"流水ID","appId":"appId","name":"单位名称","address":"单位地址","administrativeDivisionId":"行政区划id","administrativeDivisionName":"行政区划name","linkman":"联系人","contactNumber":"联系电话","licenseNumber":"危险废物经营许可证编号","fileId":"附件","remark":"备注","updateBy":"修改者","updateTime":"更新时间","createBy":"创建者","createTime":"创建时间","tenantId":"租户id","tenantName":"租户名称","delFlag":"删除标记"}
 *
 * @author A
 * @version v 0.1
 * @File HseReceiveUnitBase.java
 * @Desc 固废管理-接收单位基础信息管理
 * @DateTime 2023-07-10 17:43:24
 * @Encoding UTF-8
 * @Description Copyright (c) 2004-2023 All Rights Reserved.
 */
public class HseReceiveUnitBase extends CybEntityBase<HseReceiveUnitBase, HseReceiveUnitBaseDTO, HseReceiveUnitBaseVO> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    /**
     * 单位名称
     */
    private String name;
    /**
     * 单位地址
     */
    private String address;
    /**
     * 行政区划id
     */
    private String administrativeDivisionId;
    /**
     * 行政区划name
     */
    private String administrativeDivisionName;
    /**
     * 联系人
     */
    private String linkman;
    /**
     * 联系电话
     */
    private String contactNumber;
    /**
     * 危险废物经营许可证编号
     */
    private String licenseNumber;
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
     * 获取单位名称
     *
     * @return 单位名称
     */
    public String getName() {
        return name;
    }
    
    /**
     * 设置单位名称
     *
     * @param name 单位名称
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 获取单位地址
     *
     * @return 单位地址
     */
    public String getAddress() {
        return address;
    }
    
    /**
     * 设置单位地址
     *
     * @param address 单位地址
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    /**
     * 获取行政区划id
     *
     * @return 行政区划id
     */
    public String getAdministrativeDivisionId() {
        return administrativeDivisionId;
    }
    
    /**
     * 设置行政区划id
     *
     * @param administrativeDivisionId 行政区划id
     */
    public void setAdministrativeDivisionId(String administrativeDivisionId) {
        this.administrativeDivisionId = administrativeDivisionId;
    }
    
    /**
     * 获取行政区划name
     *
     * @return 行政区划name
     */
    public String getAdministrativeDivisionName() {
        return administrativeDivisionName;
    }
    
    /**
     * 设置行政区划name
     *
     * @param administrativeDivisionName 行政区划name
     */
    public void setAdministrativeDivisionName(String administrativeDivisionName) {
        this.administrativeDivisionName = administrativeDivisionName;
    }
    
    /**
     * 获取联系人
     *
     * @return 联系人
     */
    public String getLinkman() {
        return linkman;
    }
    
    /**
     * 设置联系人
     *
     * @param linkman 联系人
     */
    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }
    
    /**
     * 获取联系电话
     *
     * @return 联系电话
     */
    public String getContactNumber() {
        return contactNumber;
    }
    
    /**
     * 设置联系电话
     *
     * @param contactNumber 联系电话
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    
    /**
     * 获取危险废物经营许可证编号
     *
     * @return 危险废物经营许可证编号
     */
    public String getLicenseNumber() {
        return licenseNumber;
    }
    
    /**
     * 设置危险废物经营许可证编号
     *
     * @param licenseNumber 危险废物经营许可证编号
     */
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
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
     * 转换成固废管理-接收单位基础信息管理VO对象
     *
     * @return 固废管理-接收单位基础信息管理VO对象
     */
    @Override
    protected HseReceiveUnitBaseVO convertToVo() {
        HseReceiveUnitBaseVO hseReceiveUnitBaseVO = new HseReceiveUnitBaseVO();
        BeanUtils.copyProperties(this, hseReceiveUnitBaseVO);
        return hseReceiveUnitBaseVO;
    }
    
    /**
     * 转换成固废管理-接收单位基础信息管理DTO对象
     *
     * @return 固废管理-接收单位基础信息管理DTO对象
     */
    @Override
    protected HseReceiveUnitBaseDTO convertToDto() {
        HseReceiveUnitBaseDTO hseReceiveUnitBaseDTO = new HseReceiveUnitBaseDTO();
        BeanUtils.copyProperties(this, hseReceiveUnitBaseDTO);
        return hseReceiveUnitBaseDTO;
    }
    
    @Override
    public String toString() {
        return "HseReceiveUnitBase{" +
                
                "appId='" + super.getAppId() + "\'," +
                "name='" + name + "\'," +
                "address='" + address + "\'," +
                "administrativeDivisionId='" + administrativeDivisionId + "\'," +
                "administrativeDivisionName='" + administrativeDivisionName + "\'," +
                "linkman='" + linkman + "\'," +
                "contactNumber='" + contactNumber + "\'," +
                "licenseNumber='" + licenseNumber + "\'," +
                "fileId='" + fileId + "\'," +
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