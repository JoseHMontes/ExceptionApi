package com.example.jose.service;

import com.example.jose.dto.ApiResponse;
import com.example.jose.dto.UserResponse;
import com.example.jose.exceptions.UserNotFound;
import com.example.jose.mapper.UserMapper;
import com.example.jose.model.User;
import com.example.jose.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserService(UserMapper userMapper, UserRepository userRepository){
        this.userRepository = userRepository;
        this.userMapper=userMapper;
    }

    public ApiResponse<UserResponse> saveApiResponse(UserResponse userResponse){
        User user = userMapper.userDtoToUser(userResponse);
        User savePerson = userRepository.save(user);
        return ApiResponse.<UserResponse>builder()
                .status(HttpStatus.CREATED)
                .body(userMapper.userToDto(savePerson))
                .message("User Created well!")
                .build();
    }

    public ApiResponse<UserResponse> updatedById(Long id, UserResponse userResponse){
        User searchUser = userRepository.findById(id)
                .orElseThrow(()->new UserNotFound("Not found"));
        userMapper.update(userResponse, searchUser);
        return ApiResponse.<UserResponse>builder()
                .status(HttpStatus.OK)
                .body(userMapper.userToDto(userRepository.save(searchUser)))
                .message("User found successfully")
                .build();
    }

    public ApiResponse<List<UserResponse>> getAll(){
        return ApiResponse.<List<UserResponse>>builder()
                .status(HttpStatus.OK)
                .body(userRepository.findAll().stream().map(userMapper::userToDto).toList())
                .message("Users Found!")
                .build();
    }

    public ApiResponse<UserResponse> getById(Long id){
        User searchUser = userRepository.findById(id)
                .orElseThrow(()-> new UserNotFound("User not found"));
        return ApiResponse.<UserResponse>builder()
                .status(HttpStatus.OK)
                .body(userMapper.userToDto(searchUser))
                .message("User found with id = " + id)
                .build();
    }

    public  ApiResponse<String> delete (Long id){
        User userSearch = userRepository.findById(id)
                .orElseThrow(()->new UserNotFound("User not found"));
        userRepository.deleteById(id);
        return ApiResponse.<String>builder()
                .status(HttpStatus.OK)
                .body("User - " + userSearch.getName() + ". Was deleted!")
                .message("Deleted!")
                .build();
    }
}
