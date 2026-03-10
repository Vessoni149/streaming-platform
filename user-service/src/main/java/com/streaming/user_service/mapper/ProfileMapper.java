package com.streaming.user_service.mapper;

import com.streaming.user_service.dto.profile.ProfileRequestDto;
import com.streaming.user_service.dto.profile.ProfileResponseDto;
import com.streaming.user_service.model.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfileMapper {


    ProfileResponseDto toDto(Profile profile);

    Profile toEntity(ProfileRequestDto profileRequestDto);
    List<ProfileResponseDto> toDtoList(List<Profile> profiles);

}
