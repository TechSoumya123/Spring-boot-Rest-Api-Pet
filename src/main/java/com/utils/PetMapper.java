package com.utils;

import org.springframework.stereotype.Component;

import com.dto.PetDto;
import com.model.Pet;

@Component
public class PetMapper {

	public static Pet existingToUpdated(Pet existingPet, PetDto petDto) {
		return existingPet.setName(petDto.name()).setColor(petDto.color());
	}

	public static PetDto petToPetDto(Pet pet) {
		return new PetDto(pet.getName(), pet.getColor());
	}

	public static Pet petDtoToPet(PetDto petDto) {
		return new Pet().setName(petDto.name()).setColor(petDto.color());
	}

}
