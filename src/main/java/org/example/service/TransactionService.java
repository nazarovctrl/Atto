package org.example.service;

import org.example.container.ComponentContainer;
import org.example.dto.Card;
import org.example.dto.Transaction;
import org.example.enums.TransactionType;
import org.example.repository.CardRepository;
import org.example.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CardRepository cardRepository;


    public TransactionService(TransactionRepository transactionRepository, CardRepository cardRepository) {
        this.transactionRepository = transactionRepository;
        this.cardRepository = cardRepository;
    }




    public void get_transaction_list() {

        List<Transaction> transaction_list_from_db = transactionRepository.get_transaction_list_from_db();

        for (Transaction transaction : transaction_list_from_db) {
            System.out.println(transaction);
        }

    }

    public void profile_reFill(String number, Long amount) {

        Card card = cardRepository.searchCardByNumber(number);
        if (card == null) {
            System.out.println("Bunday card yoq");
            return;
        }
        if (card.getPhone() == null) {
            System.out.println("This card is not yours");
            return;
        }

        if (!card.getPhone().equals(ComponentContainer.currentProfile.getPhone())) {
            System.out.println("This card is not yours");
            return;
        }

        int i = transactionRepository.profile_refill(number, amount);

        if (i == 0) {
            System.out.println("Failed");
            return;
        }

        Transaction transaction = new Transaction(number, amount, null, TransactionType.ReFill, LocalDateTime.now());

        transactionRepository.addTransaction(transaction);

    }

    public void get_profile_transaction_list() {
        List<Transaction> profile_transaction_list_fromDb = transactionRepository.get_profile_transaction_list_fromDb(ComponentContainer.currentProfile.getPhone());
        for (Transaction transaction : profile_transaction_list_fromDb) {
            System.out.println(transaction);
        }

    }

    public void make_payment(Transaction transaction) {

        Card card = cardRepository.searchCardByNumber(transaction.getCard_number());
        if (card == null) {
            System.out.println("The card is not available");
            return;
        }

        if (card.getPhone() == null || !card.getPhone().equals(ComponentContainer.currentProfile.getPhone())) {
            System.out.println("The card is not yours");
            return;
        }


        int i = transactionRepository.make_payment(transaction,ComponentContainer.currentProfile.getPhone());


    }


}
