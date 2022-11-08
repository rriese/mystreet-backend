package com.github.riese.rafael.mystreet.resource;

import com.github.riese.rafael.mystreet.model.Status;
import com.github.riese.rafael.mystreet.service.StatusService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/status")
public class StatusResource {
    @Resource
    private StatusService statusService;

    @GetMapping("/")
    public ResponseEntity<List<Status>> getStatus() {
        return statusService.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<Status> save(@RequestBody Status status) throws Exception {
        return statusService.save(status);
    }

    @PutMapping("/")
    public ResponseEntity<Status> update(@RequestBody Status status) throws Exception {
        return statusService.update(status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        return statusService.delete(id);
    }
}
