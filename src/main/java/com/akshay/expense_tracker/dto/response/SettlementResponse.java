package com.akshay.expense_tracker.dto.response;

import lombok.Builder;
import lombok.Getter;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
public class SettlementResponse {

    private UUID settlementId;
    private String payer;
    private String receiver;
    private BigDecimal amount;
}
