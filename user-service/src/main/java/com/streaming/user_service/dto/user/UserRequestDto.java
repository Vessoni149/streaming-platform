package com.streaming.user_service.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import jakarta.validation.constraints.Email;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    @Email(message = "The email address must be valid and contain an '@' symbol.")
    @NotBlank(message = "'email' cannot be blank.")
    private String email;
    @NotBlank(message = "'firstName' cannot be blank.")
    @Size(min = 2, max = 20, message = "The name must be between 2 and 20 characters.")
    @Pattern(
            regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$",
            message = "The first name can only contain letters and spaces. Numbers and special characters are not allowed."
    )
    private String firstName;

    @NotBlank(message = "'lastName' cannot be blank.")
    @Size(min = 2, max = 30, message = "The name must be between 2 and 30 characters.")
    @Pattern(
            regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$",
            message = "The last name can only contain letters and spaces. Numbers and special characters are not allowed."
    )
    private String lastName;
    @NotBlank(message = "'password' cannot be blank.")
    @Pattern(
            regexp = "^[a-zA-Z0-9\\-_|°¿?%]+$",
            message = "Only letters, numbers, hyphens (-), underscores (_), pipes (|), degree signs (°), question marks (¿?), and percent signs (%) are allowed in the field 'password'."
    )
    @Size(min=8, message = "Password field must contain at least 8 characters.")
    private String password;
}
