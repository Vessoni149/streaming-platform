package com.streaming.user_service.dto.profile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRequestDto {

    @NotBlank(message = "Profile name is required.")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters.")
    @Pattern(
            regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$",
            message = "The first name can only contain letters and spaces. Numbers and special characters are not allowed."
    )
    private String name;
    @NotBlank(message = "Avatar URL is required.")
    @URL(message = "Must be a valid URL (e.g., http://s3.amazom.com/avatar1.png).")
    private String avatarUrl;
    @NotNull(message = "You must specify if the profile is for a child.")
    private Boolean isChild;
}
