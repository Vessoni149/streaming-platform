package com.streaming.user_service.service;

import com.streaming.user_service.dto.user.UpdateUserRequestDto;
import com.streaming.user_service.dto.user.UserRequestDto;
import com.streaming.user_service.dto.user.UserResponseDto;
import com.streaming.user_service.exceptions.ResourceNotFoundException;
import com.streaming.user_service.mapper.UserMapper;
import com.streaming.user_service.model.SubscriptionStatus;
import com.streaming.user_service.model.User;
import com.streaming.user_service.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{
    @Autowired
    private IUserRepo userRepo;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserResponseDto> getUsers() {
        List<User> users = userRepo.findAll();
        return userMapper.toDtoList(users);
    }

    @Override
    public UserResponseDto getUser(Long id) {

        return userMapper.toDto(userRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id,"User"))
        );
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user = userMapper.toEntity(userRequestDto);
        user.setSubscriptionStatus(SubscriptionStatus.INACTIVE);
        //Creamos un User para guardar la entidad YA PERSISTIDA en la DB para que contenga el ID.
        User savedUser = userRepo.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepo.existsById(id)) {
            throw new ResourceNotFoundException(id, "User");
        }
        userRepo.deleteById(id);
    }

    @Override
    public UserResponseDto updateUser(Long id, UpdateUserRequestDto request) {

        User existingUser = userRepo.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(id,"User")
                );

        if (request.getFirstName() != null) {
            existingUser.setFirstName(request.getFirstName());
        }

        if (request.getLastName() != null) {
            existingUser.setLastName(request.getLastName());
        }
        if (request.getEmail() != null) {
            existingUser.setEmail(request.getEmail());
        }

        if (request.getPassword() != null) {
            /*existingUser.setPassword(passwordEncoder.encode(request.getPassword()));*/
            existingUser.setPassword(request.getPassword());
        }

        User updated = userRepo.save(existingUser);

        return userMapper.toDto(updated);
    }
}
