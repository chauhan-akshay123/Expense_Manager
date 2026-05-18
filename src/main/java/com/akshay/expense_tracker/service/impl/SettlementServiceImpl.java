package com.akshay.expense_tracker.service.impl;

import com.akshay.expense_tracker.dto.request.SettleBalanceRequest;
import com.akshay.expense_tracker.dto.response.SettlementResponse;
import com.akshay.expense_tracker.entity.Balance;
import com.akshay.expense_tracker.entity.Settlement;
import com.akshay.expense_tracker.entity.User;
import com.akshay.expense_tracker.exception.BadRequestException;
import com.akshay.expense_tracker.exception.ResourceNotFoundException;
import com.akshay.expense_tracker.repository.BalanceRepository;
import com.akshay.expense_tracker.repository.SettlementRepository;
import com.akshay.expense_tracker.service.SettlementService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class SettlementServiceImpl implements SettlementService {

    private final BalanceRepository balanceRepository;
    private final SettlementRepository settlementRepository;

    @Override
    public SettlementResponse settleBalance(SettleBalanceRequest request) {
        User currentUser = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        Balance balance = balanceRepository.findById(
                            request.getBalanceId()
        ).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Balance not found"
                )
                );

        // only borrower can settle
        if(!balance.getBorrower().getId().equals(currentUser.getId())) {
            throw new BadRequestException(
                    "Only borrower can settle"
            );
        }

        // cannot settle more than owed
        if(request.getAmount()
                .compareTo(balance.getAmount()) > 0) {
            throw new BadRequestException(
                    "Settlement exceeds balance"
            );
        }

        Settlement settlement = new Settlement();

        settlement.setPayer(currentUser);
        settlement.setReceiver(
                balance.getLender()
        );

        settlement.setAmount(
                request.getAmount()
        );

        Settlement savedSettlement =
                settlementRepository.save(settlement);

        BigDecimal remaining =
                   balance.getAmount().subtract(
                           request.getAmount()
                   );

        // fully settled
        if(remaining.compareTo(
                BigDecimal.ZERO
        ) == 0){
            balanceRepository.delete(balance);
        } else {
            balance.setAmount(remaining);
            balanceRepository.save(balance);
        }

        return SettlementResponse.builder()
                .settlementId(savedSettlement.getId())
                .payer(savedSettlement.getPayer()
                        .getFullName()
                 )
                .receiver(
                        savedSettlement.getReceiver()
                                .getFullName()
                )
                .amount(
                        savedSettlement.getAmount()
                )
                .build();
    }
}
