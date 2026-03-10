package com.streaming.user_service.mapper;

import com.streaming.user_service.dto.user.UserRequestDto;
import com.streaming.user_service.dto.user.UserResponseDto;
import com.streaming.user_service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProfileMapper.class})
public interface UserMapper {
    @Mapping(source = "profiles", target = "profileList")
    UserResponseDto toDto(User user);

    @Mapping(target = "profiles", ignore = true)
    User toEntity(UserRequestDto userRequestDto);


    List<UserResponseDto> toDtoList(List<User> users);
}
