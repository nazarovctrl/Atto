package org.example.dto;

import org.example.enums.GeneralStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Card {
    private Integer id;
    private String number;
    private LocalDate exp_date;
    private Long balance;
    private GeneralStatus status;
    private String phone;
    private LocalDateTime created_date;
    private LocalDateTime added_date;

    public Card() {
    }

    public Card(String number, LocalDate exp_date, Long balance, GeneralStatus status, String phone, LocalDateTime created_date) {
        this.number = number;
        this.exp_date = exp_date;
        this.balance = balance;
        this.status = status;
        this.phone = phone;
        this.created_date = created_date;
    }

    public Card(Integer id, String number, LocalDate exp_date, Long balance, GeneralStatus status, String phone, LocalDateTime created_date, LocalDateTime added_date) {
        this.id = id;
        this.number = number;
        this.exp_date = exp_date;
        this.balance = balance;
        this.status = status;
        this.phone = phone;
        this.created_date = created_date;
        this.added_date = added_date;
    }

    public Card(Integer id, String number, LocalDate exp_date, Long balance, GeneralStatus status, String phone, LocalDateTime created_date) {
        this.id = id;
        this.number = number;
        this.exp_date = exp_date;
        this.balance = balance;
        this.status = status;
        this.phone = phone;
        this.created_date = created_date;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getExp_date() {
        return exp_date;
    }

    public void setExp_date(LocalDate exp_date) {
        this.exp_date = exp_date;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public GeneralStatus getStatus() {
        return status;
    }

    public void setStatus(GeneralStatus status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDateTime created_date) {
        this.created_date = created_date;
    }

    public LocalDateTime getAdded_date() {
        return added_date;
    }

    public void setAdded_date(LocalDateTime added_date) {
        this.added_date = added_date;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", exp_date=" + exp_date +
                ", balance=" + balance +
                ", status=" + status +
                ", phone='" + phone + '\'' +
                ", created_date=" + created_date +
                ", added_date=" + added_date +
                '}';
    }
}
