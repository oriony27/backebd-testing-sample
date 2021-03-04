package com.backend.testing.service.dataproviders.user.impl;

import com.backend.testing.dto.UserDto;
import com.backend.testing.entity.User;
import com.backend.testing.repository.UserRepository;
import com.backend.testing.service.dataproviders.user.api.UserDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service("databaseUserDataProvider")
public class DatabaseUserDataProvider implements UserDataProvider {
    private UserRepository repository;
    private Converter<User, UserDto> entityToDtoConverter;
    private Converter<List<User>, List<UserDto>> entityListToDtoConverter;

    @Autowired
    public DatabaseUserDataProvider(UserRepository repository,
                                    @Qualifier("entityToDtoConverter") Converter<User, UserDto> entityToDtoConverter,
                                    @Qualifier("entityListToDtoConverter") Converter<List<User>, List<UserDto>> entityListToDtoConverter
    ) {
        this.repository = repository;
        this.entityListToDtoConverter = entityListToDtoConverter;
        this.entityToDtoConverter = entityToDtoConverter;
    }

    @Override
    public List<UserDto> getAllUsers(int limit, int offset) {
        List<User> result = repository.findAll().stream().skip(offset).limit(limit).collect(Collectors.toList());
        return entityListToDtoConverter.convert(result);
    }

    @Override
    public UserDto getUserById(long userId) {
        User result = repository.findById(userId).orElse(new User());
        return entityToDtoConverter.convert(result);
    }

    @Override
    public UserDto createUser(UserDto user) {
        return getUserById(user.getId());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        return getUserById(user.getId());
    }

    @Override
    public String deleteUserById(long userId) {
        return getUserById(userId).getId() == 0 ? "Deleted" : "Not deleted!";
    }

    @Override
    public String createUserNegative(UserDto user) {
        return Objects.nonNull(getUserById(user.getId())) ? "User already exists!" : "FAILED!";
    }

    @Override
    public String deleteUserNegative(long userId) {
        return getUserById(userId).getId() == 0 ? "This user doesn't exists!" : "FAILED!";
    }
}
