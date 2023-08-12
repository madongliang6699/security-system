package com.security.wasteWarehousing.controller;

import com.security.common.core.JsonResult;
import com.security.wasteWarehousing.domain.HseHazardousWasteWarehousingDTO;
import com.security.wasteWarehousing.service.WasteWarehousingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
@RestController()
@RequestMapping("/wasteWarehousing")
public class WasteWarehousingController {
    
    private static final Logger logger = LoggerFactory.getLogger(WasteWarehousingController.class);
    
    @Autowired
    WasteWarehousingService wasteWarehousingService;
    
    
    /**
     * 新增 固废管理-危废产生
     *
     * @param dto 实体对象
     *
     * @return RespInfo 业务信息
     */
    @RequestMapping("/insert")
    public JsonResult<String> insert(@RequestBody HseHazardousWasteWarehousingDTO dto) {
        wasteWarehousingService.insert(dto);
        return JsonResult.buildSuccess("ok");
    }
    
    
    /**
     * 新增 固废管理-危废产生
     *
     * @param dto 实体对象
     *
     * @return RespInfo 业务信息
     */
    @RequestMapping("/selectByEn")
    public JsonResult<String> selectByEn(HseHazardousWasteWarehousingDTO dto) {
        wasteWarehousingService.selectByEn(dto);
        return JsonResult.buildSuccess("ok");
    }
    
    
}