package com.akshay.expense_tracker.repository;

import com.akshay.expense_tracker.entity.GroupMember;
import com.akshay.expense_tracker.enums.GroupRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GroupMemberRepository extends JpaRepository<GroupMember, UUID> {
    List<GroupMember> findByUserId(UUID userId);

    boolean existsByGroupIdAndUserId(
            UUID groupId,
            UUID userId0
    );

    boolean existsByGroupIdAndUserIdAndRole(
            UUID groupId,
            UUID userId,
            GroupRole role
    );
}
