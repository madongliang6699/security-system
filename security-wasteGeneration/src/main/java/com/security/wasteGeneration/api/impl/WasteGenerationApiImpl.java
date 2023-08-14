package com.security.wasteGeneration.api.impl;

import com.security.common.core.JsonResult;
import com.security.wasteGeneration.api.WasteGenerationApi;
import com.security.wasteGeneration.service.WasteGenerationService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService(version = "1.0.0", interfaceClass = WasteGenerationApi.class, retries = 0)
public class WasteGenerationApiImpl implements WasteGenerationApi {
    
    @Autowired
    WasteGenerationService wasteGenerationService;
    
    @Override
    public JsonResult<Boolean> checkGenerationStatusIs0AndDestinationIs1(List<String> generationIds) {
        return JsonResult.buildSuccess(wasteGenerationService.checkGenerationStatusIs0AndDestinationIs1(generationIds));
    }
    
    @Override
    public void pushGenerationStatusTo1(List<String> generationIds) {
    
    }
    
    @Override
    public void pushGenerationStatusTo0(List<String> generationIds) {
    
    }
}
