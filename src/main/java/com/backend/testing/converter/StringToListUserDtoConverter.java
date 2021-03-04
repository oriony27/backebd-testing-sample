package com.backend.testing.converter;

import com.backend.testing.dto.UserDto;
import com.backend.testing.utils.GsonUtils;
import com.google.gson.reflect.TypeToken;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("jsonToListUserDtoConverter")
public class StringToListUserDtoConverter implements Converter<String, List<UserDto>> {
    @Override
    public List<UserDto> convert(String source) {
        return GsonUtils.fromJson(source, new TypeToken<List<UserDto>>() {}.getType());
    }
}
