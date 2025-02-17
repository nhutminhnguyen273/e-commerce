package com.example.user_service.dto.user_dto;

import com.example.user_service.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDTO {
    @NotNull(message = "Username is required")
    @Size(min = 5, max = 50, message = "Username must be between 5 and 50 characters")
    private String username;
    @NotNull(message = "Firstname is required")
    private String firstname;
    @NotNull(message = "Lastname is required")
    private String lastname;
    @NotNull(message = "Birthday is required")
    private LocalDate dateOfBirth;
    @NotNull(message = "Gender is required")
    private Gender gender;
    @NotNull(message = "Email is required")
    @Email(message = "Incorrect email address")
    private String email;
    @NotNull(message = "Phone number is required")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phone;
}