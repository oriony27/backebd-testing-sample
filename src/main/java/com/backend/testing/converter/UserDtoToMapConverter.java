package com.backend.testing.converter;

import com.backend.testing.dto.UserDto;
import com.backend.testing.utils.GsonUtils;
import com.google.gson.reflect.TypeToken;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("userDtoToMapConverter")
public class UserDtoToMapConverter implements Converter<UserDto, Map<String, String>> {

    @Override
    public Map<String, String> convert(UserDto source) {
        String json = GsonUtils.toJson(source);
        return GsonUtils.fromJson(json, new TypeToken<Map<String, String>>() {}.getType());
    }
}
