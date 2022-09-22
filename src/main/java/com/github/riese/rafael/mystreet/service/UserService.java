package com.github.riese.rafael.mystreet.service;

import com.github.riese.rafael.mystreet.model.User;
import com.github.riese.rafael.mystreet.repository.UserRepository;
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

        try {
            newUser = userRepository.insert(user);
        } catch (DuplicateKeyException dke) {
            throw new DuplicateKeyException("Cpf/Cnpj or Email already in use!");
        } catch (Exception ex) {
            throw new Exception("Error saving user!");
        }
        return newUser;
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
