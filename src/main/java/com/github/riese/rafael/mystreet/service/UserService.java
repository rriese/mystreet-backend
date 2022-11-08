package com.github.riese.rafael.mystreet.service;

import com.github.riese.rafael.mystreet.model.Profile;
import com.github.riese.rafael.mystreet.model.User;
import com.github.riese.rafael.mystreet.repository.ProfileRepository;
import com.github.riese.rafael.mystreet.repository.UserRepository;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class UserService extends ServiceBase<User, UserRepository> {
    private UserRepository userRepository;
    @Resource
    private ProfileRepository profileRepository;

    protected UserService(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<User> save(User user) throws Exception {
        User newUser;

        if (user.getProfile() != null) {
            Optional<Profile> userProfile = profileRepository.findById(user.getProfile().getId());

            if (userProfile.isPresent()) {
                user.setProfile(userProfile.get());
            }
        }

        try {
            newUser = userRepository.insert(user);
        } catch (DuplicateKeyException dke) {
            throw new DuplicateKeyException("Cpf/Cnpj ou Email já está em uso! \n" + dke.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return ResponseEntity.ok().body(newUser);
    }

}
