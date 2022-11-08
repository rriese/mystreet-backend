package com.github.riese.rafael.mystreet.service;

import com.github.riese.rafael.mystreet.model.Profile;
import com.github.riese.rafael.mystreet.repository.ProfileRepository;

import org.springframework.stereotype.Service;

@Service
public class ProfileService extends ServiceBase<Profile, ProfileRepository>{
    protected ProfileService(ProfileRepository profileRepository) {
        super(profileRepository);
    }
}
