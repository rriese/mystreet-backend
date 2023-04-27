package com.github.riese.rafael.mystreet.service;

import com.github.riese.rafael.mystreet.model.IEntity;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public abstract class ServiceBase<T extends IEntity, U extends MongoRepository> implements IService<T>{
    final U repository;

    protected ServiceBase(U u) {
        this.repository = u;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public ResponseEntity<List<T>> findAll() {
        return ResponseEntity.ok().body(repository.findAll());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public ResponseEntity<Optional<T>> findById(String id) {
        var entity = repository.findById(id);

        if (entity.isPresent()) {
            return ResponseEntity.ok().body(entity);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<T> save(T t) throws Exception {
        T newEntity;

        try {
            newEntity = (T) repository.insert(t);
        } catch (DuplicateKeyException dke) {
            throw new DuplicateKeyException("Chave duplicada na base!");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return ResponseEntity.ok().body(newEntity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<T> update(T t) throws Exception {
        Optional<T> entityOpt = repository.findById(t.getId());

        if (entityOpt.isPresent()) {
            T oldEntity = entityOpt.get();

            BeanUtils.copyProperties(t, oldEntity);

            try {
                repository.save(oldEntity);
            } catch (DuplicateKeyException dke) {
                throw new DuplicateKeyException("Chave duplicada na base!");
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }
            return ResponseEntity.ok().body(oldEntity);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity<Boolean> delete(String id) {
        Optional<T> entity = repository.findById(id);

        if (entity.isPresent()) {
            repository.deleteById(entity.get().getId());
            return ResponseEntity.ok().body(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }
}
