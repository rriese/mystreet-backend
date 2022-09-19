package com.github.riese.rafael.mystreet.service;

import com.github.riese.rafael.mystreet.model.User;
import com.github.riese.rafael.mystreet.repository.UserRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public User save(User user) throws Exception {
        User userPersisted;

        try {
            userPersisted = userRepository.insert(user);
        } catch (DuplicateKeyException dke) {
            throw new DuplicateKeyException("Username already in use!");
        } catch (Exception ex) {
            throw new Exception("Error saving user!");
        }
        return userPersisted;
    }
}
