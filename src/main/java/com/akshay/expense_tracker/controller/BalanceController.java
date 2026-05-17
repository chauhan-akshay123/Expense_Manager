package com.akshay.expense_tracker.controller;

import com.akshay.expense_tracker.dto.response.ApiResponse;
import com.akshay.expense_tracker.dto.response.BalanceResponse;
import com.akshay.expense_tracker.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/balances")
@RequiredArgsConstructor
public class BalanceController {

    private final BalanceService balanceService;

    @GetMapping
    public ApiResponse<List<BalanceResponse>>
    getBalances() {
        return ApiResponse
                .<List<BalanceResponse>>builder()
                .success(true)
                .message("Balances fetched successfully")
                .data(
                        balanceService.getMyBalances()
                )
                .build();
    }
}
