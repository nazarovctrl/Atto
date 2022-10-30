package org.example.service;


import org.example.container.ComponentContainer;
import org.example.controller.admin.AdminController;
import org.example.controller.user.UserController;
import org.example.dto.Profile;
import org.example.enums.GeneralStatus;
import org.example.enums.ProfileRole;
import org.example.repository.ProfileRepository;
import org.example.util.MD5;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {


    private final ProfileRepository profileRepository;
    private final AdminController adminController;
    private final UserController userController;

    public ProfileService(ProfileRepository profileRepository, AdminController adminController, UserController userController) {
        this.profileRepository = profileRepository;
        this.adminController = adminController;
        this.userController = userController;
    }


    public void login(String phone, String password) {
        Profile profile = profileRepository.searchByPhone(phone);

        if (profile == null) {
            System.out.println("Phone xato!");
            return;
        }

        if (!profile.getPassword().equals(MD5.md5(password))) {
            System.out.println("Parol xato");
            return;
        }

        if (profile.getStatus().equals(GeneralStatus.BLOCK)) {
            System.out.println("Bu login blocklangan");
            return;
        }

        ComponentContainer.currentProfile = profile;

        if (profile.getRole().equals(ProfileRole.ADMIN)) {
            adminController.start();

        } else if (profile.getRole().equals(ProfileRole.USER)) {

            userController.start();
        }


    }


    public void addProfile(Profile profile) {
        Profile profile1 = profileRepository.searchByPhone(profile.getPhone());

        if (profile1 != null) {
            System.out.println("Phone already exists");
            return;
        }

        int i = profileRepository.addProfileToDb(profile);

        if (i != 0) {
            System.out.println("Successfully");
            return;
        }

        System.out.println("Failed");

    }


}
