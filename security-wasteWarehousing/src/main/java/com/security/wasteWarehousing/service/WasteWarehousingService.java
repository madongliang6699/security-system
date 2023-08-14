package com.security.wasteWarehousing.service;

import com.alibaba.fastjson.JSONObject;
import com.security.common.core.JsonResult;
import com.security.wasteGeneration.api.WasteGenerationApi;
import com.security.wasteWarehousing.domain.HseHazardousWasteWarehousing;
import com.security.wasteWarehousing.domain.HseHazardousWasteWarehousingDTO;
import com.security.wasteWarehousing.domain.HseHazardousWasteWarehousingMapper;
import com.security.wasteWarehousing.domain.HseHazardousWasteWarehousingVO;
import com.security.wasteWarehousing.exception.WasteWarehousingBizException;
import com.security.wasteWarehousing.exception.WasteWarehousingErrorCodeEnum;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class WasteWarehousingService {
    
    private static final Logger logger = LoggerFactory.getLogger(WasteWarehousingService.class);
    
    @Autowired
    HseHazardousWasteWarehousingMapper wasteWarehousingMapper;
    @DubboReference(version = "1.0.0")
    WasteGenerationApi wasteGenerationApi;
    
    /**
     * 新增
     *
     * @param dto
     */
    public void insert(HseHazardousWasteWarehousingDTO dto) {
    
        JsonResult<Boolean> booleanJsonResult = wasteGenerationApi.checkGenerationStatusIs0AndDestinationIs1(Arrays.asList(dto.getGenerationIds().split(",")));
        if (!booleanJsonResult.getData()) {
            throw new WasteWarehousingBizException(WasteWarehousingErrorCodeEnum.WAREHOUSING_XXXX_YYY);
        }
    
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