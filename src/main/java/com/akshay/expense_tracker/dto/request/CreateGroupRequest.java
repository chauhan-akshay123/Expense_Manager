package com.akshay.expense_tracker.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateGroupRequest {

    @NotBlank
    private String name;

    private String description;
}
