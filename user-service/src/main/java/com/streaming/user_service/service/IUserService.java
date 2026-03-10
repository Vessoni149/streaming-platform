package com.streaming.user_service.service;

import com.streaming.user_service.dto.user.UpdateUserRequestDto;
import com.streaming.user_service.dto.user.UserRequestDto;
import com.streaming.user_service.dto.user.UserResponseDto;

import java.util.List;

public interface IUserService {
    public List<UserResponseDto> getUsers();
    public UserResponseDto getUser(Long id);
    public UserResponseDto createUser(UserRequestDto user);
    public void deleteUser(Long id);
    public UserResponseDto updateUser(Long id, UpdateUserRequestDto userDto);

}
