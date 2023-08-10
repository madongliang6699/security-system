package com.security.wasteGeneration.domain.mapper;

import com.cybstar.base.secureBase.solidWaste.hazardousWaste.dto.HseHazardousWasteWarehousingSubtractRecordDTO;
import com.cybstar.base.secureBase.solidWaste.hazardousWaste.entity.HseHazardousWasteWarehousingSubtractRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 固废管理-危废入库单剩余量的减量去向记录数据库访问控制类
 *
 * @author A
 * @version v 0.1
 * @File HseHazardousWasteWarehousingSubtractRecord.java
 * @DateTime 2023-07-18 14:52:57
 * @Encoding UTF-8
 * @Description Copyright (c) 2004-2023 All Rights Reserved.
 */
@Repository
public interface HseHazardousWasteWarehousingSubtractRecordMapper {
    
    /**
     * <p>
     * 插入一条记录
     * </p>
     *
     * @param entity 实体对象
     * @return 影响行数
     */
    Integer insert(HseHazardousWasteWarehousingSubtractRecord entity);
    
    
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
    Integer updateById(HseHazardousWasteWarehousingSubtractRecord entity);
    
    /**
     * <p>
     * 根据 ID 查询
     * </p>
     *
     * @param entity 实体对象
     * @return T
     */
    HseHazardousWasteWarehousingSubtractRecord selectById(Long sid);
    
    /**
     * <p>
     * 查询（根据ID 批量查询）
     * </p>
     *
     * @param idList 主键ID列表
     * @return 固废管理-危废入库单剩余量的减量去向记录对象集合
     */
    List<HseHazardousWasteWarehousingSubtractRecord> selectBatchIds(List idList);
    
    
    /**
     * <p>
     * 根据 AppID 查询
     * </p>
     *
     * @param entity 实体对象
     * @return 固废管理-危废入库单剩余量的减量去向记录对象集合
     */
    List<HseHazardousWasteWarehousingSubtractRecord> selectByEntity(HseHazardousWasteWarehousingSubtractRecord entity);
    
    
    /**
     * <p>
     * 根据 关键字 查询
     * </p>
     *
     * @param dto 实体对象
     * @return 固废管理-危废入库单剩余量的减量去向记录对象集合
     */
    List<HseHazardousWasteWarehousingSubtractRecord> selectByKeyWord(HseHazardousWasteWarehousingSubtractRecordDTO dto);
    
}