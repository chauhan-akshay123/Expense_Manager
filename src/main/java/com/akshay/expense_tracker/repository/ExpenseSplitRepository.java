package com.akshay.expense_tracker.repository;

import com.akshay.expense_tracker.entity.ExpenseSplit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExpenseSplitRepository extends JpaRepository<ExpenseSplit, UUID> {
}
