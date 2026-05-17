package com.akshay.expense_tracker.service.impl;

import com.akshay.expense_tracker.dto.request.CreateExpenseRequest;
import com.akshay.expense_tracker.dto.response.ExpenseResponse;
import com.akshay.expense_tracker.entity.*;
import com.akshay.expense_tracker.enums.SplitType;
import com.akshay.expense_tracker.exception.BadRequestException;
import com.akshay.expense_tracker.exception.ResourceNotFoundException;
import com.akshay.expense_tracker.repository.*;
import com.akshay.expense_tracker.service.ExpenseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final ExpenseSplitRepository expenseSplitRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ExpenseResponse createExpense(CreateExpenseRequest request) {

        User currentUser = (User) SecurityContextHolder
                                   .getContext()
                                   .getAuthentication()
                                   .getPrincipal();

        Group group = groupRepository.findById(
                request.getGroupId()
        ).orElseThrow(() -> new ResourceNotFoundException("group not found."));

        boolean isMember = groupMemberRepository
                .existsByGroupIdAndUserId(
                        group.getId(),
                        currentUser.getId()
                );

        if(!isMember){
            throw new BadRequestException("You are not member of this group");
        }

        List<GroupMember> groupMembers =
                groupMemberRepository.findByGroupId(
                        group.getId()
                );

        List<UUID> validUserIds =
                  groupMembers.stream()
                          .map(member ->
                                  member.getUser().getId()
                          )
                          .toList();

        for(UUID participantId :
                    request.getParticipantIds()) {
            if(!validUserIds.contains(participantId)) {
                throw new BadRequestException(
                        "Participant not in group"
                );
            }
        }

        Expense expense = new Expense();

        expense.setTitle(request.getTitle());
        expense.setDescription(
                request.getDescription()
        );
        expense.setAmount(request.getAmount());
        expense.setCategory(request.getCategory());
        expense.setSplitType(SplitType.EQUAL);
        expense.setGroup(group);
        expense.setPaidBy(currentUser);

        Expense savedExpense = expenseRepository.save(expense);

        BigDecimal splitAmount =
                request.getAmount()
                        .divide(
                                BigDecimal.valueOf(
                                        request
                                                .getParticipantIds()
                                                .size()
                                ),
                                2,
                                RoundingMode.HALF_UP
                        );
        for(UUID participantId :
                request.getParticipantIds()) {
            User participant =
                     userRepository.findById(
                             participantId
                     ).orElseThrow(() ->
                             new ResourceNotFoundException(
                                     "User not found"
                             )
                     );

            ExpenseSplit split = new ExpenseSplit();

            split.setExpense(savedExpense);
            split.setUser(participant);
            split.setAmount(splitAmount);
            expenseSplitRepository.save(split);
        }
        return ExpenseResponse.builder()
                .id(savedExpense.getId())
                .title(savedExpense.getTitle())
                .amount(savedExpense.getAmount())
                .category(
                        savedExpense.getCategory()
                                .name()
                )
                .build();
    }
}
