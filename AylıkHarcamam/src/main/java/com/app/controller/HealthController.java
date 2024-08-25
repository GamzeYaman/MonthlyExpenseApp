package com.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/health/controller")
public class HealthController {

    @GetMapping
    public String getController() {
        return "Health Controller has been worked!!!";
    }
}
