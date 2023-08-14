package com.security.wasteGeneration.api;

import com.security.common.core.JsonResult;

import java.util.List;

public interface WasteGenerationApi {
    
    
    /**
     * 校验所选的产生单子是否都存在，并且都是“待处置”状态（status为0）、去向都是“贮存”（destination为1）
     */
    JsonResult<Boolean> checkGenerationStatusIs0AndDestinationIs1(List<String> generationIds);
    
    /**
     * 推进产生单到已处置状态
     */
    void pushGenerationStatusTo1(List<String> generationIds);
    
    
    /**
     * 修改产生单回到待处置状态
     */
    void pushGenerationStatusTo0(List<String> generationIds);
}
