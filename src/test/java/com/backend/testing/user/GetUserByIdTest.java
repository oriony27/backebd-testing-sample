package com.backend.testing.user;

import com.backend.testing.dto.UserDto;
import com.backend.testing.user.api.BaseUserTestClass;
import com.backend.testing.utils.AssertionUtils;
import com.backend.testing.utils.RandomUtils;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class GetUserByIdTest extends BaseUserTestClass {

    @Test
    @DisplayName("Get user by id.")
    public void getUserByIdPositive() throws IOException {
        long userId = userData.getRandomId();
        UserDto expected = databaseProvider.getUserById(userId);
        UserDto actual = restProvider.getUserById(userId);
        AssertionUtils.assertObjects(expected, actual);
    }

    @Test
    @DisplayName("Get user by unreal id.")
    public void getMultipleUsersByIdPositive() throws IOException {
        List<UserDto> users = restProvider.getAllUsers(RandomUtils.getRandomNumberLessThen(10), RandomUtils.getRandomNumberLessThen(2));

        for (UserDto user : users) {
            UserDto expected = databaseProvider.getUserById(user.getId());
            UserDto actual = restProvider.getUserById(user.getId());
            AssertionUtils.assertObjects(expected, actual);
        }
    }
}