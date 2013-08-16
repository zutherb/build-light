package com.buildlight.mock.bamboo.controller;

import com.buildlight.mock.bamboo.simulator.BambooSimulator;
import com.buildlight.respository.bamboo.model.BambooBuildResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author zutherb
 */
@Controller
@RequestMapping("/api/latest")
public class BambooMock {

    private BambooSimulator simulator;

    @Autowired
    public BambooMock(BambooSimulator simulator) {
        this.simulator = simulator;
    }

    @RequestMapping(value = "result/{buildKey}.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BambooBuildResponse getBambooBuildResponse(@PathVariable("buildKey") String buildKey,
                                                      @RequestParam(value = "os_authType", required = false) String authType,
                                                      @RequestParam(value = "os_username", required = false) String username,
                                                      @RequestParam(value = "os_password", required = false) String password) {
        return simulator.getBambooBuildResponse();
    }


}
