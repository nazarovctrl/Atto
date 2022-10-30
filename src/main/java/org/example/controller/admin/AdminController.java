package org.example.controller.admin;


import org.example.service.TransactionService;
import org.example.util.ScannerUtil;
import org.springframework.stereotype.Controller;

@Controller
public class AdminController {
    private final TransactionService transactionService;
    private final AdminProfileController adminProfileController;
    private final AdminTerminalController adminTerminalController;
    private final AdminCardController adminCardController;



    public AdminController(TransactionService transactionService, AdminProfileController adminProfileController, AdminTerminalController adminTerminalController, AdminCardController adminCardController) {
        this.transactionService = transactionService;
        this.adminProfileController = adminProfileController;
        this.adminTerminalController = adminTerminalController;
        this.adminCardController = adminCardController;
    }

    public void start() {
        boolean game = true;
        while (game) {
            menu();
            int action = ScannerUtil.getAction();
            switch (action) {
                case 1 -> card();
                case 2 -> terminal();
                case 3 -> profile();
                case 4 -> transaction_list();
                case 5 -> company_card();
                case 0 -> game = false;
                default -> System.out.println("Mazgi nima bu");
            }
        }
    }

    private void company_card() {

    }

    private void transaction_list() {
        transactionService.get_transaction_list();

    }

    private void profile() {
        adminProfileController.start();
    }

    private void terminal() {
        adminTerminalController.start();
    }

    private void card() {
        adminCardController.start();
    }


    private void menu() {
        System.out.println("*****  ADMIN MENU  *****");

        System.out.println("1.Card");
        System.out.println("2.Terminal");
        System.out.println("3.Profile");
        System.out.println("4.Transaction List");
        System.out.println("5.Company Card");
        System.out.println("0.Back to Menu");
    }


}
