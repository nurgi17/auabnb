package com.staxrt.tutorial.controller;

import com.staxrt.tutorial.exception.ResourceNotFoundException;
import com.staxrt.tutorial.model.Photos;
import com.staxrt.tutorial.repository.PhotosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auabnb")
public class PhotosController {

    @Autowired
    private PhotosRepository photosRepository;
    @GetMapping("/photos")
    public List<Photos> getAllPhotos() {
        return photosRepository.findAll();
    }


    @GetMapping("/photos/{id}")
    public ResponseEntity<Photos> getPhotosById(@PathVariable(value = "id") Long photoId)
            throws ResourceNotFoundException {
        Photos photo =
                photosRepository
                        .findById(photoId)
                        .orElseThrow(() -> new ResourceNotFoundException("Photo not found on :: " + photoId));
        return ResponseEntity.ok().body(photo);
    }


    @PostMapping("/photos")
    public Photos createPhoto(@Valid @RequestBody Photos photo) {
        return photosRepository.save(photo);
    }


    @PutMapping("/photos/{id}")
    public ResponseEntity<Photos> updatePhoto(
            @PathVariable(value = "id") Long photoId, @Valid @RequestBody Photos photoDetails)
            throws ResourceNotFoundException {

        Photos photo =
                photosRepository
                        .findById(photoId)
                        .orElseThrow(() -> new ResourceNotFoundException("Photo not found on :: " + photoId));

        photo.setPhoto(photoDetails.getPhoto());
        photo.setApartment_id(photoDetails.getApartment_id());
        final Photos updatedPhoto = photosRepository.save(photo);
        return ResponseEntity.ok(updatedPhoto);
    }

    @DeleteMapping("/photos/{id}")
    public Map<String, Boolean> deletePhoto(@PathVariable(value = "id") Long photoId) throws Exception {
        Photos photo =
                photosRepository
                        .findById(photoId)
                        .orElseThrow(() -> new ResourceNotFoundException("Photo not found on :: " + photoId));

        photosRepository.delete(photo);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
