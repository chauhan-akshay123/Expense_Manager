package com.akshay.expense_tracker.controller;

import com.akshay.expense_tracker.dto.request.CreateExpenseRequest;
import com.akshay.expense_tracker.dto.response.ApiResponse;
import com.akshay.expense_tracker.dto.response.ExpenseResponse;
import com.akshay.expense_tracker.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public ApiResponse<ExpenseResponse>
    createExpense(
            @Valid
            @RequestBody
            CreateExpenseRequest request
    ) {
        return ApiResponse
                .<ExpenseResponse>builder()
                .success(true)
                .message("Expense create successfully")
                .data(
                        expenseService.createExpense(request)
                )
                .build();
    }
}
