package com.github.riese.rafael.mystreet.service;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IService <T> {
    ResponseEntity<List<T>> findAll();
    ResponseEntity<Optional<T>> findById(String id);
    ResponseEntity<T> save(T t) throws Exception;
    ResponseEntity<T> update(T t) throws Exception;
    ResponseEntity<Boolean> delete(String id);
}
