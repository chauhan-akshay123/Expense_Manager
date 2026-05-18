package com.akshay.expense_tracker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "settlements")
public class Settlement extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User payer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User receiver;

    @Column(nullable = false)
    private BigDecimal amount;
}
