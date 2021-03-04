package com.backend.testing.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigHelper {

    @Value("${app.host}")
    private String applicationUrl;

    public String getApplicationUrl() {
        return applicationUrl;
    }
}