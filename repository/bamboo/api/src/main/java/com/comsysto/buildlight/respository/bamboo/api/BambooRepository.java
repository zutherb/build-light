package com.comsysto.buildlight.respository.bamboo.api;

import com.comsysto.buildlight.respository.bamboo.model.BambooPlanResponse;
import com.comsysto.buildlight.respository.bamboo.model.BambooResultResponse;

/**
 * @author zutherb
 */
public interface BambooRepository {
    BambooResultResponse getResultResponse();

    BambooPlanResponse getPlanResponse();
}
