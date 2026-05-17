package com.akshay.expense_tracker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "expense_splits")
public class ExpenseSplit extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Expense expense;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    @Column(nullable = false)
    private BigDecimal amount;

}
