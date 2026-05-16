package com.akshay.expense_tracker.service.impl;

import com.akshay.expense_tracker.dto.request.AddMemberRequest;
import com.akshay.expense_tracker.dto.request.CreateGroupRequest;
import com.akshay.expense_tracker.dto.response.GroupResponse;
import com.akshay.expense_tracker.entity.Group;
import com.akshay.expense_tracker.entity.GroupMember;
import com.akshay.expense_tracker.entity.User;
import com.akshay.expense_tracker.enums.GroupRole;
import com.akshay.expense_tracker.repository.GroupMemberRepository;
import com.akshay.expense_tracker.repository.GroupRepository;
import com.akshay.expense_tracker.repository.UserRepository;
import com.akshay.expense_tracker.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final UserRepository userRepository;

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

    @Override
    public GroupResponse addMember(UUID groupId, AddMemberRequest request) {
        User currentUser = (User)SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        boolean isAdmin = groupMemberRepository
                .existsByGroupIdAndUserIdAndRole(
                        groupId,
                        currentUser.getId(),
                        GroupRole.ADMIN
                );

        if(!isAdmin){
            throw new RuntimeException("Only admin can add members");
        }

        boolean alreadyMember = groupMemberRepository
                .existsByGroupIdAndUserId(
                        groupId,
                        request.getUserId()
                );

        if(alreadyMember){
            throw new RuntimeException(
                    "User already exists in group"
            );
        }

        User user = userRepository.findById(
                request.getUserId()
        ).orElseThrow(() -> new RuntimeException(
                "User not found"
        ));

        Group group = groupRepository.findById(
                groupId).orElseThrow(() -> new RuntimeException("Group not found"));

        GroupMember groupMember = new GroupMember();

        groupMember.setUser(user);
        groupMember.setGroup(group);
        groupMember.setRole(GroupRole.MEMBER);
        groupMemberRepository.save(groupMember);

        return GroupResponse.builder()
                .id(group.getId())
                .name(group.getName())
                .description(group.getDescription())
                .build();
    }

    @Override
    public List<GroupResponse> getUserGroups() {
        User currentUser = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return groupMemberRepository
                .findByUserId(currentUser.getId())
                .stream()
                .map(member -> {

                    Group group = member.getGroup();

                    return GroupResponse.builder()
                            .id(group.getId())
                            .name(group.getName())
                            .description(group.getDescription())
                            .build();
                })
                .toList();
    }
}
