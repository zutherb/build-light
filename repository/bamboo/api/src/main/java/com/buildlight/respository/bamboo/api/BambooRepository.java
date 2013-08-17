package com.buildlight.respository.bamboo.api;

import com.buildlight.respository.bamboo.model.BambooPlanResponse;
import com.buildlight.respository.bamboo.model.BambooResultResponse;

/**
 * @author zutherb
 */
public interface BambooRepository {
    BambooResultResponse getResultResponse();

    BambooPlanResponse getPlanResponse();
}
