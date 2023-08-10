package com.security.wasteGeneration.domain.mapper;

import com.cybstar.base.secureBase.solidWaste.hazardousWaste.dto.HseHazardousWasteWarehousingDTO;
import com.cybstar.base.secureBase.solidWaste.hazardousWaste.entity.HseHazardousWasteWarehousing;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.cybstar.base.secureBase.solidWaste.hazardousWaste.entity.HseHazardousWasteOutsourcing;
import com.cybstar.base.secureBase.solidWaste.hazardousWaste.dto.HseHazardousWasteOutsourcingDTO;
import java.io.Serializable;
import java.util.List;

/**
 * 固废处理-危废委外利用处置数据库访问控制类
 *
 * @author A
 * @File HseHazardousWasteOutsourcing.java
 * @version v 0.1
 * @DateTime 2023-07-18 11:04:20
 * @Encoding UTF-8
 * @Description
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
@Repository
public interface HseHazardousWasteOutsourcingMapper{

    /**
     * <p>
     * 获取主键ID
     * </p>
     *
     * @return 主键ID
     */
    Long getSid();

    /**
     * <p>
     * 插入一条记录
     * </p>
     *
     * @param entity 实体对象
     * @return 影响行数
     */
    Integer insert(HseHazardousWasteOutsourcing entity);
    
    /**
     * <p>
     * 批量插入
     * </p>
     *
     * @param entityList 实体对象列表
     * @return 影响行数
     */

    Integer insertList(@Param("entityList") List<HseHazardousWasteOutsourcing> entityList);

    /**
     * <p>
     * 根据 ID 删除
     * </p>
     *
     * @param entity 实体对象
     * @return 影响行数
     */
    Integer deleteById(HseHazardousWasteOutsourcing entity);

    /**
     * <p>
     * 删除（根据ID 批量删除）
     * </p>
     *
     * @param idList 主键ID列表
     * @return 影响行数
     */
    Integer deleteBatchIds(List<? extends Serializable> idList);

    /**
     * <p>
     * 根据 ID 修改
     * </p>
     *
     * @param entity 实体对象
     * @return 影响行数
     */
    Integer updateById(HseHazardousWasteOutsourcing entity);

    /**
     * <p>
     * 根据 ID 查询
     * </p>
     *
     * @param entity 实体对象
     * @return T
     */
    HseHazardousWasteOutsourcing selectById(HseHazardousWasteOutsourcing entity);

    /**
     * <p>
     * 查询（根据ID 批量查询）
     * </p>
     *
     * @param idList 主键ID列表
     * @return 固废处理-危废委外利用处置对象集合 
     */
    List<HseHazardousWasteOutsourcing> selectBatchIds(List<? extends Serializable> idList);
    
    /**
     * <p>
     * 根据 AppID 查询
     * </p>
     *
     * @param entity 实体对象
     * @return 固废处理-危废委外利用处置对象集合
     */
    List<HseHazardousWasteOutsourcing> selectByAppId(HseHazardousWasteOutsourcingDTO entity);
    
    /**
     * <p>
     * 根据实体对象属性查询
     * </p>
     *
     * @param entity 实体对象
     * @return 固废处理-危废委外利用处置对象集合
     */
    List<HseHazardousWasteOutsourcing> selectByEntity(HseHazardousWasteOutsourcing entity);
    
    /**
     * <p>
     * 根据 keyWord 模糊查询
     * </p>
     *
     * @param dto 实体对象
     * @return 固废处理-危废委外利用处置对象集合
     */
    List<HseHazardousWasteOutsourcing> selectPageByKeyWord(HseHazardousWasteOutsourcingDTO dto);

    /**
     * 根据出厂时间参数的年份，查询出当年最新的一条数据
     *
     * @param dto
     * @return
     */
    HseHazardousWasteOutsourcing selectLastOneForYear(HseHazardousWasteOutsourcingDTO dto);
}