package com.akshay.expense_tracker.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class ExpenseResponse {

    private UUID id;

    private String title;

    private BigDecimal amount;

    private String category;
}
