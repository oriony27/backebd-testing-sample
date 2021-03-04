package com.backend.testing.user;

import com.backend.testing.dto.UserDto;
import com.backend.testing.user.api.BaseUserTest;
import com.backend.testing.utils.AssertionUtils;
import org.junit.Test;

import java.io.IOException;

public class UpdateUserTest extends BaseUserTest {

    @Test
    public void updateUserByIdPositive() throws IOException {
        long userId = userData.getRandomId();
        UserDto userBeforeUpdate = databaseProvider.getUserById(userId);

        try {
            UserDto userToUpdate = new UserDto(userBeforeUpdate.getId(), "UPDATED_USER", 43);
            UserDto actual = restProvider.updateUser(userToUpdate);
            UserDto expected = databaseProvider.updateUser(userToUpdate);
            AssertionUtils.assertObjects(expected, actual);
        } finally {
            restProvider.updateUser(userBeforeUpdate);
        }
    }
}