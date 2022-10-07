package com.github.riese.rafael.mystreet.service;

import com.github.riese.rafael.mystreet.model.Profile;
import com.github.riese.rafael.mystreet.model.User;
import com.github.riese.rafael.mystreet.repository.ProfileRepository;
import com.github.riese.rafael.mystreet.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Resource
    private UserRepository userRepository;
    @Resource
    private ProfileRepository profileRepository;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok().body(userRepository.findAll());
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public ResponseEntity<Optional<User>> findById(String id) {
        var user = userRepository.findById(id);

        if (user.isPresent()) {
            return ResponseEntity.ok().body(user);
        }
        return ResponseEntity.notFound().build();
    }

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

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<User> update(User user) {
        Optional<User> userOpt = userRepository.findById(user.getId());

        if (userOpt.isPresent()) {
            User oldUser = userOpt.get();

            BeanUtils.copyProperties(user, oldUser);
            userRepository.save(oldUser);
            return ResponseEntity.ok().body(oldUser);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity<Boolean> delete(String id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            userRepository.deleteById(user.get().getId());
            return ResponseEntity.ok().body(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }
}
