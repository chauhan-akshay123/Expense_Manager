package com.akshay.expense_tracker.service;

import com.akshay.expense_tracker.dto.request.CreateExpenseRequest;
import com.akshay.expense_tracker.dto.response.ExpenseResponse;

public interface ExpenseService {

    ExpenseResponse createExpense(
            CreateExpenseRequest request
    );
}
