package com.backend.testing.service.rest.impl;

import com.backend.testing.enumerations.StatusCodes;
import com.backend.testing.service.rest.api.RestClient;
import com.backend.testing.utils.GsonUtils;
import com.squareup.okhttp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service("restClientImpl")
public class OkHttpRestClientImpl implements RestClient {
    private OkHttpClient client;

    @Autowired
    public OkHttpRestClientImpl(OkHttpClient client) {
        this.client = client;
    }

    @Override
    public String get(String path, Map<String, String> params, StatusCodes statusCode) throws IOException {
        Request request = prepareRequest().url(prepareUrl(path, params).build()).get().build();
        return executeRequest(request, statusCode);
    }

    @Override
    public String post(String path, Map<String, String> params, Map<String, String> body, StatusCodes statusCode) throws IOException {
        Request request = prepareRequest().url(prepareUrl(path, params).build()).post(prepareRequestBody(body)).build();
        return executeRequest(request, statusCode);
    }

    @Override
    public String put(String path, Map<String, String> params, Map<String, String> body, StatusCodes statusCode) throws IOException {
        Request request = prepareRequest().url(prepareUrl(path, params).build()).put(prepareRequestBody(body)).build();
        return executeRequest(request, statusCode);
    }

    @Override
    public String delete(String path, Map<String, String> params, StatusCodes statusCode) throws IOException {
        Request request = prepareRequest().url(prepareUrl(path, params).build()).delete().build();
        return executeRequest(request, statusCode);
    }

    private Request.Builder prepareRequest() {
        return new Request.Builder().header("Content-Type", "application/json");
    }

    private RequestBody prepareRequestBody(Map<String, String> body) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), GsonUtils.toJson(body));
        return requestBody;
    }

    private HttpUrl.Builder prepareUrl(String url, Map<String, String> params) {
        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();

        if (params != null) {
            for(Map.Entry<String, String> param : params.entrySet()) {
                httpBuilder.addQueryParameter(param.getKey(),param.getValue());
            }
        }

        return httpBuilder;
    }

    private String executeRequest(Request request, StatusCodes statusCodes) throws IOException {
        Response response = client.newCall(request).execute();
        assert statusCodes.getStatusCode() == response.code();
        return response.body().string();
    }
}
