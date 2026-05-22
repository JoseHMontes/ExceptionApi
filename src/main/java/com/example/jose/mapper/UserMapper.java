package com.example.jose.mapper;

import com.example.jose.dto.UserResponse;
import com.example.jose.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse userToDto(User user);
    User userDtoToUser(UserResponse userResponse);
    void update(UserResponse userResponse, @MappingTarget User user);
}
