package com.security.wasteGeneration.domain.dto;

import com.cybstar.base.secureBase.solidWaste.hazardousWaste.vo.HseHazardousWasteDirectoryVO;
import com.cybstar.base.secureBase.solidWaste.hazardousWaste.entity.HseHazardousWasteDirectory;

import java.io.Serializable;

import com.cybstar.base.CybDtoBase;
import org.springframework.beans.BeanUtils;

/**
 * 固废管理-国家危险废物名录知识库实体交互类
 * {"sid":"流水ID","appId":"appId","type":"危废类别","wasteCode":"危废代码","name":"国家危险废物名录名称","fileId":"附件","remark":"备注","updateBy":"修改者","updateTime":"更新时间","createBy":"创建者","createTime":"创建时间","tenantId":"租户id","tenantName":"租户名称","delFlag":"删除标记"}
 *
 * @author A
 * @version v 0.1
 * @File HseHazardousWasteDirectoryDTO.java
 * @Desc 固废管理-国家危险废物名录知识库
 * @DateTime 2023-07-10 15:18:17
 * @Encoding UTF-8
 * @Description Copyright (c) 2004-2023 All Rights Reserved.
 */
public class HseHazardousWasteDirectoryDTO extends CybDtoBase<HseHazardousWasteDirectory, HseHazardousWasteDirectoryDTO, HseHazardousWasteDirectoryVO> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 危废类别
     */
    private String type;
    
    /**
     * 危废代码
     */
    private String wasteCode;
    
    /**
     * 国家危险废物名录名称
     */
    private String name;
    
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
     * 获取危废类别
     *
     * @return 危废类别
     */
    public String getType() {
        return type;
    }
    
    /**
     * 设置危废类别
     *
     * @param type 危废类别
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * 设置危废类别
     *
     * @param type 危废类别
     */
    public HseHazardousWasteDirectoryDTO type(String type) {
        this.type = type;
        return this;
    }
    
    /**
     * 获取危废代码
     *
     * @return 危废代码
     */
    public String getWasteCode() {
        return wasteCode;
    }
    
    /**
     * 设置危废代码
     *
     * @param wasteCode 危废代码
     */
    public void setWasteCode(String wasteCode) {
        this.wasteCode = wasteCode;
    }
    
    /**
     * 设置危废代码
     *
     * @param wasteCode 危废代码
     */
    public HseHazardousWasteDirectoryDTO wasteCode(String wasteCode) {
        this.wasteCode = wasteCode;
        return this;
    }
    
    /**
     * 获取国家危险废物名录名称
     *
     * @return 国家危险废物名录名称
     */
    public String getName() {
        return name;
    }
    
    /**
     * 设置国家危险废物名录名称
     *
     * @param name 国家危险废物名录名称
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 设置国家危险废物名录名称
     *
     * @param name 国家危险废物名录名称
     */
    public HseHazardousWasteDirectoryDTO name(String name) {
        this.name = name;
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
    public HseHazardousWasteDirectoryDTO fileId(String fileId) {
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
    public HseHazardousWasteDirectoryDTO tenantId(String tenantId) {
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
    public HseHazardousWasteDirectoryDTO tenantName(String tenantName) {
        this.tenantName = tenantName;
        return this;
    }
    
    
    /**
     * 转换成固废管理-国家危险废物名录知识库Entity对象
     *
     * @return 固废管理-国家危险废物名录知识库Entity对象
     */
    @Override
    protected HseHazardousWasteDirectory convertToEntity() {
        HseHazardousWasteDirectory hseHazardousWasteDirectory = new HseHazardousWasteDirectory();
        BeanUtils.copyProperties(this, hseHazardousWasteDirectory);
        return hseHazardousWasteDirectory;
    }
    
    /**
     * 转换成固废管理-国家危险废物名录知识库VO对象
     *
     * @return 固废管理-国家危险废物名录知识库VO对象
     */
    @Override
    protected HseHazardousWasteDirectoryVO convertToVo() {
        HseHazardousWasteDirectoryVO hseHazardousWasteDirectoryVO = new HseHazardousWasteDirectoryVO();
        BeanUtils.copyProperties(this, hseHazardousWasteDirectoryVO);
        return hseHazardousWasteDirectoryVO;
    }
    
    @Override
    public String toString() {
        return "HseHazardousWasteDirectoryDTO{" +
                
                "appId='" + super.getAppId() + "\'," +
                "type='" + type + "\'," +
                "wasteCode='" + wasteCode + "\'," +
                "name='" + name + "\'," +
                "fileId='" + fileId + "\'," +
                "tenantId='" + tenantId + "\'," +
                "tenantName='" + tenantName + "\'," +
                "remark='" + super.getRemark() + "\'" +
                '}';
    }
    
}