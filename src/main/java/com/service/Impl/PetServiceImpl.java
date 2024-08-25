package com.service.Impl;

import static com.utils.PetMapper.existingToUpdated;
import static com.utils.PetMapper.petToPetDto;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dto.PetDto;
import com.exception.PetNotFoundException;
import com.model.Pet;
import com.repository.PetRepository;
import com.service.IPetService;
import com.utils.PetMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements IPetService {

	private final PetRepository petRepository;

	@Override
	public PetDto savePet(PetDto petDto) {
		var pet = PetMapper.petDtoToPet(petDto);
		var savePet = petRepository.save(pet);
		return petToPetDto(savePet);

	}

	@Transactional
	@Override
	public PetDto updatePet(Long petId, PetDto petDto) {
		return petRepository.findById(petId).map(existingPet -> existingToUpdated(existingPet, petDto))
				.map(pet -> petToPetDto(pet)).get();
	}

	@Override
	public List<Pet> fetchAllPets() {
		return petRepository.findAll();
	}

	@Override
	public Pet getPetById(Long petId) {
		return petRepository.findById(petId).orElseThrow(() -> new PetNotFoundException("Sorry, Pet not found!"));
	}

	@Override
	public void deletePet(Long petId) {
		Optional.of(getPetById(petId)).ifPresent(petRepository::delete);
	}

}
