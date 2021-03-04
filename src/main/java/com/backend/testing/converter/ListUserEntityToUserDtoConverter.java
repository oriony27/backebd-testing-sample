package com.backend.testing.converter;

import com.backend.testing.dto.UserDto;
import com.backend.testing.entity.User;
import com.google.common.collect.Lists;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("entityListToDtoConverter")
public class ListUserEntityToUserDtoConverter implements Converter<List<User>, List<UserDto>> {
    private Converter<User, UserDto> entityToDtoConverter;

    @Autowired
    public ListUserEntityToUserDtoConverter(@Qualifier("entityToDtoConverter") Converter<User, UserDto> entityToDtoConverter) {
        this.entityToDtoConverter = entityToDtoConverter;
    }

    @Override
    public List<UserDto> convert(List<User> source) {
        List<UserDto> result = Lists.newArrayList();
        for (User user : source) {
            result.add(entityToDtoConverter.convert(user));
        }
        return result;
    }
}
