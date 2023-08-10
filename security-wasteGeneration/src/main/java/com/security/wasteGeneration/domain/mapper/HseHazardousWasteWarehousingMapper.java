package com.security.wasteGeneration.domain.mapper;

import com.cybstar.base.secureBase.solidWaste.hazardousWaste.dto.HseHazardousWasteGenerationDTO;
import com.cybstar.base.secureBase.solidWaste.hazardousWaste.dto.HseHazardousWasteWarehousingDTO;
import com.cybstar.base.secureBase.solidWaste.hazardousWaste.entity.HseHazardousWasteGeneration;
import com.cybstar.base.secureBase.solidWaste.hazardousWaste.entity.HseHazardousWasteWarehousing;
import com.cybstar.base.secureBase.solidWaste.hazardousWaste.vo.HseHazardousWasteWarehousingVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 固废管理-危废入库数据库访问控制类
 *
 * @author A
 * @version v 0.1
 * @File HseHazardousWasteWarehousing.java
 * @DateTime 2023-07-13 17:18:17
 * @Encoding UTF-8
 * @Description Copyright (c) 2004-2023 All Rights Reserved.
 */
@Repository
public interface HseHazardousWasteWarehousingMapper {
    
    /**
     * <p>
     * 插入一条记录
     * </p>
     *
     * @param entity 实体对象
     * @return 影响行数
     */
    Integer insert(HseHazardousWasteWarehousing entity);
    
    
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
    Integer updateById(HseHazardousWasteWarehousing entity);
    
    /**
     * <p>
     * 根据 ID 查询
     * </p>
     *
     * @param entity 实体对象
     * @return T
     */
    HseHazardousWasteWarehousing selectById(Long sid);
    
    /**
     * <p>
     * 查询（根据ID 批量查询）
     * </p>
     *
     * @param idList 主键ID列表
     * @return 固废管理-危废入库对象集合
     */
    List<HseHazardousWasteWarehousing> selectBatchIds(List idList);
    
    
    /**
     * <p>
     * 根据 AppID 查询
     * </p>
     *
     * @param entity 实体对象
     * @return 固废管理-危废入库对象集合
     */
    List<HseHazardousWasteWarehousing> selectByEntity(HseHazardousWasteWarehousingDTO entity);
    
    
    /**
     * <p>
     * 根据 关键字 查询
     * </p>
     *
     * @param dto 实体对象
     * @return 固废管理-危废入库对象集合
     */
    List<HseHazardousWasteWarehousingVO> selectByKeyWord(HseHazardousWasteWarehousingDTO dto);
    
    /**
     * 根据入库时间参数的年份，查询出当年最新的一条数据
     *
     * @param dto
     * @return
     */
    HseHazardousWasteWarehousing selectLastOneForYear(HseHazardousWasteWarehousingDTO dto);
    
}