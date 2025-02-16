package com.example.user_service.dto.user_dto;

import com.example.user_service.enums.Gender;
import com.example.user_service.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String email;
    private String phone;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private boolean deleted;
    private Role role;

    public String getDeleted() {
        return deleted ? "Deleted" : "Active";
    }

    public String getRole() {
        return role.getName();
    }
}
