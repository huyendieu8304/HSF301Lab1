package com.dieu.lab1.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Agent {
    @Id
    private Integer id;

    @Column(nullable = false, unique=true)
    private String name;

    @Column(nullable = false, unique=true)
    private String email;

    private String status;

    @Column(nullable = false)
    private String address;

    @CreationTimestamp
    @Column(name = "register_date", nullable = false)
    private Date registerDate;

    @Column(name = "account_balance")
    private Double accountBalance;

}
