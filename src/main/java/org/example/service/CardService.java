package org.example.service;

import org.example.container.ComponentContainer;
import org.example.dto.Card;
import org.example.dto.Transaction;
import org.example.enums.GeneralStatus;
import org.example.repository.CardRepository;
import org.example.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final TransactionRepository transactionRepository;


    public CardService(CardRepository cardRepository, TransactionRepository transactionRepository) {
        this.cardRepository = cardRepository;
        this.transactionRepository = transactionRepository;
    }

    public void addCard_toDb(Card card) {
        Card card1 = cardRepository.searchCardByNumber(card.getNumber());
        if (card1 != null) {
            System.out.println("Bunday karta mavjud");
            return;
        }

        int i = cardRepository.addCardToDb(card);

    }


    public void get_card_list() {
        List<Card> card_list = cardRepository.get_card_list();

        for (Card card : card_list) {
            System.out.println(card);

        }

    }

    public void update(Card card) {

        int n = cardRepository.updateCardFromDb(card);
        if (n != 0) {
            System.out.println("Updated");
        } else {
            System.out.println("Failed");
        }
    }

    public void changeStatus_admin(String number) {
        Card card = cardRepository.searchCardByNumber(number);
        if (card == null) {
            System.out.println("Bunday card yoq");
            return;
        }

        String status = GeneralStatus.BLOCK.name();
        if (card.getStatus().equals(GeneralStatus.BLOCK)) {
            status = GeneralStatus.ACTIVE.name();

        }

        int i = cardRepository.changeStatus(number, status);

        if (i != 0) {
            System.out.println("Changed to" + status);
        } else {
            System.out.println("Failed");
        }

    }

    public void reFill(Transaction transaction) {

        Card card = cardRepository.searchCardByNumber(transaction.getCard_number());
        if (card == null) {
            System.out.println("The card is not exists");
            return;
        }
        int i = cardRepository.refillCard(transaction.getAmount(), transaction.getCard_number());

        if (i != 0) {
            transactionRepository.reFill(transaction);
        }

    }

    public void deleteCard(String number) {
        int i = cardRepository.deleteCardFromDb(number);
        if (i != 0) {
            System.out.println("Deleted");
        } else {
            System.out.println("Failed");
        }

    }


    public void addCard_to_User(String number) {


        Card card = cardRepository.searchCardByNumber(number);

        if (card == null) {
            System.out.println("Bunday karta mavjud emas");
            return;
        }
        if (card.getPhone() != null) {
            System.out.println("Bu kartani ulay olmaysiz");
            return;
        }

        card.setPhone(ComponentContainer.currentProfile.getPhone());
        card.setAdded_date(LocalDateTime.now());
        int i = cardRepository.addPhone_to_Card(card);

        if (i != 0) {
            System.out.println("Successfully");
        } else {
            System.out.println("Failed");

        }
    }

    public void get_profile_card_list() {

        List<Card> profile_card_list_fromDb = cardRepository.get_profile_card_list_fromDb(ComponentContainer.currentProfile.getPhone());


        for (Card card : profile_card_list_fromDb) {
            System.out.println(card.getNumber() + " # " + card.getExp_date() + " # " + card.getBalance());
        }
    }

    public void changeStatus_user(String number) {
        Card card = cardRepository.searchCardByNumber(number);
        if (card == null) {
            System.out.println("The card is not exists");
            return;
        }

        if (!card.getPhone().equals(ComponentContainer.currentProfile.getPhone())) {
            System.out.println("The card is not yours");
            return;
        }

        String status = GeneralStatus.BLOCK.name();
        if (card.getStatus().equals(GeneralStatus.BLOCK)) {
            status = GeneralStatus.ACTIVE.name();
        }


        int i = cardRepository.changeStatus(number, status);

        if (i != 0) {
            System.out.println("card status changed to " + status);
        } else {
            System.out.println("Failed");
        }
    }

    public void delete_profile_card(String number) {

        Card card = cardRepository.searchCardByNumber(number);
        if (card == null) {
            System.out.println("The card is not exists");
            return;
        }

        if (!card.getPhone().equals(ComponentContainer.currentProfile.getPhone())) {
            System.out.println("The card is not yours");
            return;
        }


        int i = cardRepository.delete_phone_from_card(number);

        if (i != 0) {
            cardRepository.delete_phone_from_card(number);
            System.out.println("Deleted");
        } else {
            System.out.println("Failed");
        }


    }
}
