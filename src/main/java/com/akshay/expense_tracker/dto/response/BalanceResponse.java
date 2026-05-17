package com.akshay.expense_tracker.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class BalanceResponse {

    private String lender;
    private String borrower;
    private BigDecimal amount;
}
