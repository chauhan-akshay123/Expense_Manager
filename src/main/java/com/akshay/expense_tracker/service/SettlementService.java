package com.akshay.expense_tracker.service;

import com.akshay.expense_tracker.dto.request.SettleBalanceRequest;
import com.akshay.expense_tracker.dto.response.SettlementResponse;

public interface SettlementService {
    SettlementResponse settleBalance(
            SettleBalanceRequest request
    );
}
