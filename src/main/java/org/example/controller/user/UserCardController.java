package org.example.controller.user;

import org.example.service.CardService;
import org.example.util.ScannerUtil;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class UserCardController {
    private final CardService  cardService;

    public UserCardController(CardService cardService) {
        this.cardService = cardService;
    }

    public void start() {
        boolean game = true;
        while (game) {
            menu();
            int action = ScannerUtil.getAction();
            switch (action) {
                case 1 -> add_card();
                case 2 -> card_list();
                case 3 -> card_change_status();
                case 4 -> delete_card();
                case 0 -> game = false;
                default -> System.out.println("Mazgi nima bu");
            }
        }


    }

    private void delete_card() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter card number: ");
        String number = scanner.next();

        cardService.delete_profile_card(number);
    }

    private void card_change_status() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter card number: ");
        String number = scanner.next();

        cardService.changeStatus_user(number);

    }

    private void card_list() {
        cardService.get_profile_card_list();
    }

    private void add_card() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter card number: ");
        String number = scanner.next();


        cardService.addCard_to_User(number);


    }


    private void menu() {
        System.out.println("**** CARD MENU ****");
        System.out.println("1.Add card");
        System.out.println("2.Card list");
        System.out.println("3.Card change status");
        System.out.println("4.Delete card");
        System.out.println("0.Back to PROFILE MENU");
    }
}