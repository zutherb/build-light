package com.buildlight.mock.controller.jenkins;

import com.buildlight.mock.simulator.JenkinsSimulator;
import com.buildlight.respository.jenkins.model.JenkinsBuildResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author zutherb
 */
@Controller
public class JenkinsMock {

    private JenkinsSimulator simulator;

    @Autowired
    public JenkinsMock(JenkinsSimulator simulator) {
        this.simulator = simulator;
    }

    @RequestMapping(value = "{name}/api/json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JenkinsBuildResponse getBambooBuildResponse(@PathVariable("name") String name) {
        return simulator.getBuildResponse();
    }


}
