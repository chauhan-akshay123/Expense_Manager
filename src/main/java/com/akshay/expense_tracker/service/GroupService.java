package com.akshay.expense_tracker.service;

import com.akshay.expense_tracker.dto.request.CreateGroupRequest;
import com.akshay.expense_tracker.dto.response.GroupResponse;
import com.akshay.expense_tracker.entity.Group;

public interface GroupService {

    GroupResponse createGroup(CreateGroupRequest request);
}
