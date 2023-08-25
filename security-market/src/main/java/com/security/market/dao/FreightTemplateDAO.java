package com.security.market.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.security.common.dao.BaseDAO;
import com.security.market.entity.FreightTemplateDO;
import com.security.market.mapper.FreightTemplateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <p>
 * 运费模板 DAO 接口
 * </p>
 *
 * @author zhonghuashishan
 */
@Repository
public class FreightTemplateDAO extends BaseDAO<FreightTemplateMapper, FreightTemplateDO> implements Serializable {
    
    @Autowired
    private FreightTemplateMapper freightTemplateMapper;
    
    /**
     * 通过区域ID查找运费模板
     */
    public FreightTemplateDO getByRegionId(String regionId) {
        QueryWrapper<FreightTemplateDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("region_id", regionId);
        return freightTemplateMapper.selectOne(queryWrapper);
    }
    
}
