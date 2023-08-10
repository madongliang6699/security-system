package com.security.wasteGeneration.domain.mapper;

import com.cybstar.base.secureBase.solidWaste.hazardousWaste.dto.HseReceiveUnitBaseDTO;
import com.cybstar.base.secureBase.solidWaste.hazardousWaste.entity.HseReceiveUnitBase;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 固废管理-接收单位基础信息管理数据库访问控制类
 *
 * @author A
 * @version v 0.1
 * @File HseReceiveUnitBase.java
 * @DateTime 2023-07-10 15:20:24
 * @Encoding UTF-8
 * @Description Copyright (c) 2004-2023 All Rights Reserved.
 */
@Repository
public interface HseReceiveUnitBaseMapper {
    
    /**
     * <p>
     * 插入一条记录
     * </p>
     *
     * @param entity 实体对象
     * @return 影响行数
     */
    Integer insert(HseReceiveUnitBase entity);
    
    
    /**
     * <p>
     * 根据 ID 删除
     * </p>
     *
     * @param entity 实体对象
     * @return 影响行数
     */
    Integer deleteById(Long sid);
    
    /**
     * <p>
     * 删除（根据ID 批量删除）
     * </p>
     *
     * @param idList 主键ID列表
     * @return 影响行数
     */
    Integer deleteBatchIds(List idList);
    
    /**
     * <p>
     * 根据 ID 修改
     * </p>
     *
     * @param entity 实体对象
     * @return 影响行数
     */
    Integer updateById(HseReceiveUnitBase entity);
    
    /**
     * <p>
     * 根据 ID 查询
     * </p>
     *
     * @param entity 实体对象
     * @return T
     */
    HseReceiveUnitBase selectById(Long sid);
    
    /**
     * <p>
     * 查询（根据ID 批量查询）
     * </p>
     *
     * @param idList 主键ID列表
     * @return 固废管理-接收单位基础信息管理对象集合
     */
    List<HseReceiveUnitBase> selectBatchIds(List idList);
    
    
    /**
     * <p>
     * 根据 AppID 查询
     * </p>
     *
     * @param entity 实体对象
     * @return 固废管理-接收单位基础信息管理对象集合
     */
    List<HseReceiveUnitBase> selectByEntity(HseReceiveUnitBase entity);
    
    
    /**
     * <p>
     * 根据 关键字 查询
     * </p>
     *
     * @param dto 实体对象
     * @return 固废管理-接收单位基础信息管理对象集合
     */
    List<HseReceiveUnitBase> selectByKeyWord(HseReceiveUnitBaseDTO dto);
    
}