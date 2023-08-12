package com.security.wasteGeneration.domain.mapper;

import com.security.wasteGeneration.domain.dto.HseHazardousWasteGenerationDTO;
import com.security.wasteGeneration.domain.entity.HseHazardousWasteGeneration;
import org.springframework.stereotype.Repository;

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
@Repository
public interface HseHazardousWasteGenerationMapper {
    
    /**
     * <p>
     * 插入一条记录
     * </p>
     *
     * @param entity 实体对象
     *
     * @return 影响行数
     */
    Integer insert(HseHazardousWasteGeneration entity);
    
    
    /**
     * <p>
     * 根据 ID 删除
     * </p>
     *
     * @param entity 实体对象
     *
     * @return 影响行数
     */
    Integer deleteById(Long sid);
    
    /**
     * <p>
     * 删除（根据ID 批量删除）
     * </p>
     *
     * @param idList 主键ID列表
     *
     * @return 影响行数
     */
    Integer deleteBatchIds(List idList);
    
    /**
     * <p>
     * 查询（根据ID 批量查询）
     * </p>
     *
     * @param idList 主键ID列表
     *
     * @return 固废管理-危废产生对象集合
     */
    List<HseHazardousWasteGeneration> selectBatchIds(List idList);
    
    
    /**
     * <p>
     * 根据 AppID 查询
     * </p>
     *
     * @param entity 实体对象
     *
     * @return 固废管理-危废产生对象集合
     */
    List<HseHazardousWasteGeneration> selectByEntity(HseHazardousWasteGenerationDTO dto);
    
}