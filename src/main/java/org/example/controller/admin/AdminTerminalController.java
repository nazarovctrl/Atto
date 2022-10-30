package org.example.controller.admin;

import org.example.dto.Terminal;
import org.example.enums.GeneralStatus;
import org.example.service.TerminalService;
import org.example.util.ScannerUtil;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.Scanner;

@Controller
public class AdminTerminalController {
    private final TerminalService terminalService;

    public AdminTerminalController(TerminalService terminalService) {
        this.terminalService = terminalService;
    }

    public void start() {
        boolean game = true;
        while (game) {
            menu();
            int action = ScannerUtil.getAction();
            switch (action) {
                case 1 -> create();
                case 2 -> get_terminal_list();
                case 3 -> update();
                case 4 -> change_status();
                case 5 -> delete();
                case 0 -> game = false;
                default -> System.out.println("Mazgi nima bu!");
            }
        }
    }

    private void delete() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter code:");
        String code = scanner.next();

        terminalService.deleteTerminal(code);

    }

    private void change_status() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter code:");
        String code = scanner.next();

        terminalService.changeTerminal_status(code);

    }

    private void update() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter code:");
        String code = scanner.next();

        System.out.print("Enter address: ");
        String address = scanner.next();
        terminalService.updateTerminal_address(code, address);


    }

    private void get_terminal_list() {

        terminalService.get_terminal_list();

    }

    private void create() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter code:");
        String code = scanner.next();

        System.out.print("Enter address");
        String address = scanner.next();

        Terminal terminal = new Terminal(code, address, GeneralStatus.ACTIVE, LocalDateTime.now());
        terminalService.create(terminal);

    }


    private void menu() {
        System.out.println("*****  TERMINAL MENU (ADMIN) *****");
        System.out.println("1.Create");
        System.out.println("2.Terminal list");
        System.out.println("3.Update");
        System.out.println("4.Change status");
        System.out.println("5.Delete");
        System.out.println("0.Back to ADMIN MENU");
    }
}
