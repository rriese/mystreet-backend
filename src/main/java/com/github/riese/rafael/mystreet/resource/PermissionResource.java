package com.github.riese.rafael.mystreet.resource;

import com.github.riese.rafael.mystreet.model.Permission;
import com.github.riese.rafael.mystreet.service.PermissionService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/permission")
public class PermissionResource {
    @Resource
    private PermissionService permissionService;

    @GetMapping("/")
    public ResponseEntity<List<Permission>> getPermissions() {
        return permissionService.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<Permission> save(@RequestBody Permission permission) throws Exception {
        return permissionService.save(permission);
    }

    @PutMapping("/")
    public ResponseEntity<Permission> update(@RequestBody Permission permission) throws Exception {
        return permissionService.update(permission);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        return permissionService.delete(id);
    }
}
