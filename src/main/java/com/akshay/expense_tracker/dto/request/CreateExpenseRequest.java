package com.akshay.expense_tracker.dto.request;

import com.akshay.expense_tracker.enums.ExpenseCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateExpenseRequest {

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private ExpenseCategory category;

    @NotNull
    private UUID groupId;

    @NotNull
    private List<UUID> participantIDs;
}
