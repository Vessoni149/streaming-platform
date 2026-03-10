package com.streaming.user_service.controller;

import com.streaming.user_service.dto.profile.ProfileRequestDto;
import com.streaming.user_service.dto.profile.ProfileResponseDto;
import com.streaming.user_service.dto.profile.UpdateProfileRequestDto;
import com.streaming.user_service.responses.ApiResponse;
import com.streaming.user_service.service.IProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
    @Autowired
    private IProfileService profileService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProfileResponseDto>>> getProfiles() {
        List<ProfileResponseDto> profiles = profileService.getProfiles();
        return ResponseEntity.ok(ApiResponse.success(profiles, "Profiles retrieved successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProfileResponseDto>> getProfile(@PathVariable Long id) {
        // El Service lanzará ResourceNotFoundException si no existe,
        // nuestro GlobalExceptionHandler se encarga del resto.
        ProfileResponseDto profile = profileService.getProfile(id);
        return ResponseEntity.ok(ApiResponse.success(profile, "Profile retrieved successfully"));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<ApiResponse<ProfileResponseDto>> createProfile(
            @PathVariable Long userId,
            @Valid @RequestBody ProfileRequestDto profileRequestDto) {
        ProfileResponseDto createdProfile = profileService.createProfile(userId,profileRequestDto);
        return new ResponseEntity<>(
                ApiResponse.success(createdProfile, "Profile created successfully"),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Profile deleted successfully"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<ProfileResponseDto>> updateProfile(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProfileRequestDto request) {

        ProfileResponseDto updatedProfile = profileService.editProfile(id, request);
        return ResponseEntity.ok(ApiResponse.success(updatedProfile, "Profile updated successfully"));
    }
    
}   
