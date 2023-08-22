package com.security.wasteGeneration.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.security.wasteGeneration.domain.dto.HseHazardousWasteGenerationDTO;
import com.security.wasteGeneration.domain.entity.HseHazardousWasteGeneration;

import java.util.List;


/**
 * 固废管理-危废产生数据库访问控制类
 *
 * @author A
 * @version v 0.1
 * @File HseHazardousWasteGeneration.java
 * @DateTime 2023-07-11 18:04:36
 * @Encoding UTF-8
 * @Description Copyright (c) 2004-2023 All Rights Reserved.
 */
//@Repository  //这里不需要注解
public interface HseHazardousWasteGenerationMapper extends BaseMapper<HseHazardousWasteGeneration> {
    
    
    List<HseHazardousWasteGeneration> selectByEntity(HseHazardousWasteGenerationDTO dto);
    
}