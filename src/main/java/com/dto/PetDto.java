package com.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public record PetDto(

		@NotBlank(message = "Pet name must not be null") 
		@JsonProperty(value = "pet_name")
		String name,

		@NotBlank(message = "Pet name must not be null")
		@JsonProperty(value = "pet_color")
		String color) implements Serializable {

}
