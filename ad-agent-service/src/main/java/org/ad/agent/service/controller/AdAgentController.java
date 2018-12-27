package org.ad.agent.service.controller;

import org.ad.agent.service.service.AdAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/ad-agent", produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public class AdAgentController {

    @Autowired
    private AdAgentService service;
}
