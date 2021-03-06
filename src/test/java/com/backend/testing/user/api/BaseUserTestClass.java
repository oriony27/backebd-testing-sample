package com.backend.testing.user.api;

import com.backend.testing.Application;
import com.backend.testing.dto.UserDto;
import com.backend.testing.service.database.user.UserData;
import com.backend.testing.service.dataproviders.user.api.UserDataProvider;
import com.backend.testing.utils.GsonUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class BaseUserTestClass {
    @Autowired
    @Qualifier("databaseUserDataProvider")
    protected UserDataProvider databaseProvider;

    @Autowired
    @Qualifier("restUserDataProvider")
    protected UserDataProvider restProvider;

    @Autowired
    protected UserData userData;

    @Before
    public void init() throws IOException {
        for (int i = 1; i < 10; i++) {
            UserDto userToCreate = new UserDto(i, "User" + i, 23);
            restProvider.createUser(userToCreate);
        }
    }

    @After
    public void tearDown() throws IOException {
        List<UserDto> createdUsers = restProvider.getAllUsers(userData.getNumberOfRecords(), 0);

        for (UserDto user : createdUsers) {
            restProvider.deleteUserById(user.getId());
        }
    }

}
