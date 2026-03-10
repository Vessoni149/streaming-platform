package com.streaming.user_service.service;

import com.streaming.user_service.dto.profile.ProfileRequestDto;
import com.streaming.user_service.dto.profile.ProfileResponseDto;
import com.streaming.user_service.dto.profile.UpdateProfileRequestDto;

import java.util.List;

public interface IProfileService {
    public List<ProfileResponseDto> getProfiles();
    public ProfileResponseDto getProfile(Long id);
    public ProfileResponseDto createProfile(Long id,ProfileRequestDto profileRequestDto);
    public void deleteProfile(Long id);
    public ProfileResponseDto editProfile(Long id, UpdateProfileRequestDto profile);
}
