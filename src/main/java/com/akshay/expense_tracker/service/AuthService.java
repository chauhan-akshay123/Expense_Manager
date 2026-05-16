package com.akshay.expense_tracker.service;

import com.akshay.expense_tracker.dto.request.LoginRequest;
import com.akshay.expense_tracker.dto.request.RegisterRequest;
import com.akshay.expense_tracker.dto.response.AuthResponse;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
