package com.github.riese.rafael.mystreet.resource;

import com.github.riese.rafael.mystreet.model.State;
import com.github.riese.rafael.mystreet.service.StateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/state")
public class StateResource {
    @Resource
    private StateService stateService;

    @GetMapping("/")
    public ResponseEntity<List<State>> getStatesAndCities() {
        return stateService.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<State> save(@RequestBody State stateAndCity) throws Exception {
        return stateService.save(stateAndCity);
    }

    @PutMapping("/")
    public ResponseEntity<State> update(@RequestBody State stateAndCity) throws Exception {
        return stateService.update(stateAndCity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        return stateService.delete(id);
    }
}
