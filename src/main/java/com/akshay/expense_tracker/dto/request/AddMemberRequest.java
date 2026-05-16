package com.akshay.expense_tracker.dto.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AddMemberRequest {

    @NonNull
    private UUID userId;
}
