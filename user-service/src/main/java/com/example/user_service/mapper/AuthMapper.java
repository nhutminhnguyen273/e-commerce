package com.example.user_service.mapper;

import com.example.user_service.dto.auth_dto.RegistrationDTO;
import com.example.user_service.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    User toUserEntity(RegistrationDTO input);
}