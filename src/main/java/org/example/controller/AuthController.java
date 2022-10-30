package org.example.controller;

import org.example.dto.Profile;
import org.example.enums.GeneralStatus;
import org.example.enums.ProfileRole;
import org.example.service.ProfileService;
import org.example.util.MD5;
import org.example.util.ScannerUtil;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.Scanner;

@Controller
public class AuthController {


    private final ProfileService profileService;

    public AuthController(ProfileService profileService) {
        this.profileService = profileService;
    }

    public void start() {

        boolean game = true;
        while (game) {

            menu();
            int action = ScannerUtil.getAction();

            switch (action) {
                case 1 -> login();
                case 2 -> registration();
                case 0 -> game = false;
                default -> System.out.println("Mazgi nima bu");
            }
        }


    }

    private void registration() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter name:");
        String name = scanner.next();

        System.out.print("Enter surname:");
        String surname = scanner.next();

        System.out.print("Enter phone:");
        String phone = scanner.next();

        System.out.print("Enter password:");
        String password = scanner.next();

        Profile profile = new Profile(name, surname, phone, MD5.md5(password), LocalDateTime.now(), GeneralStatus.ACTIVE, ProfileRole.USER);

        profileService.addProfile(profile);

    }

    private void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter phone: ");
        String phone = scanner.next();

        System.out.print("Enter password: ");
        String password = scanner.next();

        profileService.login(phone, password);


    }


    private void menu() {
        System.out.println("*****  MENU  *****");

        System.out.println("1.Login");
        System.out.println("2.Registration");
        System.out.println("0.Exit");
    }

}
