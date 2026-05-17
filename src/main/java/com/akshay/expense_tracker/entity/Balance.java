package com.akshay.expense_tracker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "balances")
public class Balance extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User lender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User borrower;

    @Column(nullable = false)
    private BigDecimal amount;
}
