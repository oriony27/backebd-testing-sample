package com.backend.testing.service.dataproviders.user.api;

import com.backend.testing.dto.UserDto;

import java.io.IOException;
import java.util.List;

public interface UserDataProvider {
    List<UserDto> getAllUsers(int limit, int offset) throws IOException;
    UserDto getUserById(long userId) throws IOException;
    UserDto createUser(UserDto user) throws IOException;
    UserDto updateUser(UserDto user) throws IOException;
    String deleteUserById(long userId) throws IOException;

    String createUserNegative(UserDto user) throws IOException;
    String deleteUserNegative(long userId) throws IOException;
}