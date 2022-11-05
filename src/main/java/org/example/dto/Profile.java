package org.example.dto;

import org.example.enums.GeneralStatus;
import org.example.enums.ProfileRole;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Profile  {
//    name,surname,phone unique,pswd,created_date,status,role

    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String password;
    private LocalDateTime created_date;
    private GeneralStatus status;
    private ProfileRole role;

    public Profile() {
    }

    public Profile(Long id, String name, String surname, String phone, String password, LocalDateTime created_date, GeneralStatus status, ProfileRole role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.password = password;
        this.created_date = created_date;
        this.status = status;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDateTime created_date) {
        this.created_date = created_date;
    }

    public GeneralStatus getStatus() {
        return status;
    }

    public void setStatus(GeneralStatus status) {
        this.status = status;
    }

    public ProfileRole getRole() {
        return role;
    }

    public void setRole(ProfileRole role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", created_date=" + created_date +
                ", status=" + status +
                ", role=" + role +
                '}';
    }

}
