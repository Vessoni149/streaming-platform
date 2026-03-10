package com.streaming.user_service.service;

import com.streaming.user_service.dto.profile.ProfileRequestDto;
import com.streaming.user_service.dto.profile.ProfileResponseDto;
import com.streaming.user_service.dto.profile.UpdateProfileRequestDto;
import com.streaming.user_service.exceptions.ResourceNotFoundException;
import com.streaming.user_service.mapper.ProfileMapper;
import com.streaming.user_service.model.Profile;
import com.streaming.user_service.model.User;
import com.streaming.user_service.repository.IProfileRepo;
import com.streaming.user_service.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService implements IProfileService {

    @Autowired
    private ProfileMapper profileMapper;
    @Autowired
    private IProfileRepo profileRepo;
    @Autowired
    private IUserRepo userRepo;

    @Override
    public List<ProfileResponseDto> getProfiles() {
        List<Profile> profileList = profileRepo.findAll();
        return profileMapper.toDtoList(profileList);
    }

    @Override
    public ProfileResponseDto getProfile(Long id) {
        Profile profile = profileRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id, "Profile")
        );
        return profileMapper.toDto(profile);
    }

    @Override
    public ProfileResponseDto createProfile(Long userId, ProfileRequestDto profileRequestDto) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(userId, "User"));
        Profile profile = profileMapper.toEntity(profileRequestDto);
        profile.setUser(user);
        user.addProfile(profile);
        Profile profileSaved = profileRepo.save(profile);
        return profileMapper.toDto(profileSaved);
    }

    @Override
    public void deleteProfile(Long id) {
        if(!profileRepo.existsById(id)){
            throw new ResourceNotFoundException(id, "Profile");
        }
        profileRepo.deleteById(id);
    }


    @Override
    public ProfileResponseDto editProfile(Long id, UpdateProfileRequestDto request) {

        Profile existingProfile = profileRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, "Profile"));

        if (request.getName() != null) {
            existingProfile.setName(request.getName());
        }

        if (request.getAvatarUrl() != null) {
            /*existingProfile.setPassword(passwordEncoder.encode(request.getPassword()));*/
            existingProfile.setAvatarUrl(request.getAvatarUrl());
        }
        if (request.getIsChild() != null) {
            existingProfile.setIsChild(request.getIsChild());
        }

        Profile updated = profileRepo.save(existingProfile);

        return profileMapper.toDto(updated);
    }

}
