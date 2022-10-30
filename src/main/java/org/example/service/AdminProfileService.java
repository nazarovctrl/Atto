package org.example.service;

import org.example.dto.Profile;
import org.example.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminProfileService {

    private final ProfileRepository profileRepository;

    public AdminProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public void get_profile_list() {
        List<Profile> profile_list_fromDb = profileRepository.get_profile_list_fromDb();

        for (Profile profile : profile_list_fromDb) {
            System.out.println(profile);
        }

    }

}
