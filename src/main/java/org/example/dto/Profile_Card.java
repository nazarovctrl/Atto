package org.example.dto;

import java.time.LocalDateTime;

public class Profile_Card {
    private Integer id;
    private String profile_phone;
    private String card_number;
    private LocalDateTime added_date;

    public Profile_Card() {
    }

    public Profile_Card(String profile_phone, String card_number, LocalDateTime added_date) {
        this.profile_phone = profile_phone;
        this.card_number = card_number;
        this.added_date = added_date;
    }

    public Profile_Card(Integer id, String profile_phone, String card_number, LocalDateTime added_date) {
        this.id = id;
        this.profile_phone = profile_phone;
        this.card_number = card_number;
        this.added_date = added_date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProfile_phone() {
        return profile_phone;
    }

    public void setProfile_phone(String profile_phone) {
        this.profile_phone = profile_phone;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public LocalDateTime getAdded_date() {
        return added_date;
    }

    public void setAdded_date(LocalDateTime added_date) {
        this.added_date = added_date;
    }


    @Override
    public String toString() {
        return "Profile_Card{" +
                "id=" + id +
                ", profile_phone='" + profile_phone + '\'' +
                ", card_number='" + card_number + '\'' +
                ", added_date=" + added_date +
                '}';
    }
}
