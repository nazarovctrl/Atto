package org.example.controller.admin;


import org.example.service.AdminProfileService;
import org.example.util.ScannerUtil;
import org.springframework.stereotype.Controller;

@Controller
public class AdminProfileController {

    private final AdminProfileService adminProfileService;

    public AdminProfileController(AdminProfileService adminProfileService) {
        this.adminProfileService = adminProfileService;
    }

    public void start() {

        boolean game = true;
        while (game) {
            menu();
            int action = ScannerUtil.getAction();
            switch (action) {
                case 1 -> get_profile_list();
                case 2 -> change_status();
                case 0 -> game = false;
                default -> System.out.println("Nimabu Mazgi ");
            }

        }


    }

    private void change_status() {

    }

    private void get_profile_list() {
        adminProfileService.get_profile_list();
    }

    private void menu() {
        System.out.println("*****  PROFILE MENU (ADMIN) *****");
        System.out.println("1.Profile list");
        System.out.println("2.Change status");
        System.out.println("0.Back to ADMIN MENU");
    }


}
