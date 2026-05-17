package com.akshay.expense_tracker.repository;

import com.akshay.expense_tracker.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, UUID> {

    Optional<Balance>
    findByLenderIdAndBorrowerId(
            UUID lenderId,
            UUID borrowerId
    );

    List<Balance>
    findByBorrowerIdOrLenderId(
            UUID borrowerId,
            UUID lenderId
    );
}
