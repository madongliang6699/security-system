package com.security.wasteGeneration.controller;

import com.security.common.core.JsonResult;
import com.security.wasteGeneration.domain.dto.HseHazardousWasteGenerationDTO;
import com.security.wasteGeneration.service.WasteGenerationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 固废管理-危废产生 控制器
 * 要求: 前端入参 DTO 业务交互 DTO 数据库交互 Entity 返回数据 VO
 *
 * @author A
 * @version v1.0
 * @File HseHazardousWasteGenerationController.java
 * @Desc 固废管理-危废产生
 * @Date 2023-07-11
 * @Encoding UTF-8
 * @Description Copyright (c) 2004-2023 All Rights Reserved.
 */
@RestController("HseHazardousWasteGenerationController-v0")
@RequestMapping("/hseHazardousWasteGeneration")
public class WasteGenerationController {
    
    private static final Logger logger = LoggerFactory.getLogger(WasteGenerationController.class);
    
    @Autowired
    WasteGenerationService wasteGenerationService;
    
    
    
    /**
     * 新增 固废管理-危废产生
     *
     * @param dto       实体对象
     * @param tokenInfo token信息
     * @return RespInfo 业务信息
     */
    @RequestMapping("/insert")
    public JsonResult<String> insert(HseHazardousWasteGenerationDTO dto) {
        Assert.notNull(dto, "参数为空");
        Assert.hasLength(dto.getWasteBaseId(), "危废id为空");
        Assert.notNull(dto.getGenerationTime(), "产生时间为空");
        Assert.hasLength(dto.getGenerationCode(), "产生批次编号为空");
        Assert.notNull(dto.getGenerationQuantity(), "产生量为空");
        wasteGenerationService.insert(dto);
        return JsonResult.buildSuccess("ok");
    }
    
    
    

}