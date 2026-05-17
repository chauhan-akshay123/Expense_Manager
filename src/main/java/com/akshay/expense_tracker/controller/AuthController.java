package com.akshay.expense_tracker.controller;

import com.akshay.expense_tracker.dto.request.LoginRequest;
import com.akshay.expense_tracker.dto.request.RegisterRequest;
import com.akshay.expense_tracker.dto.response.ApiResponse;
import com.akshay.expense_tracker.dto.response.AuthResponse;
import com.akshay.expense_tracker.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ApiResponse<AuthResponse> register(
            @Valid @RequestBody RegisterRequest request
            ){

        return ApiResponse
                .<AuthResponse>builder()
                .success(true)
                .message("User registered successfully")
                .data(
                        authService.register(request)
                )
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(
            @Valid @RequestBody LoginRequest request
            ){
         return ApiResponse
                 .<AuthResponse>builder()
                 .success(true)
                 .message("Logged in successfully")
                 .data(
                         authService.login(request)
                 )
                 .build();
    }
}
