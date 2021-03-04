package com.backend.testing.configuration;

import com.squareup.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class BeanConfiguration {

    @Bean
    public OkHttpClient createHttpClient() {
        OkHttpClient client = new OkHttpClient();
        client.setReadTimeout(2000, TimeUnit.MILLISECONDS);
        client.setConnectTimeout(1500, TimeUnit.MILLISECONDS);
        client.setWriteTimeout(1500, TimeUnit.MILLISECONDS);
        return client;
    }
}