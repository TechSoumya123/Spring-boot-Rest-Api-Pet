package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {

}
