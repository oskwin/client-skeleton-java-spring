package eu.arrowhead.client.skeleton.executor.controller;

import eu.arrowhead.common.CommonConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExecutorController {
    //=================================================================================================
    // members

    //TODO: add your variables here

    //=================================================================================================
    // methods

    //-------------------------------------------------------------------------------------------------
    @GetMapping(path = CommonConstants.ECHO_URI)
    public String echoService() {
        return "Got it!";
    }

    //-------------------------------------------------------------------------------------------------
    //TODO: implement here your executor related REST end points
}