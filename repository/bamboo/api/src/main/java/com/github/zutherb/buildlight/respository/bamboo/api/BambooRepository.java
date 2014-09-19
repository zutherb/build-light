package com.github.zutherb.buildlight.respository.bamboo.api;

import com.github.zutherb.buildlight.respository.bamboo.model.BambooPlanResponse;
import com.github.zutherb.buildlight.respository.bamboo.model.BambooResultResponse;
import com.github.zutherb.buildlight.respository.common.BuildServerRepository;

/**
 * @author zutherb
 */
public interface BambooRepository extends BuildServerRepository {
    BambooResultResponse getResultResponse();

    BambooPlanResponse getPlanResponse();
}
