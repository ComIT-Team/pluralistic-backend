package org.comit.pluralisticsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class VersionController {
    @GetMapping("/version")
    public String getAppVersion() {
        return "1.0.1"; // Replace with your actual version retrieval logic
    }
}
