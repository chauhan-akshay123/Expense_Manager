package com.akshay.expense_tracker.service;

import com.akshay.expense_tracker.dto.response.BalanceResponse;

import java.util.List;

public interface BalanceService {
    List<BalanceResponse> getMyBalances();
}
