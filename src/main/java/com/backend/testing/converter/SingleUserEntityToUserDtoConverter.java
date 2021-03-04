package com.backend.testing.converter;

import com.backend.testing.dto.UserDto;
import com.backend.testing.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service("entityToDtoConverter")
public class SingleUserEntityToUserDtoConverter implements Converter<User, UserDto> {

    @Override
    public UserDto convert(User source) {
        return new UserDto(source.getId(), source.getName(), source.getAge());
    }
}
