package org.example.controller.admin;

import org.example.dto.Card;
import org.example.dto.Transaction;
import org.example.enums.GeneralStatus;
import org.example.enums.TransactionType;
import org.example.service.CardService;
import org.example.util.CardUtil;
import org.example.util.ScannerUtil;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.Scanner;


@Controller
public class AdminCardController {


    private final CardService cardService;

    public AdminCardController(CardService cardService) {
        this.cardService = cardService;
    }



    public void start() {
        boolean game = true;
        while (game) {
            menu();
            int action = ScannerUtil.getAction();

            switch (action) {
                case 1 -> create();
                case 2 -> get_card_list();
                case 3 -> update();
                case 4 -> change_status();
                case 5 -> refill();
                case 6 -> delete();
                case 0 -> game = false;
                default -> System.out.println("Mazgi nima bu");
            }


        }


    }

    private void update() {
        Card card_from_scanner = CardUtil.getFromScanner();


        Card card = new Card(card_from_scanner.getNumber(), card_from_scanner.getExp_date(), 0L, GeneralStatus.ACTIVE, null, LocalDateTime.now());

        cardService.update(card);

    }

    private void delete() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter card number: ");
        String number = scanner.next();

        cardService.deleteCard(number);

    }

    private void refill() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter card number: ");
        String number = scanner.next();

        System.out.print("Enter amount:");
        Long amount = scanner.nextLong();
        Transaction transaction = new Transaction(number, amount, null, TransactionType.ReFill, LocalDateTime.now());

        cardService.reFill(transaction);


    }

    private void change_status() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter card number: ");
        String number = scanner.next();

        cardService.changeStatus_admin(number);


    }

    private void get_card_list() {

        cardService.get_card_list();

    }

    private void create() {

        Card card_from_scanner = CardUtil.getFromScanner();


        Card card = new Card(card_from_scanner.getNumber(), card_from_scanner.getExp_date(), 0L, GeneralStatus.ACTIVE, null, LocalDateTime.now());

        cardService.addCard_toDb(card);

    }


    private void menu() {
        System.out.println("*****  CARD MENU (ADMIN)  *****");

        System.out.println("1.Create");
        System.out.println("2.Card List");
        System.out.println("3.Update");
        System.out.println("4.Change Status");
        System.out.println("5.Refill");
        System.out.println("6.Delete");
        System.out.println("0.Back to ADMIN MENU");

    }


}
