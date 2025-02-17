package com.example.user_service.model;

import com.example.shared_library.model.BasicModel;
import com.example.user_service.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "phone")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BasicModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(length = 50, nullable = false)
    private String username;
    @Column(length = 150, nullable = false)
    private String firstname;
    @Column(length = 150, nullable = false)
    private String lastname;
    @Column(nullable = false)
    private LocalDate dateOfBirth;
    @Column(length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(length = 150, nullable = false)
    private String email;
    @Column(length = 10, nullable = false)
    private String phone;
    @Column(length = 250)
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}