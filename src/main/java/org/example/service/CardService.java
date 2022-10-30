package org.example.service;

import org.example.dto.Card;
import org.example.dto.Transaction;
import org.example.enums.GeneralStatus;
import org.example.repository.CardRepository;
import org.example.repository.TransactionRepository;
import org.springframework.stereotype.Service;

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

    public void changeStatus(String number) {
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


        transactionRepository.reFill(transaction);


    }

    public void deleteCard(String number) {
        int i = cardRepository.deleteCardFromDb(number);
        if (i != 0) {
            System.out.println("Deleted");
        } else {
            System.out.println("Failed");
        }

    }


}
