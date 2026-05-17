package com.akshay.expense_tracker.service.impl;

import com.akshay.expense_tracker.dto.response.BalanceResponse;
import com.akshay.expense_tracker.entity.Balance;
import com.akshay.expense_tracker.entity.User;
import com.akshay.expense_tracker.repository.BalanceRepository;
import com.akshay.expense_tracker.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BalanceServiceImpl implements BalanceService {

    private final BalanceRepository balanceRepository;

    @Override
    public List<BalanceResponse> getMyBalances() {
        User currentUser =
                (User) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();

        List<Balance> balances =
                balanceRepository
                        .findByBorrowerIdOrLenderId(
                                currentUser.getId(),
                                currentUser.getId()
                        );
        return balances.stream()
                .map(balance ->
                        BalanceResponse.builder()
                                .lender(
                                        balance.getLender()
                                                .getFullName()
                                )
                                .borrower(
                                        balance.getBorrower()
                                                .getFullName()
                                )
                                .amount(
                                        balance.getAmount()
                                )
                                .build()
                 )
                .toList();
    }
}
