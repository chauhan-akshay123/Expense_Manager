package com.akshay.expense_tracker.repository;

import com.akshay.expense_tracker.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GroupRepository extends JpaRepository<Group, UUID> {
}
