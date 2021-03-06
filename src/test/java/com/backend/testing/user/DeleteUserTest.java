package com.backend.testing.user;

import com.backend.testing.dto.UserDto;
import com.backend.testing.user.api.BaseUserTestClass;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class DeleteUserTest extends BaseUserTestClass {

    @Test
    @DisplayName("Delete user.")
    public void deleteUserPositive() throws IOException {
        UserDto userToCreate = new UserDto(1, "CREATED_BY_AUTOMATION", 2);
        UserDto createdUser = restProvider.createUser(userToCreate);

        String actual = restProvider.deleteUserById(createdUser.getId());
        String expected = databaseProvider.deleteUserById(createdUser.getId());
        Assert.assertEquals("User hasn't been deleted!", expected, actual);
    }

    @Test
    @DisplayName("Delete user that not exists.")
    public void deleteUserNegative_deleteUserThatDoesNotExists() throws IOException {
        long unrealId = 9999999999l;
        String actual = restProvider.deleteUserNegative(unrealId);
        String expected = databaseProvider.deleteUserNegative(unrealId);
        Assert.assertEquals("It's possible to delete user that doesn't exists!", expected, actual);
    }
}
