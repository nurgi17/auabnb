package com.staxrt.tutorial.controller;

import com.staxrt.tutorial.exception.ResourceNotFoundException;
import com.staxrt.tutorial.model.City;
import com.staxrt.tutorial.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auabnb")
public class CityController {
    @Autowired
    private CityRepository cityRepository;

    @GetMapping("/cities")
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }


    @GetMapping("/cities/{id}")
    public ResponseEntity<City> getCitysById(@PathVariable(value = "id") Long cityId)
            throws ResourceNotFoundException {
        City city =
                cityRepository
                        .findById(cityId)
                        .orElseThrow(() -> new ResourceNotFoundException("City not found on :: " + cityId));
        return ResponseEntity.ok().body(city);
    }


    @PostMapping("/cities")
    public City createCity(@Valid @RequestBody City city) {
        return cityRepository.save(city);
    }


    @PutMapping("/cities/{id}")
    public ResponseEntity<City> updateCity(
            @PathVariable(value = "id") Long cityId, @Valid @RequestBody City cityDetails)
            throws ResourceNotFoundException {

        City city =
                cityRepository
                        .findById(cityId)
                        .orElseThrow(() -> new ResourceNotFoundException("City not found on :: " + cityId));

        city.setName(cityDetails.getName());
        city.setPhoto(cityDetails.getPhoto());
        final City updatedCity = cityRepository.save(city);
        return ResponseEntity.ok(updatedCity);
    }

    @DeleteMapping("/cities/{id}")
    public Map<String, Boolean> deleteCity(@PathVariable(value = "id") Long cityId) throws Exception {
        City city =
                cityRepository
                        .findById(cityId)
                        .orElseThrow(() -> new ResourceNotFoundException("City not found on :: " + cityId));

        cityRepository.delete(city);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
