package com.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dto.PetDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@Validated
@RequestMapping(path = { "/api/v1/pet" })
public interface PetController {

	@PostMapping(path = { "/create" })
	public ResponseEntity<PetDto> savePet(@RequestBody @Valid final PetDto petDto);

	@PutMapping(path = { "/update/{id}" })
	public ResponseEntity<?> updatePet(@RequestBody @Valid final PetDto petDto,
			@PathVariable("id") @Min(value = 1, message = "Pet id must be grater than 0") final Long petId);

	@GetMapping(path = { "/getAll" })
	public ResponseEntity<Object> getAllPets();

	@GetMapping(path = { "/byId/{id}" })
	public ResponseEntity<?> getPetById(
			@PathVariable("id") @Min(value = 1, message = "Pet id must be grater than 0") final Long petId);

	@DeleteMapping(path = { "/delete/{id}" })
	public ResponseEntity<?> deletePet(
			@PathVariable("id") @Min(value = 1, message = "Pet id must be grater than 0") final Long petId);
}
