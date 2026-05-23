package com.example.jose.controller;

import com.example.jose.dto.ApiResponse;
import com.example.jose.dto.UserResponse;
import com.example.jose.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController (UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> savePersonApiResponse(@RequestBody @Valid UserResponse userResponse){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveApiResponse(userResponse));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(userService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> updateByIdApiResponse(@PathVariable Long id, @RequestBody UserResponse userResponse){
        return ResponseEntity.status(HttpStatus.FOUND).body(userService.updatedById(id,userResponse));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<ApiResponse<String>> deleteById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.delete(id));
    }
}
