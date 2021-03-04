package com.backend.testing.service.dataproviders.user.impl;

import com.backend.testing.configuration.ConfigHelper;
import com.backend.testing.dto.UserDto;
import com.backend.testing.enumerations.StatusCodes;
import com.backend.testing.enumerations.UserApiPath;
import com.backend.testing.service.dataproviders.user.api.UserDataProvider;
import com.backend.testing.service.rest.api.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("restUserDataProvider")
public class RestUserDataProvider implements UserDataProvider {
    private static final String URL_PATH_SUFFIX = "/user";
    private String baseUrl;

    private RestClient client;
    private ConfigHelper configHelper;
    private Converter<String, UserDto> jsonToUserDtoConverter;
    private Converter<String, List<UserDto>> jsonToListUserDtoConverter;
    private Converter<UserDto, Map<String, String>> mapToUserDtoConverter;

    @Autowired
    public RestUserDataProvider(RestClient client,
                                ConfigHelper configHelper,
                                @Qualifier("jsonToUserDtoConverter") Converter<String, UserDto> jsonToUserDtoConverter,
                                @Qualifier("userDtoToMapConverter") Converter<UserDto, Map<String, String>> mapToUserDtoConverter,
                                @Qualifier("jsonToListUserDtoConverter") Converter<String, List<UserDto>> jsonToListUserDtoConverter
                                ) {
        this.client = client;
        this.configHelper = configHelper;
        this.mapToUserDtoConverter = mapToUserDtoConverter;
        this.jsonToUserDtoConverter = jsonToUserDtoConverter;
        this.jsonToListUserDtoConverter = jsonToListUserDtoConverter;
    }

    @PostConstruct
    public void init() {
        baseUrl = configHelper.getApplicationUrl().concat(URL_PATH_SUFFIX);
    }

   @Override
    public List<UserDto> getAllUsers(int limit, int offset) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("limit", String.valueOf(limit));
        params.put("offset", String.valueOf(offset));

        String response = client.get(baseUrl.concat(UserApiPath.GET_ALL_USERS.getPath()), params, StatusCodes.OK);
        return jsonToListUserDtoConverter.convert(response);
    }

    @Override
    public UserDto getUserById(long userId) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(userId));

        String response = client.get(baseUrl.concat(UserApiPath.GET_USER_BY_ID.getPath()), params, StatusCodes.OK);
        return jsonToUserDtoConverter.convert(response);
    }

    @Override
    public UserDto createUser(UserDto user) throws IOException {
        Map<String, String> body = mapToUserDtoConverter.convert(user);

        String response = client.post(baseUrl.concat(UserApiPath.CREATE_USER.getPath()), null, body, StatusCodes.CREATED);
        return jsonToUserDtoConverter.convert(response);
    }

    @Override
    public UserDto updateUser(UserDto user) throws IOException {
        Map<String, String> body = mapToUserDtoConverter.convert(user);

        String response = client.put(baseUrl.concat(UserApiPath.UPDATE_USER.getPath()), null, body, StatusCodes.OK);
        return jsonToUserDtoConverter.convert(response);
    }

    @Override
    public String deleteUserById(long userId) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(userId));

        return client.delete(baseUrl.concat(UserApiPath.DELETE_USER_BY_ID.getPath()), params, StatusCodes.OK);
    }

    @Override
    public String createUserNegative(UserDto user) throws IOException {
        Map<String, String> body = mapToUserDtoConverter.convert(user);
        String response = client.post(baseUrl.concat(UserApiPath.CREATE_USER.getPath()), null, body, StatusCodes.NOT_FOUND);

        return response;
    }

    @Override
    public String deleteUserNegative(long userId) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(userId));

        return client.delete(baseUrl.concat(UserApiPath.DELETE_USER_BY_ID.getPath()), params, StatusCodes.NOT_FOUND);
    }
}
