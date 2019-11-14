package com.staxrt.tutorial.controller;

import com.staxrt.tutorial.exception.ResourceNotFoundException;
import com.staxrt.tutorial.model.Apartment;
import com.staxrt.tutorial.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auabnb")
public class ApartmentController {

    @Autowired
    private ApartmentRepository apartmentRepository;

    @GetMapping("/apartments")
    public List<Apartment> getAllApartments() {
        return apartmentRepository.findAll();
    }


    @GetMapping("/apartments/{id}")
    public ResponseEntity<Apartment> getApartmentsById(@PathVariable(value = "id") Long apartmentId)
            throws ResourceNotFoundException {
        Apartment apartment =
                apartmentRepository
                        .findById(apartmentId)
                        .orElseThrow(() -> new ResourceNotFoundException("Apartment not found on :: " + apartmentId));
        return ResponseEntity.ok().body(apartment);
    }


    @PostMapping("/apartments")
    public Apartment createApartment(@Valid @RequestBody Apartment apartment) {
        return apartmentRepository.save(apartment);
    }


    @PutMapping("/apartments/{id}")
    public ResponseEntity<Apartment> updateApartment(
            @PathVariable(value = "id") Long apartmentId, @Valid @RequestBody Apartment apartmentDetails)
            throws ResourceNotFoundException {

        Apartment apartment =
                apartmentRepository
                        .findById(apartmentId)
                        .orElseThrow(() -> new ResourceNotFoundException("Apartment not found on :: " + apartmentId));

        apartment.setTitle(apartmentDetails.getTitle());
        apartment.setPrice(apartmentDetails.getPrice());
        apartment.setDescription(apartmentDetails.getDescription());
        apartment.setCountry(apartmentDetails.getCountry());
        apartment.setAddress(apartmentDetails.getAddress());
        apartment.setCity_id(apartmentDetails.getCity_id());
        apartment.setUser_id(apartmentDetails.getUser_id());

        final Apartment updatedApartment = apartmentRepository.save(apartment);
        return ResponseEntity.ok(updatedApartment);
    }

    @DeleteMapping("/apartments/{id}")
    public Map<String, Boolean> deleteApartment(@PathVariable(value = "id") Long apartmentId) throws Exception {
        Apartment apartment =
                apartmentRepository
                        .findById(apartmentId)
                        .orElseThrow(() -> new ResourceNotFoundException("Apartment not found on :: " + apartmentId));

        apartmentRepository.delete(apartment);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
