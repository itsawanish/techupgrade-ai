package com.techupgrade.ai.user.controller;

import com.techupgrade.ai.common.response.ApiResponse;
import com.techupgrade.ai.user.dto.UserCreateRequest;
import com.techupgrade.ai.user.dto.UserResponse;
import com.techupgrade.ai.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> createUser(
            @Valid @RequestBody UserCreateRequest request) {

        UserResponse response = userService.createUser(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("User create Successfully",response));
    }
}