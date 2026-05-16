package com.akshay.expense_tracker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "groups")
public class Group extends BaseEntity{

    @Column(nullable = false)
    private String name;

    private String description;
}
