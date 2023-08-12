package com.security.wasteWarehousing.service;

import com.alibaba.fastjson.JSONObject;
import com.security.wasteWarehousing.domain.HseHazardousWasteWarehousing;
import com.security.wasteWarehousing.domain.HseHazardousWasteWarehousingDTO;
import com.security.wasteWarehousing.domain.HseHazardousWasteWarehousingMapper;
import com.security.wasteWarehousing.domain.HseHazardousWasteWarehousingVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class WasteWarehousingService {
    
    private static final Logger logger = LoggerFactory.getLogger(WasteWarehousingService.class);
    
    @Autowired
    HseHazardousWasteWarehousingMapper wasteWarehousingMapper;
    
    
    /**
     * 新增
     *
     * @param dto
     */
    public void insert(HseHazardousWasteWarehousingDTO dto) {
        logger.info("dto:{}", JSONObject.toJSONString(dto));
        HseHazardousWasteWarehousing wasteGeneration = new HseHazardousWasteWarehousing();
        BeanUtils.copyProperties(dto, wasteGeneration);
        logger.info("wasteGeneration:{}", JSONObject.toJSONString(wasteGeneration));
        wasteWarehousingMapper.insert(wasteGeneration);
    }
    
    /**
     * 查询
     *
     * @param dto
     */
    public List<HseHazardousWasteWarehousingVO> selectByEn(HseHazardousWasteWarehousingDTO dto) {
        List<HseHazardousWasteWarehousing> generations = wasteWarehousingMapper.selectByEntity(dto);
        
        List<HseHazardousWasteWarehousingVO> generationVOS = generations.stream()
                .map(en -> {
                    HseHazardousWasteWarehousingVO generationVO = new HseHazardousWasteWarehousingVO();
                    BeanUtils.copyProperties(en, generationVO);
                    return generationVO;
                }).collect(Collectors.toList());
        
        logger.info("generationVOS:{}", JSONObject.toJSONString(generationVOS));
        return generationVOS;
    }
}