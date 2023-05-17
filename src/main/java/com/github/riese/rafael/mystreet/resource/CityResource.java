package com.github.riese.rafael.mystreet.resource;

import com.github.riese.rafael.mystreet.model.City;
import com.github.riese.rafael.mystreet.model.State;
import com.github.riese.rafael.mystreet.service.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/city")
public class CityResource {
    @Resource
    private CityService cityService;

    @GetMapping("/")
    public ResponseEntity<List<City>> getCities() {
        return cityService.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<City> save(@RequestBody City city) throws Exception {
        return cityService.save(city);
    }

    @PutMapping("/")
    public ResponseEntity<City> update(@RequestBody City city) throws Exception {
        return cityService.update(city);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        return cityService.delete(id);
    }
}
