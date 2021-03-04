package com.backend.testing.converter;

import com.backend.testing.dto.UserDto;
import com.backend.testing.utils.GsonUtils;
import com.google.gson.reflect.TypeToken;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("jsonToUserDtoConverter")
public class StringToUserDtoConverter implements Converter<String, UserDto> {
    @Override
    public UserDto convert(String source) {
        return GsonUtils.fromJson(source, new TypeToken<UserDto>() {}.getType());
    }
}
