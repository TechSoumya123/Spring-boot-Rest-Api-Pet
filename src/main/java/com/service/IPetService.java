package com.service;

import java.util.List;

import com.dto.PetDto;
import com.model.Pet;

public interface IPetService {

	PetDto savePet(PetDto petDto);

	PetDto updatePet(Long petId, PetDto petDto);

	void deletePet(Long petId);

	List<Pet> fetchAllPets();

	Pet getPetById(Long petId);

}
