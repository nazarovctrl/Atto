package org.example.service;

import org.example.dto.Card;
import org.example.dto.Profile_Card;
import org.example.enums.GeneralStatus;
import org.example.repository.CardRepository;
import org.example.repository.Profile_Card_Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Profile_Card_Service {

    private final CardRepository cardRepository;
    private final Profile_Card_Repository profile_card_repository;


    public Profile_Card_Service(CardRepository cardRepository, Profile_Card_Repository profile_card_repository) {
        this.cardRepository = cardRepository;
        this.profile_card_repository = profile_card_repository;
    }

    public void addCard_to_User(Profile_Card profile_card) {


        Card card = cardRepository.searchCardByNumber(profile_card.getCard_number());

        if (card == null) {
            System.out.println("Bunday karta mavjud emas");
            return;
        }
        if (card.getPhone() != null) {
            System.out.println("Bu kartani ulay olmaysiz");
            return;
        }


        int i = profile_card_repository.addCard_to_user(profile_card);

        if (i != 0) {
            cardRepository.addPhone_to_Card(profile_card.getCard_number(), profile_card.getProfile_phone());
            System.out.println("Successfully");
        } else {
            System.out.println("Failed");

        }
    }


    public void get_profile_card_list(String phone) {

        List<Card> profile_card_list_fromDb = cardRepository.get_profile_card_list_fromDb(phone);

        for (Card card : profile_card_list_fromDb) {
            System.out.println(card.getNumber() + " # " + card.getExp_date() + " # " + card.getBalance());
        }
    }

    public void change_profile_card_status(String phone, String number) {
        Card card = cardRepository.searchCardByNumber(number);
        if (card == null) {
            System.out.println("The card is not exists");
            return;
        }

        String status = GeneralStatus.BLOCK.name();
        if (card.getStatus().equals(GeneralStatus.BLOCK)) {
            status = GeneralStatus.ACTIVE.name();
        }

        int i = cardRepository.change_profile_card_status_fromDB(phone, number, status);

        if (i != 0) {
            System.out.println("card status changed to " + status);
        } else {
            System.out.println("Failed");
        }
    }

    public void delete_profile_card(String phone, String number) {


        int i = profile_card_repository.delete_profile_card(phone, number);

        if (i != 0) {
            cardRepository.delete_phone_from_card(number);
            System.out.println("Deleted");
        } else {
            System.out.println("Failed");
        }


    }
}

