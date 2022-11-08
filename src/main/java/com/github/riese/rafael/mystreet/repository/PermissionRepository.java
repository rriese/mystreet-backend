package com.github.riese.rafael.mystreet.repository;

import com.github.riese.rafael.mystreet.model.Permission;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PermissionRepository extends MongoRepository<Permission, String> {
}
