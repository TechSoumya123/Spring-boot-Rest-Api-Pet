package com.model;

import java.io.Serializable;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@NoArgsConstructor
@DynamicUpdate
@Entity
@Table(name = "pet_tb")
public class Pet implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pet_id")
	private Long petId;

	@NotBlank(message = "Every pet must specified a name..")
	@Column(name = "pet_name", nullable = false, length = 20)
	private String name;

	@NotBlank(message = "Every pet have their own color to identify")
	@Column(name = "pet_color", nullable = false, length = 25)
	private String color;

	public Pet(String name, String color) {
		this.name = name;
		this.color = color;
	}

}
