package com.github.riese.rafael.mystreet.service;

import com.github.riese.rafael.mystreet.model.User;
import com.github.riese.rafael.mystreet.repository.UserRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService extends ServiceBase<User, UserRepository> {
    protected UserService(UserRepository userRepository) {
        super(userRepository);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<User> save(User user) throws Exception {
        User newUser;

        try {
            newUser = repository.insert(user);
        } catch (DuplicateKeyException dke) {
            throw new DuplicateKeyException("Cpf/Cnpj ou Email já está em uso!");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return ResponseEntity.ok().body(newUser);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<User> update(User user) throws Exception {
        Optional<User> entityOpt = repository.findById(user.getId());

        if (entityOpt.isPresent()) {
            User oldEntity = entityOpt.get();

            if (user.getProfile() != null && !user.getProfile().equals(oldEntity.getProfile())) {
                return ResponseEntity.notFound().build();
            } else {
                user.setProfile(oldEntity.getProfile());
                user.setPassword(oldEntity.getPassword());
            }

            BeanUtils.copyProperties(user, oldEntity);

            try {
                repository.save(oldEntity);
            } catch (DuplicateKeyException dke) {
                throw new DuplicateKeyException("Cpf/Cnpj ou Email já está em uso!");
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }
            return ResponseEntity.ok().body(oldEntity);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<User> updateUserPassword(User user) throws Exception {
        User userUpdated;
        try {
            userUpdated = repository.save(user);
        } catch (DuplicateKeyException dke) {
            throw new DuplicateKeyException("Cpf/Cnpj ou Email já está em uso!");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return ResponseEntity.ok().body(userUpdated);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public ResponseEntity<User> getCurrentUser(String userId) {
        return ResponseEntity.ok().body(repository.findById(userId).get());
    }

}
