package com.staxrt.tutorial.repository;

import com.staxrt.tutorial.model.Photos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotosRepository extends JpaRepository<Photos, Long> {}
