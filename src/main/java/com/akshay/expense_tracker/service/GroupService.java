package com.akshay.expense_tracker.service;

import com.akshay.expense_tracker.dto.request.AddMemberRequest;
import com.akshay.expense_tracker.dto.request.CreateGroupRequest;
import com.akshay.expense_tracker.dto.response.GroupResponse;
import com.akshay.expense_tracker.entity.Group;

import java.util.List;
import java.util.UUID;

public interface GroupService {

    GroupResponse createGroup(CreateGroupRequest request);

    GroupResponse addMember(
            UUID groupId,
            AddMemberRequest request
    );

    List<GroupResponse> getUserGroups();
}
