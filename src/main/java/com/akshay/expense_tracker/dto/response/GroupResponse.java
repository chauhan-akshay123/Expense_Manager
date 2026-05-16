package com.akshay.expense_tracker.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class GroupResponse {

    private UUID id;
    private String name;
    private String description;
}
