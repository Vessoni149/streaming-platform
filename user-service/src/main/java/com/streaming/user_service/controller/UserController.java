package com.streaming.user_service.controller;

import com.streaming.user_service.dto.user.UpdateUserRequestDto;
import com.streaming.user_service.dto.user.UserRequestDto;
import com.streaming.user_service.dto.user.UserResponseDto;
import com.streaming.user_service.responses.ApiResponse;
import com.streaming.user_service.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
    @RestController
    @RequestMapping("/users")
    public class UserController {

        private final IUserService userService;

        public UserController(IUserService userService) {
            this.userService = userService;
        }

        @GetMapping
        public ResponseEntity<ApiResponse<List<UserResponseDto>>> getUsers() {
            List<UserResponseDto> users = userService.getUsers();
            return ResponseEntity.ok(ApiResponse.success(users, "Users retrieved successfully"));
        }

        @GetMapping("/{id}")
        public ResponseEntity<ApiResponse<UserResponseDto>> getUser(@PathVariable Long id) {
            // Si el usuario no existe, el Service lanzará ResourceNotFoundException
            // y nuestro GlobalExceptionHandler se encargará de todo.
            UserResponseDto user = userService.getUser(id);
            return ResponseEntity.ok(ApiResponse.success(user, "User retrieved successfully"));
        }

        @PostMapping
        public ResponseEntity<ApiResponse<UserResponseDto>> createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
            UserResponseDto createdUser = userService.createUser(userRequestDto);
            return new ResponseEntity<>(
                    ApiResponse.success(createdUser, "User created successfully"),
                    HttpStatus.CREATED
            );
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
            userService.deleteUser(id);
            // Usamos success(null, ...) porque el cuerpo de respuesta de un delete suele estar vacío
            return ResponseEntity.ok(ApiResponse.success(null, "User deleted successfully"));
        }

        @PatchMapping("/{id}")
        public ResponseEntity<ApiResponse<UserResponseDto>> updateUser(
                @PathVariable Long id,
                @Valid @RequestBody UpdateUserRequestDto request) {

            UserResponseDto updatedUser = userService.updateUser(id, request);
            return ResponseEntity.ok(ApiResponse.success(updatedUser, "User updated successfully"));
        }
    }


