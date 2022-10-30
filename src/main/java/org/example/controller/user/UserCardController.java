package org.example.controller.user;

import org.example.container.ComponentContainer;
import org.example.dto.Profile_Card;
import org.example.service.Profile_Card_Service;
import org.example.util.ScannerUtil;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.Scanner;

@Controller
public class UserCardController {
    private final Profile_Card_Service profile_card_service;

    public UserCardController(Profile_Card_Service profile_card_service) {
        this.profile_card_service = profile_card_service;
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

        profile_card_service.delete_profile_card(ComponentContainer.currentProfile.getPhone(), number);
    }

    private void card_change_status() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter card number: ");
        String number = scanner.next();

        profile_card_service.change_profile_card_status(ComponentContainer.currentProfile.getPhone(), number);

    }

    private void card_list() {
        profile_card_service.get_profile_card_list(ComponentContainer.currentProfile.getPhone());
    }

    private void add_card() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter card number: ");
        String number = scanner.next();

        Profile_Card profile_card = new Profile_Card(ComponentContainer.currentProfile.getPhone(), number, LocalDateTime.now());

        profile_card_service.addCard_to_User(profile_card);


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