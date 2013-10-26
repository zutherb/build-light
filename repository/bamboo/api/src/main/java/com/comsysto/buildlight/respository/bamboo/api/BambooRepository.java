package com.comsysto.buildlight.respository.bamboo.api;

import com.comsysto.buildlight.respository.bamboo.model.BambooPlanResponse;
import com.comsysto.buildlight.respository.bamboo.model.BambooResultResponse;
import com.comsysto.buildlight.respository.common.BuildServerRepository;

/**
 * @author zutherb
 */
public interface BambooRepository extends BuildServerRepository {
    BambooResultResponse getResultResponse();

    BambooPlanResponse getPlanResponse();
}
