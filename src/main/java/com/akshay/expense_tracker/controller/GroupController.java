package com.akshay.expense_tracker.controller;

import com.akshay.expense_tracker.dto.request.CreateGroupRequest;
import com.akshay.expense_tracker.dto.response.GroupResponse;
import com.akshay.expense_tracker.entity.Group;
import com.akshay.expense_tracker.service.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    public GroupResponse createGroup(
            @Valid
            @RequestBody
            CreateGroupRequest request
    ){
        return groupService.createGroup(request);
    }
}
