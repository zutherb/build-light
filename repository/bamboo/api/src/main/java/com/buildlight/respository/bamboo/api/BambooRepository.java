package com.buildlight.respository.bamboo.api;

import com.buildlight.respository.bamboo.model.BambooBuildResponse;

/**
 * @author zutherb
 */
public interface BambooRepository {
    BambooBuildResponse getBambooBuildResponse();
}
