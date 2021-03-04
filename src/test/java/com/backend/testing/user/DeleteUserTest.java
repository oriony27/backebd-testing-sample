package com.backend.testing.user;

import com.backend.testing.dto.UserDto;
import com.backend.testing.user.api.BaseUserTest;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class DeleteUserTest extends BaseUserTest {

    @Test
    public void deleteUserPositive() throws IOException {
        UserDto userToCreate = new UserDto(1, "CREATED_BY_AUTOMATION", 2);
        UserDto createdUser = restProvider.createUser(userToCreate);

        String actual = restProvider.deleteUserById(createdUser.getId());
        String expected = databaseProvider.deleteUserById(createdUser.getId());
        Assert.assertEquals("User hasn't been deleted!", expected, actual);
    }

    @Test
    public void deleteUserNegative_deleteUserThatDoesNotExists() throws IOException {
        long unrealId = 9999999999l;
        String actual = restProvider.deleteUserNegative(unrealId);
        String expected = databaseProvider.deleteUserNegative(unrealId);
        Assert.assertEquals("It's possible to delete user that doesn't exists!", expected, actual);
    }
}
