package com.example.user_service.mapper;

import com.example.user_service.dto.user_dto.UserDTO;
import com.example.user_service.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDTO(User user);
}
