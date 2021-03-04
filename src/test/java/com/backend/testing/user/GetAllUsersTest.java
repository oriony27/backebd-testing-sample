package com.backend.testing.user;

import com.backend.testing.dto.UserDto;
import com.backend.testing.user.api.BaseUserTest;
import com.backend.testing.utils.AssertionUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class GetAllUsersTest extends BaseUserTest {

    @Test
    public void getAllUsersPositive() throws IOException {
        int amountOfUsers = userData.getNumberOfRecords();

        for (int i = 1; i < amountOfUsers + 1; i++) {
            for (int j = 1; j < amountOfUsers + 1; j++) {
                List<UserDto> expected = databaseProvider.getAllUsers(i, j);
                List<UserDto> actual = restProvider.getAllUsers(i, j);
                AssertionUtils.assertObjectsList(expected, actual, UserDto::getId);
            }
        }
    }
}
