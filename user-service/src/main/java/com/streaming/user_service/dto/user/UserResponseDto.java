package com.streaming.user_service.dto.user;

import com.streaming.user_service.dto.profile.ProfileResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Long id;

    private String email;
    private String firstName;
    private String lastName;

    private String subscriptionStatus;
    private LocalDateTime createdAt;
    private Boolean enabled;
    private List<ProfileResponseDto> profileList;
}
