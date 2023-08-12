package com.security.wasteGeneration.service;

import com.alibaba.fastjson.JSONObject;
import com.security.wasteGeneration.domain.dto.HseHazardousWasteGenerationDTO;
import com.security.wasteGeneration.domain.entity.HseHazardousWasteGeneration;
import com.security.wasteGeneration.domain.mapper.HseHazardousWasteGenerationMapper;
import com.security.wasteGeneration.domain.vo.HseHazardousWasteGenerationVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class WasteGenerationService {
    
    private static final Logger logger = LoggerFactory.getLogger(WasteGenerationService.class);
    
    @Autowired
    HseHazardousWasteGenerationMapper wasteGenerationMapper;
    
    
    /**
     * 新增
     *
     * @param dto
     */
    public void insert(HseHazardousWasteGenerationDTO dto) {
        logger.info("dto:{}", JSONObject.toJSONString(dto));
        HseHazardousWasteGeneration wasteGeneration = new HseHazardousWasteGeneration();
        BeanUtils.copyProperties(dto, wasteGeneration);
        logger.info("wasteGeneration:{}", JSONObject.toJSONString(wasteGeneration));
        wasteGenerationMapper.insert(wasteGeneration);
    }
    
    /**
     * 查询
     *
     * @param dto
     */
    public List<HseHazardousWasteGenerationVO> selectByEn(HseHazardousWasteGenerationDTO dto) {
        List<HseHazardousWasteGeneration> generations = wasteGenerationMapper.selectByEntity(dto);
        
        List<HseHazardousWasteGenerationVO> generationVOS = generations.stream()
                .map(en -> {
                    HseHazardousWasteGenerationVO generationVO = new HseHazardousWasteGenerationVO();
                    BeanUtils.copyProperties(en, generationVO);
                    return generationVO;
                }).collect(Collectors.toList());
        
        logger.info("generationVOS:{}", JSONObject.toJSONString(generationVOS));
        return generationVOS;
    }
}