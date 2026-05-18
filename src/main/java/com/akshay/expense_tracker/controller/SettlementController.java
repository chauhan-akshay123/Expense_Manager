package com.akshay.expense_tracker.controller;

import com.akshay.expense_tracker.dto.request.SettleBalanceRequest;
import com.akshay.expense_tracker.dto.response.ApiResponse;
import com.akshay.expense_tracker.dto.response.SettlementResponse;
import com.akshay.expense_tracker.service.SettlementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/settlements")
@RequiredArgsConstructor
public class SettlementController {

    private final SettlementService settlementService;

    @PostMapping
    public ApiResponse<SettlementResponse>
    settleBalance(
            @Valid
            @RequestBody SettleBalanceRequest request
            ) {

        return ApiResponse
                .<SettlementResponse>builder()
                .success(true)
                .message("Balance settled successfully")
                .data(
                        settlementService.settleBalance(request)
                )
                .build();
    }
}
