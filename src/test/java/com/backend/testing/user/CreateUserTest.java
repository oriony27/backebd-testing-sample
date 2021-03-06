package com.backend.testing.user;

import com.backend.testing.dto.UserDto;
import com.backend.testing.user.api.BaseUserTestClass;
import com.backend.testing.utils.AssertionUtils;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CreateUserTest extends BaseUserTestClass {

    @Test
    @DisplayName("Create user that can be added.")
    public void createUserPositive() throws IOException {
        UserDto userToCreate = new UserDto(1, "CREATED_BY_AUTOMATION", 2);
        try {
            UserDto actual = restProvider.createUser(userToCreate);
            UserDto expected = databaseProvider.updateUser(actual);
            AssertionUtils.assertObjects(expected, actual);
        } finally {
            restProvider.deleteUserById(userToCreate.getId());
        }
    }

    @Test
    @DisplayName("Create user that already exists.")
    public void createUserNegative_createUserThatAlreadyExists() throws IOException {
        UserDto existingUser = restProvider.getUserById(userData.getRandomId());

        String actual = restProvider.createUserNegative(existingUser);
        String expected = databaseProvider.createUserNegative(existingUser);
        Assert.assertEquals("It's possible to create user that already exists!", expected, actual);
    }
}