package com.github.riese.rafael.mystreet.service;

import com.github.riese.rafael.mystreet.model.Profile;
import com.github.riese.rafael.mystreet.model.User;
import com.github.riese.rafael.mystreet.repository.ProfileRepository;
import com.github.riese.rafael.mystreet.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
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
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Optional<User> findByEmail(String login) {
        return userRepository.findByEmail(login);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public User save(User user) throws Exception {
        User newUser;

        Optional<Profile> userProfile = profileRepository.findById(user.getProfile().getId());

        if (userProfile.isPresent()) {
            user.setProfile(userProfile.get());
        }

        try {
            newUser = userRepository.insert(user);
        } catch (DuplicateKeyException dke) {
            throw new DuplicateKeyException("Cpf/Cnpj or Email already in use!");
        } catch (Exception ex) {
            throw new Exception("Error saving user!");
        }
        return newUser;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public User update(User user) {
        Optional<User> userOpt = userRepository.findById(user.getId());

        if (userOpt.isPresent()) {
            User oldUser = userOpt.get();

            BeanUtils.copyProperties(user, oldUser);
            userRepository.save(oldUser);
            return oldUser;
        }
        return user;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(String email) {
        Optional<User> user = this.findByEmail(email);

        if (user.isPresent()) {
            userRepository.deleteById(user.get().getId());
            return true;
        }
        return false;
    }
}
