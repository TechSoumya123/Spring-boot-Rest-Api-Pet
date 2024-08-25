package com.controller.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.controller.PetController;
import com.dto.PetDto;
import com.exception.PetNotFoundException;
import com.service.IPetService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PetControllerImpl implements PetController {

	private final IPetService petService;
	final static Logger logger = LoggerFactory.getLogger(PetControllerImpl.class);

	@Override
	public ResponseEntity<PetDto> savePet(PetDto petDto) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(petService.savePet(petDto));
		} catch (Exception exception) {
			logger.error("\n Error getting during save request {}", exception.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}

	@Override
	public ResponseEntity<?> updatePet(PetDto petDto, Long petId) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(petService.updatePet(petId, petDto));
		} catch (PetNotFoundException exception) {
			logger.error("\n Error occured during find the pet {}", exception.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		} catch (Exception exception) {
			logger.error("\n Error occured during update the pet {}", exception.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

	@Override
	public ResponseEntity<Object> getAllPets() {
		try {
			var fetchAllPets = petService.fetchAllPets();
			return fetchAllPets.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(fetchAllPets);
		} catch (Exception exception) {
			logger.error("\n {}", exception.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}

	@Override
	public ResponseEntity<?> getPetById(Long petId) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(petService.getPetById(petId));
		} catch (PetNotFoundException exception) {
			logger.error("\n Error occured during find the pet {}", exception.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		} catch (Exception exception) {
			logger.error("\n Error occured during update the pet {}", exception.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> deletePet(Long petId) {
		try {
			petService.deletePet(petId);
			return ResponseEntity.status(HttpStatus.OK).body("Pet deleted successfully!");
		} catch (PetNotFoundException exception) {
			logger.error("\n Error occured during find the pet {}", exception.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		} catch (Exception exception) {
			logger.error("\n Error occured during update the pet {}", exception.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

}
