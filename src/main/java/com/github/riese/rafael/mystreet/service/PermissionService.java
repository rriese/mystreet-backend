package com.github.riese.rafael.mystreet.service;

import com.github.riese.rafael.mystreet.model.Permission;
import com.github.riese.rafael.mystreet.repository.PermissionRepository;

import org.springframework.stereotype.Service;

@Service
public class PermissionService extends ServiceBase<Permission, PermissionRepository> {
    protected PermissionService(PermissionRepository permissionRepository) {
        super(permissionRepository);
    }
}
