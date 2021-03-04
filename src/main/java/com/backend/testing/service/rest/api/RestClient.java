package com.backend.testing.service.rest.api;

import com.backend.testing.enumerations.StatusCodes;

import java.io.IOException;
import java.util.Map;

public interface RestClient {
    String get(String path, Map<String, String> params, StatusCodes statusCode) throws IOException;
    String post(String path, Map<String, String> params, Map<String, String> body, StatusCodes statusCode) throws IOException;
    String put(String path, Map<String, String> params, Map<String, String> body, StatusCodes statusCode) throws IOException;
    String delete(String path, Map<String, String> params, StatusCodes statusCode) throws IOException;
}
