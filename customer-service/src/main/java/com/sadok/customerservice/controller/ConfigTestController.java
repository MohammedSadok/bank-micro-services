package com.sadok.customerservice.controller;

import com.sadok.customerservice.config.GlobalConfig;
import lombok.AllArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RefreshScope
public class ConfigTestController {


    private GlobalConfig globalConfig;

    @GetMapping("/globalConfig")
    public GlobalConfig getConfig(){
        return globalConfig;
    }
}
