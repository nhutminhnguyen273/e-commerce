package com.example.user_service.mapper;

import com.example.user_service.dto.auth_dto.RegistrationDTO;
import com.example.user_service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthMapper {
//    @Mapping(target = "confirmPassword", ignore = true)
    User toUserEntity(RegistrationDTO input);
}