package com.backend.testing.user;

import com.backend.testing.dto.UserDto;
import com.backend.testing.user.api.BaseUserTestClass;
import com.backend.testing.utils.AssertionUtils;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import java.io.IOException;

public class UpdateUserTest extends BaseUserTestClass {

    @Test
    @DisplayName("Update existing user.")
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