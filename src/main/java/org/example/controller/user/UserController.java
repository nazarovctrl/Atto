package org.example.controller.user;


import org.example.dto.Transaction;
import org.example.enums.TransactionType;
import org.example.service.TransactionService;
import org.example.util.ScannerUtil;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.Scanner;

@Controller
public class UserController {
    private final TransactionService transactionService;
    private  final UserCardController userCardController;

    public UserController(TransactionService transactionService, UserCardController userCardController) {
        this.transactionService = transactionService;
        this.userCardController = userCardController;
    }




    public void start() {
        boolean game = true;
        while (game) {
            menu();
            int action = ScannerUtil.getAction();
            switch (action) {
                case 1 -> card();
                case 2 -> reFill();
                case 3 -> transaction();
                case 4 -> make_payment();
                case 0 -> game = false;
                default -> System.out.println("Mazgi nima bu");
            }
        }

    }

    private void make_payment() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter card number: ");
        String number = scanner.next();


        System.out.print("Enter terminal code: ");
        String terminal_c = scanner.next();

        Transaction transaction = new Transaction(number, 1400L, terminal_c, TransactionType.Payment, LocalDateTime.now());

        transactionService.make_payment(transaction);

    }

    private void transaction() {
        transactionService.get_profile_transaction_list();
    }

    private void reFill() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter card number: ");
        String number = scanner.next();

        System.out.println("Enter amount: ");
        Long amount = scanner.nextLong();

        transactionService.profile_reFill(number, amount);


    }

    private void card() {
        userCardController.start();
    }


    public void menu() {
        System.out.println("***** PROFILE MENU *****");
        System.out.println("1.Card");
        System.out.println("2.ReFill");
        System.out.println("3.Transaction");
        System.out.println("4.Make Payment");
        System.out.println("0.Back to Menu");
    }

}
