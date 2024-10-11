package com.dieu.lab1.entity;

import com.dieu.lab1.enumeration.EAgentStatus;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique=true)
    private String name;

    @Column(nullable = false, unique=true)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EAgentStatus status;

    @Column(nullable = false)
    private String address;

    @Column(name = "register_date", nullable = false)
    private LocalDate registerDate;

    @Column(name = "account_balance")
    private Double accountBalance;

    public Agent() {
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public EAgentStatus getStatus() {
        return status;
    }

    public void setStatus(EAgentStatus status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
