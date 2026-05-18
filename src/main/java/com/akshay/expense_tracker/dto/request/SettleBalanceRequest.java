package com.akshay.expense_tracker.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class SettleBalanceRequest {

    @NotNull
    private UUID balanceId;

    @NotNull
    private BigDecimal amount;
}
