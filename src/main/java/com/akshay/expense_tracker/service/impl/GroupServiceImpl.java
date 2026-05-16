package com.akshay.expense_tracker.service.impl;

import com.akshay.expense_tracker.dto.request.CreateGroupRequest;
import com.akshay.expense_tracker.dto.response.GroupResponse;
import com.akshay.expense_tracker.entity.Group;
import com.akshay.expense_tracker.entity.GroupMember;
import com.akshay.expense_tracker.entity.User;
import com.akshay.expense_tracker.enums.GroupRole;
import com.akshay.expense_tracker.repository.GroupMemberRepository;
import com.akshay.expense_tracker.repository.GroupRepository;
import com.akshay.expense_tracker.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;

    @Override
    public GroupResponse createGroup(CreateGroupRequest request) {
        User currentUser = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        Group group = new Group();

        group.setName(request.getName());
        group.setDescription(request.getDescription());

        Group savedGroup = groupRepository.save(group);

        GroupMember groupMember = new GroupMember();

        groupMember.setUser(currentUser);
        groupMember.setGroup(savedGroup);
        groupMember.setRole(GroupRole.ADMIN);
        groupMemberRepository.save(groupMember);

        return GroupResponse.builder()
                .id(savedGroup.getId())
                .name(savedGroup.getName())
                .description(savedGroup.getDescription())
                .build();
        }
}
